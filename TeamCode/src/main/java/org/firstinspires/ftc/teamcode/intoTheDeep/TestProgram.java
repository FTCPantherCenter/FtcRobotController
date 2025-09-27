package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
@Disabled
public class TestProgram extends LinearOpMode {

    DcMotor leftFront;
    DcMotor rightFront;


    @Override
    public void runOpMode() throws InterruptedException {
        //initialization
        leftFront = hardwareMap.get(DcMotor.class, "leftSideFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightSideFront");
        //direction
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        //running the motor

        waitForStart();

        leftFront.setPower(1);
        rightFront.setPower(1);
        sleep(2000);
        leftFront.setPower(0.1);
        rightFront.setPower(0.25);
        sleep(1000);
        leftFront.setPower(-0.75);
        rightFront.setPower(-0.5);
        sleep(1000);
        leftFront.setPower(-0.675);
        rightFront.setPower(-0.875);
        sleep(2000);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }
}

