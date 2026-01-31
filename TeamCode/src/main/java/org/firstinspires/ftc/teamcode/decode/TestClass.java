package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This tests the robot with a basic telemetry
 */
@TeleOp
public class TestClass extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Hello","World");
    }
    public void loop(){

    }
}
