package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="RevBotTele")
public class RevBotTele extends OpMode {
    private RevBot revBot;

    @Override
    public void init(){
        revBot = new RevBot(telemetry, hardwareMap);
    }

    @Override
    public void loop(){
        revBot.teleOp(gamepad1, gamepad2);
        telemetry.update();
    }

    @Override
    public void stop(){
        revBot.stopAll();
    }
}
