package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="CoachBotAuto", group="")
public class CoachBotAuto extends LinearOpMode {
    private CoachBot coachBot;

    @Override
    public void runOpMode(){
        coachBot = new CoachBot(telemetry, hardwareMap);

        waitForStart();

        while(opModeIsActive()){

        }
    }
}
