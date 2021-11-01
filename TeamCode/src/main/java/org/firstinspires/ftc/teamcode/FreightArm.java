package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class FreightArm {
    private Telemetry telemetry;
    private AnalogInput potentiometer;
    private DcMotor motorArm;
    private PID_Controller pid_controller;

    private enum ARM_STATE {
        SCOOP,
        CRUISE,
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


    public FreightArm(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;

        motorArm = in_hardwareMap.get(DcMotor.class, "motorArm");
        motorArm.setDirection(DcMotorSimple.Direction.REVERSE);
        potentiometer = in_hardwareMap.get(AnalogInput.class, "potentiometer");
        pid_controller = new PID_Controller(3.5, 0.001, 0.0, 1.0, -0.5);

        state = ARM_STATE.CRUISE;
        GOAL_VOLTAGE = VOLTAGE_LEVEL_ONE;

        motorArm.setPower(0.0);
    }

    public void execute(boolean scoop_request, boolean cruise_request, boolean level_two_request, boolean level_three_request, boolean manual_mode_request, double manual_power){
        telemetry.addData("faceButtons", scoop_request+"-"+cruise_request+"-"+level_two_request+"-"+level_three_request);
        if(scoop_request && !cruise_request && !level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.SCOOP){
            state = ARM_STATE.SCOOP;
            GOAL_VOLTAGE = VOLTAGE_GROUND;
            pid_controller.reset();
        }else if(!scoop_request && cruise_request && !level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.CRUISE){
            state = ARM_STATE.CRUISE;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_ONE;
            pid_controller.reset();
        }else if(!scoop_request && !cruise_request && level_two_request && !level_three_request && !manual_mode_request && state != ARM_STATE.LEVEL_TWO){
            state = ARM_STATE.LEVEL_TWO;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_TWO;
            pid_controller.reset();
        }else if(!scoop_request && !cruise_request && !level_two_request && level_three_request && !manual_mode_request && state != ARM_STATE.LEVEL_THREE){
            state = ARM_STATE.LEVEL_THREE;
            GOAL_VOLTAGE = VOLTAGE_LEVEL_THREE;
            pid_controller.reset();
        }else if(!scoop_request && !cruise_request && !level_two_request && level_three_request && manual_mode_request && state != ARM_STATE.MANUAL_CONTROL){
            state = ARM_STATE.MANUAL_CONTROL;
        }else{
            //multiple or nothing pushed, leave state alone
        }

        arm_control(manual_power);
    }

    private void arm_control(double manual_power){
        if(this.state == ARM_STATE.MANUAL_CONTROL){
            arm_manual_control(manual_power);
        }else{
            arm_state_control();
        }
    }

    private void arm_state_control(){
        telemetry.addData("state", this.state);
        double error = GOAL_VOLTAGE - potentiometer.getVoltage();
        double outputPower = pid_controller.getOutput(error);

        motorArm.setPower(outputPower);

        telemetry.addData("errVolt", error);
        telemetry.addData("potVolt", potentiometer.getVoltage());
        telemetry.addData("armPow", outputPower);
    }

    public void arm_manual_control(double power){
        motorArm.setPower(power);
        telemetry.addData("potVolt", potentiometer.getVoltage());
        telemetry.addData("armPow", power);
    }
}
