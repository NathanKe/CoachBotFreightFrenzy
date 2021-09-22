package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="CoachBotTele",group="")
public class CoachBotTele extends OpMode {
    private CoachBot coachBot;

    @Override
    public void init(){
        coachBot = new CoachBot(telemetry, hardwareMap);
    }

    @Override
    public void loop(){
        coachBot.teleOp(gamepad1, gamepad2);
    }

    @Override
    public void stop(){
        coachBot.stopAll();
    }
}
