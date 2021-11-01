package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.time.chrono.MinguoChronology;

@Config
public class PID_Controller {
    private Telemetry telemetry;

    public static double PROPORTIONAL_CONSTANT;
    public static double INTEGRAL_CONSTANT;
    public static double DERIVATIVE_CONSTANT;

    public double INTEGRAL_ERROR;
    private double PREV_ERROR;

    public static double MAX_OUTPUT;
    public static double MIN_OUTPUT;

    public ElapsedTime TIMER;
    private double PREV_MILLISECONDS;

    PID_Controller(double in_Prop, double in_Intr, double in_Deriv, double in_MaxOut, double in_minOut, Telemetry in_telemetry){
        PROPORTIONAL_CONSTANT = in_Prop;
        INTEGRAL_CONSTANT = in_Intr;
        DERIVATIVE_CONSTANT = in_Deriv;

        MAX_OUTPUT = in_MaxOut;
        MIN_OUTPUT = in_minOut;

        TIMER = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        TIMER.reset();

        telemetry = in_telemetry;
    }

    public void reset(){
        this.INTEGRAL_ERROR = 0.0;
        TIMER.reset();
    }

    public double getOutput(double error){
        double TIME_DIFF = TIMER.milliseconds() - PREV_MILLISECONDS;
        PREV_MILLISECONDS = TIMER.milliseconds();
        PREV_ERROR = error;
        INTEGRAL_ERROR += error * TIME_DIFF;

        double p_val = PROPORTIONAL_CONSTANT * error;
        double i_val = INTEGRAL_CONSTANT * INTEGRAL_ERROR;
        double d_val = DERIVATIVE_CONSTANT * ((error - PREV_ERROR)/TIME_DIFF);

        telemetry.addData("p_val", p_val);
        telemetry.addData("i_val", i_val);
        telemetry.addData("d_val", d_val);


        double raw_out = p_val + i_val + d_val;

        if(raw_out <= MIN_OUTPUT){
            return MIN_OUTPUT;
        }else if(raw_out >= MAX_OUTPUT){
            return MAX_OUTPUT;
        }else{
            return raw_out;
        }
    }
}
