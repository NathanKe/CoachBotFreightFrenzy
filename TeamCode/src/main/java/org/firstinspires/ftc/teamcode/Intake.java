package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private Telemetry telemetry;
    private DcMotor motorIntake;

    public Intake(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        motorIntake = in_hardwareMap.get(DcMotor.class, "motorIntake");
        motorIntake.setPower(0.0);
    }

    public void simpleDrive(double power){
        motorIntake.setPower(power);
        telemetry.addData("armPow", power);
    }
}
