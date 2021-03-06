package org.firstinspires.ftc.teamcode.revbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class PushBotPlatform {
    private final Telemetry telemetry;
    private final FtcDashboard dashboard;

    private final DcMotor motorRight;
    private final DcMotor motorLeft;

    private final DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");

    PushBotPlatform(Telemetry in_telemetry, HardwareMap in_hwMap, FtcDashboard in_dashboard) {
        telemetry = in_telemetry;
        dashboard = in_dashboard;

        motorRight = in_hwMap.get(DcMotor.class, "motorRight");
        motorLeft = in_hwMap.get(DcMotor.class, "motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        stopDriveMotors();
    }

    void simpleDrive(double left_y, double right_x) {
        double scale = Math.max(Math.abs(left_y) + Math.abs(right_x), 1);

        double left_pow = (left_y + right_x) / scale;
        double right_pow = (left_y - right_x) / scale;

        motorLeft.setPower(left_pow);
        motorRight.setPower(right_pow);

        telemetry.addData("leftPow", left_pow);
        telemetry.addData("rightPow", right_pow);
    }

    public void stopDriveMotors() {
        motorRight.setPower(0.0);
        motorLeft.setPower(0.0);
    }
}
