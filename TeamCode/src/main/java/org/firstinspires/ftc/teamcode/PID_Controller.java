package org.firstinspires.ftc.teamcode;

public class PID_Controller {
    private double PROPORTIONAL_CONSTANT;
    private double INTEGRAL_CONSTANT;
    private double DERIVATIVE_CONSTANT;

    private double INTEGRAL_ERROR;
    private double PREV_ERROR;

    private double MAX_OUTPUT;

    PID_Controller(double in_Prop, double in_Intr, double in_Deriv, double in_MaxOut){
        PROPORTIONAL_CONSTANT = in_Prop;
        INTEGRAL_CONSTANT = in_Intr;
        DERIVATIVE_CONSTANT = in_Deriv;

        MAX_OUTPUT = in_MaxOut;
    }

    public void reset(){
        INTEGRAL_ERROR = 0.0;
    }

    public double output(double error){
        double p_val = PROPORTIONAL_CONSTANT * error;

        INTEGRAL_ERROR += error;
        double i_val = INTEGRAL_CONSTANT * INTEGRAL_ERROR;

        double d_val = DERIVATIVE_CONSTANT * (error - PREV_ERROR);
        PREV_ERROR = error;

        double raw_out = p_val + i_val + d_val;
        double clipped_out = Math.signum(raw_out) + Math.min(MAX_OUTPUT, Math.abs(raw_out));

        return clipped_out;
    }
}
