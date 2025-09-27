package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Disabled
public class TestClass extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Hello","World");
    }
    public void loop(){

    }
}
