package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class PushBotPlatform {
    private Telemetry telemetry;

    private DcMotor motorRight;
    private DcMotor motorLeft;

    private DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");

    PushBotPlatform(Telemetry in_telemetry, HardwareMap in_hwMap) {
        this.telemetry = in_telemetry;
        motorRight = in_hwMap.get(DcMotor.class, "motorRight");
        motorLeft = in_hwMap.get(DcMotor.class, "motorLeft");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        stopAll();
    }

    void simpleDrive(double left_y, double right_x) {
        double scale = Math.max(Math.abs(left_y)+Math.abs(right_x), 1);

        double left_pow = (left_y + right_x)/scale;
        double right_pow = (left_y - right_x)/scale;

        motorLeft.setPower(left_pow);
        motorRight.setPower(right_pow);

        telemetry.addData("leftPow", left_pow);
        telemetry.addData("rightPow", right_pow);
    }

    private void stopDriveMotors() {
        motorRight.setPower(0.0);
        motorLeft.setPower(0.0);
    }

    void stopAll() {
        stopDriveMotors();
    }
}
