package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CoachBot {
    private CarouselSpinner carouselSpinner;
    private DriveTrain driveTrain;
    private FreightArm freightArm;
    private Intake intake;

    private Telemetry telemetry;

    public CoachBot(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        this.initialize(telemetry, in_hardwareMap);
    }

    public void initialize(Telemetry in_telemetry, HardwareMap hardwareMap){
        carouselSpinner = new CarouselSpinner(telemetry, hardwareMap);
        driveTrain = new DriveTrain(telemetry, hardwareMap);
        freightArm = new FreightArm(telemetry, hardwareMap);
        intake = new Intake(telemetry, hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){

    }

    public void stopAll(){

    }
}
