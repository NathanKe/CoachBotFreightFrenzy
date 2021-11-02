package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class FreightArm {
    private final Telemetry telemetry;
    private final AnalogInput potentiometer;
    private final DcMotor motorArm;
    private final PID_Controller pid_controller;

    private enum ARM_STATE {
        GROUND,
        LEVEL_ONE,
        LEVEL_TWO,
        LEVEL_THREE,
        MANUAL_CONTROL
    }

    private ARM_STATE state;
    private double GOAL_VOLTAGE;

    public static double VOLTAGE_GROUND = 0.47;
    public static double VOLTAGE_LEVEL_ONE = 0.62;
    public static double VOLTAGE_LEVEL_TWO = 0.78;
    public static double VOLTAGE_LEVEL_THREE = 0.90;


    public FreightArm(Telemetry in_telemetry, HardwareMap in_hardwareMap) {
        telemetry = in_telemetry;

        motorArm = in_hardwareMap.get(DcMotor.class, "motorArm");
        motorArm.setDirection(DcMotorSimple.Direction.REVERSE);
        potentiometer = in_hardwareMap.get(AnalogInput.class, "potentiometer");
        pid_controller = new PID_Controller(8.0, 0.0, 0.0, 0.75, -0.05, in_telemetry);

        VOLTAGE_GROUND = potentiometer.getVoltage();
        VOLTAGE_LEVEL_ONE = VOLTAGE_GROUND + 0.16;
        VOLTAGE_LEVEL_TWO = VOLTAGE_LEVEL_ONE + 0.16;
        VOLTAGE_LEVEL_THREE = VOLTAGE_LEVEL_TWO + 0.16;

        state = ARM_STATE.LEVEL_ONE;
        GOAL_VOLTAGE = VOLTAGE_LEVEL_ONE;

        motorArm.setPower(0.0);
    }

    public void execute(boolean ground_request, boolean level_one_request, boolean level_two_request, boolean level_three_request, boolean manual_mode_request, double manual_power) {
        telemetry.addData("faceButtons", ground_request + "-" + level_one_request + "-" + level_two_request + "-" + level_three_request + "--" + manual_mode_request);

        if (ground_request && !level_one_request && !level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.GROUND) {
            state = ARM_STATE.GROUND;
            GOAL_VOLTAGE = VOLTAGE_GROUND;
            pid_controller.reset();
        } else if (!ground_request && level_one_request && !level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.LEVEL_ONE) {
            state = ARM_STATE.LEVEL_ONE;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_ONE;
            pid_controller.reset();
        } else if (!ground_request && !level_one_request && level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.LEVEL_TWO) {
            state = ARM_STATE.LEVEL_TWO;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_TWO;
            pid_controller.reset();
        } else if (!ground_request && !level_one_request && !level_two_request && level_three_request && !manual_mode_request && state != ARM_STATE.LEVEL_THREE) {
            state = ARM_STATE.LEVEL_THREE;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_THREE;
            pid_controller.reset();
        } else if (!ground_request && !level_one_request && !level_two_request && !level_three_request && manual_mode_request && state != ARM_STATE.MANUAL_CONTROL) {
            state = ARM_STATE.MANUAL_CONTROL;
        } else {
            //multiple or nothing pushed, leave state alone
        }

        arm_control(manual_power);
    }

    private void arm_control(double manual_power) {
        if (this.state == ARM_STATE.MANUAL_CONTROL) {
            arm_manual_control(manual_power);
        } else {
            arm_state_control();
        }
    }

    private void arm_state_control() {
        telemetry.addData("state", this.state);
        double error = GOAL_VOLTAGE - potentiometer.getVoltage();
        double outputPower = pid_controller.getOutput(error);

        motorArm.setPower(outputPower);

        telemetry.addData("errVolt", error);
        telemetry.addData("potVolt", potentiometer.getVoltage());
        telemetry.addData("armPow", outputPower);
    }

    public void arm_manual_control(double power) {
        motorArm.setPower(power);
        telemetry.addData("potVolt", potentiometer.getVoltage());
        telemetry.addData("armPow", power);
    }
}
