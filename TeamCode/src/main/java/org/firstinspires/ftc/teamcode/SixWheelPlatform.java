package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class SixWheelPlatform {
    private Telemetry telemetry;

    private DcMotor motorRightOne;
    private DcMotor motorRightTwo;
    private DcMotor motorLeftOne;
    private DcMotor motorLeftTwo;

    private DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");

    SixWheelPlatform(Telemetry in_telemetry, HardwareMap in_hwMap) {
        this.telemetry = in_telemetry;
        motorRightOne = in_hwMap.get(DcMotor.class, "motorRightOne");
        motorRightTwo = in_hwMap.get(DcMotor.class, "motorRightTwo");
        motorLeftOne = in_hwMap.get(DcMotor.class, "motorLeftOne");
        motorLeftTwo = in_hwMap.get(DcMotor.class, "motorLeftTwo");
        stopAll();
    }

    void baseDriveTrain(double left_y, double right_y, double scale) {
        motorRightOne.setPower(right_y);
        motorRightTwo.setPower(-1 * right_y);
        motorLeftOne.setPower(-1 * left_y);
        motorLeftTwo.setPower(left_y);
    }

    private void stopDriveMotors() {
        motorLeftOne.setPower(0.0);
        motorLeftTwo.setPower(0.0);
        motorRightOne.setPower(0.0);
        motorRightTwo.setPower(0.0);
    }

    void stopAll() {
        stopDriveMotors();
    }

}
