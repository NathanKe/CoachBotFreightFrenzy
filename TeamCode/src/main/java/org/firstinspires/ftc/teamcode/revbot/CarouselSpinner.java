package org.firstinspires.ftc.teamcode.revbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CarouselSpinner {
    private final Telemetry telemetry;
    private final FtcDashboard dashboard;

    private CRServo spinner;

    public CarouselSpinner(Telemetry in_telemetry, HardwareMap in_hardwareMap, FtcDashboard in_dashboard) {
        telemetry = in_telemetry;
        dashboard = in_dashboard;
        spinner = in_hardwareMap.get(CRServo.class, "carouselSpinner");
    }

    public void spin(double power){
        spinner.setPower(power);
    }
}
