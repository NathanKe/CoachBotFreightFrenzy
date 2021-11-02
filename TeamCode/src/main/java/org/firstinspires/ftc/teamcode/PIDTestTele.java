package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//@TeleOp(name="PIDTest")
public class PIDTestTele extends OpMode {
    private Telemetry telemetry;
    private FtcDashboard dashboard;
    private PID_Controller pid_controller;
    private DcMotor motor;

    private final int GOAL_TICKS = 500;
    double cur_error;

    @Override
    public void init() {
        pid_controller = new PID_Controller(0.005, 0, 0, 1.0, -0.5, telemetry, dashboard);
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pid_controller.reset();
    }

    @Override
    public void loop() {

        TelemetryPacket tp = new TelemetryPacket();
        cur_error = GOAL_TICKS - motor.getCurrentPosition();
        double out_power = pid_controller.getOutput(cur_error, tp);

        motor.setPower(out_power);

        telemetry.addData("pow", out_power);
        telemetry.addData("err", cur_error);
        telemetry.addData("ticks", motor.getCurrentPosition());
        telemetry.update();


    }
}
