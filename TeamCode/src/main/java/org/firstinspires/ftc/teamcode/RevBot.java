package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RevBot {
    private PushBotPlatform pushBotPlatform;
    private FreightArm freightArm;
    private Intake intake;

    private Telemetry telemetry;

    public RevBot(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        pushBotPlatform = new PushBotPlatform(in_telemetry, in_hardwareMap);
        freightArm = new FreightArm(in_telemetry, in_hardwareMap);
        intake = new Intake(in_telemetry, in_hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){
        //player one drives
        pushBotPlatform.simpleDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);

        //player two works the arm
        intake.simpleDrive(-1 * gamepad2.left_stick_y);
        freightArm.execute(gamepad2.a, gamepad2.b, gamepad2.x, gamepad2.y);
    }

    public void stopAll(){
        pushBotPlatform.stopDriveMotors();
        freightArm.simpleDrive(0.0);
        intake.simpleDrive(0.0);
    }
}
