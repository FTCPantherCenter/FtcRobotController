package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



@TeleOp
public class BaseMecanumDriveRemade extends OpMode {
basicFunctions doStuff;

public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);
    }


    @Override
    //basic movement and motor control
    public void loop() {




        if (gamepad1.left_stick_x != 0.0 ){
            //turning with left joystick horizontal
            doStuff.turn(gamepad1.left_stick_x)
        }


        if (gamepad1.right_stick_x != 0.0 || gamepad1.right_stick_y != 0.0) {
            doStuff.move(gamepad1.right_stick_x,gamepad1.right_stick_y)
        }

    }
    //stop button pressed
    public void stop(){
        doStuff.move(0)
    }
}
