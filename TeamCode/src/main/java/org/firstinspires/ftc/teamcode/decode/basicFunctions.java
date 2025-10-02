package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class basicFunctions{
    public void basicFunctions() {
        DcMotor leftFront = null;
        DcMotor rightFront = null;
        DcMotor leftBack = null;
        DcMotor rightBack = null;
    }

    public void init(HardwareMap hwMap){
        leftFront = hwMap.get(DcMotor.class, "fl_drive");
        leftBack = hwMap.get(DcMotor.class, "bl_drive");
        rightFront = hwMap.get(DcMotor.class, "fr_drive");
        rightBack = hwMap.get(DcMotor.class, "br_drive");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    public void move(double power_y, double power_x){
        rightFront.setPower((-power_x + (-power_y)));
        rightBack.setPower(((power_x) + (-power_y)));
        leftFront.setPower(((power_x) + (-power_y)));
        leftBack.setPower((-power_x + (-power_y)));


    }

    public void turn(double turn_pow)
        leftFront.setPower(turn_pow);
        rightFront.setPower(-turn_pow);
        leftBack.setPower(turn_pow);
        rightBack.setPower(-turn_pow);
}
