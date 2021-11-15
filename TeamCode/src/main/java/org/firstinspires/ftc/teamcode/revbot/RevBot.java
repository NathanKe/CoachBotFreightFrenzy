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

    public void twoPlayerTeleOp(Gamepad gamepad1, Gamepad gamepad2) {
        //player one drives
        pushBotPlatform.simpleDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);

        //player two works the arm & spinner
        intake.intake(gamepad2.left_trigger - gamepad2.right_trigger * 0.5);
        freightArm.execute(gamepad2.cross, gamepad2.square, gamepad2.triangle, gamepad2.circle, gamepad2.left_stick_button, -1.0 * gamepad2.left_stick_y);
        if(gamepad2.dpad_right){
            carouselSpinner.spin(1.0);
        }else if(gamepad2.dpad_left){
            carouselSpinner.spin(-1.0);
        }else{
            carouselSpinner.spin(0.0);
        }
    }

    public void onePlayerTeleOp(Gamepad gamepad1) {
        //player one drives
        pushBotPlatform.simpleDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);

        //player two works the arm & spinner
        intake.intake(gamepad1.left_trigger - gamepad1.right_trigger * 0.5);
        freightArm.execute(gamepad1.cross, gamepad1.square, gamepad1.triangle, gamepad1.circle, gamepad1.left_stick_button, 0.0);
        if(gamepad1.dpad_right){
            carouselSpinner.spin(1.0);
        }else if(gamepad1.dpad_left){
            carouselSpinner.spin(-1.0);
        }else{
            carouselSpinner.spin(0.0);
        }
    }

    public void stopAll() {
        pushBotPlatform.stopDriveMotors();
        freightArm.arm_manual_control(0.0);
        intake.intake(0.0);
        carouselSpinner.spin(0.0);
    }
}
