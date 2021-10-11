package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CoachBot {
    private CarouselSpinner carouselSpinner;
    private SixWheelPlatform driveTrain;
    private FreightArm freightArm;
    private Intake intake;

    private Telemetry telemetry;

    public CoachBot(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        this.initialize(in_telemetry, in_hardwareMap);
    }

    public void initialize(Telemetry in_telemetry, HardwareMap hardwareMap){
        carouselSpinner = new CarouselSpinner(in_telemetry, hardwareMap);
        driveTrain = new SixWheelPlatform(in_telemetry, hardwareMap);
        freightArm = new FreightArm(in_telemetry, hardwareMap);
        intake = new Intake(in_telemetry, hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){
        driveTrain.baseDriveTrain(gamepad1.left_stick_y, gamepad1.right_stick_y, 1.0);

        freightArm.execute(gamepad2.a, gamepad2.b, gamepad2.x, gamepad2.y);
    }

    public void stopAll(){
        driveTrain.stopAll();
    }
}
