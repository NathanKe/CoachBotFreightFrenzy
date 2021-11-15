package org.firstinspires.ftc.teamcode.revbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private final Telemetry telemetry;
    private final FtcDashboard dashboard;

    private final DcMotor motorIntake;

    public Intake(Telemetry in_telemetry, HardwareMap in_hardwareMap, FtcDashboard in_dashboard) {
        telemetry = in_telemetry;
        dashboard = in_dashboard;

        motorIntake = in_hardwareMap.get(DcMotor.class, "motorIntake");
        motorIntake.setPower(0.0);
    }

    public void intake(double power) {
        motorIntake.setPower(power);
        telemetry.addData("armPow", power);
    }
}
