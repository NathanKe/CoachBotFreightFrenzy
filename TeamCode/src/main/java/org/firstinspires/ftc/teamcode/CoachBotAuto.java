package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="CoachBotAuto")
public class CoachBotAuto extends LinearOpMode {
    private RevBot revBot;

    @Override
    public void runOpMode(){
        revBot = new RevBot(telemetry, hardwareMap);

        waitForStart();

        while(opModeIsActive()){

        }
    }
}
