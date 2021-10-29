package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
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
        LEVEL_THREE
    }

    private ARM_STATE state;

    public static double VOLTAGE_GROUND;
    public static double VOLTAGE_LEVEL_ONE;
    public static double VOLTAGE_LEVEL_TWO;
    public static double VOLTAGE_LEVEL_THREE;


    public FreightArm(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;

        motorArm = in_hardwareMap.get(DcMotor.class, "motorArm");
        potentiometer = in_hardwareMap.get(AnalogInput.class, "potentiometer");
        pid_controller = new PID_Controller(0.0, 0.0, 0.0, 1.0);

        state = ARM_STATE.CRUISE;

        motorArm.setPower(0.0);
    }

    public void execute(boolean scoop_request, boolean cruise_request, boolean level_two_request, boolean level_three_request){
        if(scoop_request && !cruise_request && !level_two_request && !level_three_request && state != ARM_STATE.SCOOP){
            state = ARM_STATE.SCOOP;
            pid_controller.reset();
        }else if(!scoop_request && cruise_request && !level_two_request && !level_three_request && state != ARM_STATE.CRUISE){
            state = ARM_STATE.CRUISE;
            pid_controller.reset();
        }else if(!scoop_request && !cruise_request && level_two_request && !level_three_request && state != ARM_STATE.LEVEL_TWO){
            state = ARM_STATE.LEVEL_TWO;
            pid_controller.reset();
        }else if(!scoop_request && !cruise_request && !level_two_request && level_three_request && state != ARM_STATE.LEVEL_THREE){
            state = ARM_STATE.LEVEL_THREE;
            pid_controller.reset();
        }else{
            //multiple or nothing pushed, leave state alone
        }

        arm_control(state);
    }

    private double voltage_error(ARM_STATE in_state){
        double goal_voltage;
        switch (in_state) {
            case SCOOP:
                goal_voltage = VOLTAGE_GROUND;
            case CRUISE:
                goal_voltage = VOLTAGE_LEVEL_ONE;
            case LEVEL_TWO:
                goal_voltage = VOLTAGE_LEVEL_TWO;
            case LEVEL_THREE:
                goal_voltage = VOLTAGE_LEVEL_THREE;
            default:
                goal_voltage = VOLTAGE_GROUND;
        }

        return goal_voltage - potentiometer.getVoltage();
    }

    private void arm_control(ARM_STATE goal_state){
        double error = voltage_error(goal_state);
        double outputPower = pid_controller.getOutput(error);

        motorArm.setPower(outputPower);
    }

    public void simpleDrive(double power){
        motorArm.setPower(power);
        telemetry.addData("armPow", power);
        telemetry.addData("potVolt", potentiometer.getVoltage());
    }
}
