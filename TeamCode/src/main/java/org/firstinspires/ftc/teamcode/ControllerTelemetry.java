package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.text.DecimalFormat;

public class ControllerTelemetry extends OpMode {
    private final DecimalFormat twoDecimals = new DecimalFormat("0.00");

    String controllerState(Gamepad gp) {
        String outStr = "";

        String joysticks = "LX: " + twoDecimals.format(gp.left_stick_x) + " LY: " + twoDecimals.format(gp.left_stick_y) + " RX: " + twoDecimals.format(gp.right_stick_x) + " RY: " + twoDecimals.format(gp.right_stick_y);
        String shoulders = "RB: " + (gp.right_bumper ? 1 : 0) + " RT: " + twoDecimals.format(gp.right_trigger) + " LB: " + (gp.left_bumper ? 1 : 0) + " LT: " + twoDecimals.format(gp.left_trigger);
        String face = "A: " + (gp.a ? 1 : 0) + " B: " + (gp.b ? 1 : 0) + " X: " + (gp.x ? 1 : 0) + " Y: " + (gp.y ? 1 : 0) + " U: " + (gp.dpad_up ? 1 : 0) + " D: " + (gp.dpad_down ? 1 : 0) + " L: " + (gp.dpad_left ? 1 : 0) + " R: " + (gp.dpad_right ? 1 : 0);

        outStr = joysticks + System.lineSeparator() + shoulders + System.lineSeparator() + face;

        return outStr;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addLine("Gamepad1:");
        telemetry.addLine(controllerState(gamepad1));
        telemetry.addLine("Gamepad2:");
        telemetry.addLine(controllerState(gamepad2));
        telemetry.update();
    }
}
