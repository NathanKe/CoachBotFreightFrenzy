package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.revbot.RevBot;

@TeleOp(name = "RevBotTele", group = "")
public class RevBotTele extends OpMode {
    public static RevBot revBot;
    FtcDashboard dashboard;

    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        revBot = new RevBot(telemetry, hardwareMap, dashboard);
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
