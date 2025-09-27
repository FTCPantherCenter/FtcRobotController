//auto code, postion on the right side of the submersible and this should park the robot in the observation zone
//untested
package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
@Disabled
//asd.
public class autoV2right extends LinearOpMode{

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor leftBack;

    final double sensitivity = 1.00;
    public void fullStop() {
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        leftFront.setPower(0);
    }

    public void startLeft(){
        leftBack.setPower(sensitivity);
        leftFront.setPower(-sensitivity);
        rightFront.setPower(sensitivity);
        rightBack.setPower(-sensitivity);
    }
    public void startRight(){
        leftBack.setPower(-sensitivity);
        leftFront.setPower(sensitivity);
        rightFront.setPower(-sensitivity);
        rightBack.setPower(sensitivity);
    }
    @Override
    public void runOpMode() throws InterruptedException{
        //initialization
        leftFront = hardwareMap.get(DcMotor.class, "fl_drive");
        leftBack = hardwareMap.get(DcMotor.class, "bl_drive");
        rightFront = hardwareMap.get(DcMotor.class, "fr_drive");
        rightBack = hardwareMap.get(DcMotor.class, "br_drive");


        //direction
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

    //full stop function

        //running the motor

            waitForStart();
            //mecanum inside force direction
            //start moving right
            startRight();
            leftBack.setPower(-sensitivity);
            leftFront.setPower(sensitivity);
            rightFront.setPower(-sensitivity);
            rightBack.setPower(sensitivity);
            sleep(1250);
            //stop
            fullStop();
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }

    }

