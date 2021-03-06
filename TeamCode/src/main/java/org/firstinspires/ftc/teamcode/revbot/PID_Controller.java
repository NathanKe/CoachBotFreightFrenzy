package org.firstinspires.ftc.teamcode.revbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class PID_Controller {
    public static double PROPORTIONAL_CONSTANT;
    public static double INTEGRAL_CONSTANT;
    public static double DERIVATIVE_CONSTANT;
    public static double MAX_OUTPUT;
    public static double MIN_OUTPUT;
    private final Telemetry telemetry;
    private final FtcDashboard dashboard;
    public double INTEGRAL_ERROR;
    public ElapsedTime TIMER;
    private double PREV_ERROR;
    private double PREV_MILLISECONDS;

    PID_Controller(double in_Proportional, double in_Integral, double in_Derivative, double in_MaxOut, double in_minOut, Telemetry in_telemetry, FtcDashboard in_dashboard) {
        telemetry = in_telemetry;
        dashboard = in_dashboard;

        PROPORTIONAL_CONSTANT = in_Proportional;
        INTEGRAL_CONSTANT = in_Integral;
        DERIVATIVE_CONSTANT = in_Derivative;

        MAX_OUTPUT = in_MaxOut;
        MIN_OUTPUT = in_minOut;

        TIMER = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        TIMER.reset();
    }

    public void reset() {
        this.INTEGRAL_ERROR = 0.0;
        TIMER.reset();
    }

    public double getOutput(double error, TelemetryPacket tp) {
        double CUR_TIME = TIMER.milliseconds();
        double TIME_DIFF = CUR_TIME - PREV_MILLISECONDS;
        PREV_MILLISECONDS = CUR_TIME;
        PREV_ERROR = error;
        INTEGRAL_ERROR += error * TIME_DIFF;

        double p_val = PROPORTIONAL_CONSTANT * error;
        double i_val = INTEGRAL_CONSTANT * INTEGRAL_ERROR;
        double d_val = DERIVATIVE_CONSTANT * ((error - PREV_ERROR) / TIME_DIFF);
        double raw_out = p_val + i_val + d_val;
        double out;
        if (raw_out <= MIN_OUTPUT) {
            out = MIN_OUTPUT;
        } else {
            out = Math.min(MAX_OUTPUT, raw_out);
        }

        tp.put("p_val", p_val);
        tp.put("i_val", i_val);
        tp.put("d_val", d_val);
        tp.put("raw_out", raw_out);
        tp.put("out", out);

        return out;
    }
}
