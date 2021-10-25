package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID_Controller {
    private double PROPORTIONAL_CONSTANT;
    private double INTEGRAL_CONSTANT;
    private double DERIVATIVE_CONSTANT;

    private double INTEGRAL_ERROR;
    private double PREV_ERROR;

    private double MAX_OUTPUT;

    private ElapsedTime TIMER;
    private double PREV_MILLISECONDS;

    PID_Controller(double in_Prop, double in_Intr, double in_Deriv, double in_MaxOut){
        PROPORTIONAL_CONSTANT = in_Prop;
        INTEGRAL_CONSTANT = in_Intr;
        DERIVATIVE_CONSTANT = in_Deriv;

        MAX_OUTPUT = in_MaxOut;

        TIMER = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        TIMER.reset();
    }

    public void reset(){
        INTEGRAL_ERROR = 0.0;
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


        double raw_out = p_val + i_val + d_val;
        return Math.signum(raw_out) * Math.min(MAX_OUTPUT, Math.abs(raw_out));
    }
}