package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "RevBotTele", group = "")
public class RevBotTele extends OpMode {
    FtcDashboard dashboard;

    public static RevBot revBot;

    @Override
    public void init() {
        revBot = new RevBot(telemetry, hardwareMap);
        dashboard = FtcDashboard.getInstance();
    }

    @Override
    public void loop() {
        revBot.teleOp(gamepad1, gamepad2);
        telemetry.update();
    }

    @Override
    public void stop() {
        revBot.stopAll();
    }
}
