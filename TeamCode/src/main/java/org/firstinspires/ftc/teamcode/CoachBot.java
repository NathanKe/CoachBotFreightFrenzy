package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CoachBot {
    private CarouselSpinner carouselSpinner;
    private GyroMecanumPlatform driveTrain;
    private FreightArm freightArm;
    private Intake intake;

    private Telemetry telemetry;

    public CoachBot(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        this.initialize(in_telemetry, in_hardwareMap);
    }

    public void initialize(Telemetry in_telemetry, HardwareMap hardwareMap){
        carouselSpinner = new CarouselSpinner(in_telemetry, hardwareMap);
        driveTrain = new GyroMecanumPlatform(in_telemetry, hardwareMap);
        freightArm = new FreightArm(in_telemetry, hardwareMap);
        intake = new Intake(in_telemetry, hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){
        driveTrain.fieldDriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x,1.0);
    }

    public void stopAll(){
        driveTrain.mecanumPlatform.stopAll();
    }
}
