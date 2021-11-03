package org.firstinspires.ftc.teamcode.revbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RevBot {
    private final PushBotPlatform pushBotPlatform;
    private final FreightArm freightArm;
    private final Intake intake;
    private final CarouselSpinner carouselSpinner;

    public RevBot(Telemetry in_telemetry, HardwareMap in_hardwareMap, FtcDashboard in_dashboard) {

        pushBotPlatform = new PushBotPlatform(in_telemetry, in_hardwareMap, in_dashboard);
        freightArm = new FreightArm(in_telemetry, in_hardwareMap, in_dashboard);
        intake = new Intake(in_telemetry, in_hardwareMap, in_dashboard);
        carouselSpinner = new CarouselSpinner(in_telemetry, in_hardwareMap, in_dashboard);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2) {
        //player one drives
        pushBotPlatform.simpleDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);

        //player two works the arm
        intake.simpleDrive(gamepad2.left_trigger - gamepad2.right_trigger * 0.5);
        freightArm.execute(gamepad2.cross, gamepad2.square, gamepad2.triangle, gamepad2.circle, gamepad2.left_stick_button, -1.0 * gamepad2.left_stick_y);
    }

    public void stopAll() {
        pushBotPlatform.stopDriveMotors();
        freightArm.arm_manual_control(0.0);
        intake.simpleDrive(0.0);
    }
}
