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
        intake.simpleDrive(gamepad2.left_trigger - gamepad2.right_trigger * 0.25);

        freightArm.execute(gamepad2.cross, gamepad2.square, gamepad2.triangle, gamepad2.circle, gamepad2.left_stick_button, gamepad2.left_stick_y);

    }

    public void stopAll(){
        pushBotPlatform.stopDriveMotors();
        freightArm.arm_manual_control(0.0);
        intake.simpleDrive(0.0);
    }
}
