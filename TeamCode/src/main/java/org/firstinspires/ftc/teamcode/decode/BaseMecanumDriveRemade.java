package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



@TeleOp
public class BaseMecanumDriveRemade extends OpMode {



    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;




    //initialization
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "fl_drive");
        leftBack = hardwareMap.get(DcMotor.class, "bl_drive");
        rightFront = hardwareMap.get(DcMotor.class, "fr_drive");
        rightBack = hardwareMap.get(DcMotor.class, "br_drive");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    @Override
    //basic movement and motor control
    public void loop() {




        if (gamepad1.left_stick_x != 0.0 ){
            //turning with left joystick horizontal
            leftFront.setPower(gamepad1.left_stick_x );
            rightFront.setPower(-gamepad1.left_stick_x );
            leftBack.setPower(gamepad1.left_stick_x  );
            rightBack.setPower(-gamepad1.left_stick_x);
        }


        if (gamepad1.right_stick_x != 0.0 || gamepad1.right_stick_y != 0.0) {
            rightFront.setPower((-gamepad1.right_stick_x + (-gamepad1.right_stick_y)));
            rightBack.setPower(((gamepad1.right_stick_x) + (-gamepad1.right_stick_y)));
            leftFront.setPower(((gamepad1.right_stick_x) + (-gamepad1.right_stick_y)));
            leftBack.setPower((-gamepad1.right_stick_x + (-gamepad1.right_stick_y)));
        }

    }
    //stop button pressed
    public void stop(){
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
    }
}
