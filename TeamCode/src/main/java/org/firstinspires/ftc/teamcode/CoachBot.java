package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CoachBot {
    private PushBotPlatform driveTrain;
    private FreightArm freightArm;
    private Intake intake;

    private Telemetry telemetry;

    public CoachBot(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        this.initialize(in_telemetry, in_hardwareMap);
    }

    public void initialize(Telemetry in_telemetry, HardwareMap hardwareMap){
        driveTrain = new PushBotPlatform(in_telemetry, hardwareMap);
        freightArm = new FreightArm(in_telemetry, hardwareMap);
        intake = new Intake(in_telemetry, hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){
        driveTrain.simpleDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);
        freightArm.simpleDrive(-1 * gamepad2.left_stick_y);
        intake.simpleDrive(-1 * gamepad2.right_stick_y);
    }

    public void stopAll(){
        driveTrain.stopAll();
        freightArm.simpleDrive(0.0);
        intake.simpleDrive(0.0);
    }
}
