package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ArmTestTele extends OpMode {
    private FreightArm freightArm;

    @Override
    public void init() {
        freightArm = new FreightArm(telemetry, hardwareMap);
    }

    @Override
    public void loop() {
        freightArm.execute(gamepad1.a, gamepad2.b, gamepad2.x, gamepad2.y);
    }
}
