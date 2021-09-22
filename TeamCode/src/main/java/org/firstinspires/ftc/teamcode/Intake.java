package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private Telemetry telemetry;

    public Intake(Telemetry in_telemetry, HardwareMap in_hardwareMap){
        telemetry = in_telemetry;
        this.initialize(in_hardwareMap);
    }

    public void initialize(HardwareMap hardwareMap){

    }
}
