package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CoachBot {
    private CarouselSpinner carouselSpinner;
    private DriveTrain driveTrain;
    private FreightArm freightArm;
    private Intake intake;

    public void initialize(HardwareMap hardwareMap){
        carouselSpinner.initialize(hardwareMap);
        driveTrain.initialize(hardwareMap);
        freightArm.initialize(hardwareMap);
        intake.initialize(hardwareMap);
    }

    public void teleOp(Gamepad gamepad1, Gamepad gamepad2){

    }

    public void stopAll(){

    }
}
