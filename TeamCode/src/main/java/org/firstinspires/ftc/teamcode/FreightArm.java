package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FreightArm {
    private Telemetry telemetry;
    private AnalogInput potentiometer;
    private DcMotor motorArm;
    private PID_Controller pid_controller;

    private enum ARM_STATE {
        GROUND,
        LEVEL_ONE,
        LEVEL_TWO,
        LEVEL_THREE
    }

    private ARM_STATE state;

    private double VOLTAGE_GROUND;
    private double VOLTAGE_LEVEL_ONE;
    private double VOLTAGE_LEVEL_TWO;
    private double VOLTAGE_LEVEL_THREE;


    public FreightArm(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;

        motorArm = in_hardwareMap.get(DcMotor.class, "motorArm");
        potentiometer = in_hardwareMap.get(AnalogInput.class, "potentiometer");
        pid_controller = new PID_Controller(1.0,0, 0, 0);

        state = ARM_STATE.GROUND;

        motorArm.setPower(0.0);
    }

    public void execute(boolean a_pressed, boolean b_pressed, boolean x_pressed, boolean y_pressed){
        if(a_pressed && !b_pressed && !x_pressed && !y_pressed && state != ARM_STATE.GROUND){
            state = ARM_STATE.GROUND;
            pid_controller.reset();
        }else if(!a_pressed && b_pressed && !x_pressed && !y_pressed && state != ARM_STATE.LEVEL_ONE){
            state = ARM_STATE.LEVEL_ONE;
            pid_controller.reset();
        }else if(!a_pressed && !b_pressed && x_pressed && !y_pressed && state != ARM_STATE.LEVEL_TWO){
            state = ARM_STATE.LEVEL_TWO;
            pid_controller.reset();
        }else if(!a_pressed && !b_pressed && !x_pressed && y_pressed && state != ARM_STATE.LEVEL_THREE){
            state = ARM_STATE.LEVEL_THREE;
            pid_controller.reset();
        }else{
            //multiple or nothing pushed, leave state alone
        }

        arm_control(state);
    }

    private double voltage_from_state(ARM_STATE in_state){
        switch (in_state) {
            case LEVEL_ONE:
                return VOLTAGE_LEVEL_ONE;
            case LEVEL_TWO:
                return VOLTAGE_LEVEL_TWO;
            case LEVEL_THREE:
                return VOLTAGE_LEVEL_THREE;
            default:
                return VOLTAGE_GROUND;
        }
    }

    private void arm_control(ARM_STATE goal_state){

        double goal_voltage = voltage_from_state(goal_state);
        double current_voltage = potentiometer.getVoltage();

        double error = goal_voltage - current_voltage;
        double outputPower = pid_controller.output(error);

        motorArm.setPower(outputPower);
    }

    public void simpleDrive(double power){
        motorArm.setPower(power);
        telemetry.addData("armPow", power);
        telemetry.addData("potVolt", potentiometer.getVersion());
    }
}
