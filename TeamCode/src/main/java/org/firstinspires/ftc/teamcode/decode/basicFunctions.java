package org.firstinspires.ftc.teamcode.decode;

import android.text.InputFilter;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.net.CacheRequest;

/**
 * This class include basic robot moving functions such as:
 * Automatically initalizing the motors for "lowercase left-right" "Uppercase Front-Back", drive using encoder
 * The constructor defiles the DcMotors as null
 * 2 Methods are: move, turn(not including init or contstructor)
 * Move takes the input of a x and y power, meant to be controller joystick cordinates, and moves the motors using a mecanum formula
 * Turn takes 1 parameter, turn power, with a negative power turning left, and a positive power turning right. The input is meant to be a controller joystick x cordinate. The robot turns in place with proper mass distribution
 */

public class basicFunctions{
    /**
     * This is the constructor, defining all of the motors as null
     */
    public void basicFunctions() {
        DcMotor leftFront = null;
        DcMotor rightFront = null;
        DcMotor leftBack = null;
        DcMotor rightBack = null;
        //DcMotor flyLaunch = null;
        //CRServo leftBoost = null;
        //CRServo rightBoost = null;
    }

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    //DcMotor flyLaunch = null;
    //CRServo leftBoost = null;
    //CRServo rightBoost = null;
    /**
     * This is the hardwaremap, to be ran within the init(), it sets motors, sets their directions, and their run modes
     * @param hwMap In this variable you pass hardwareMap within the init(), which creates it
     */
    public void init(HardwareMap hwMap){
        leftFront = hwMap.get(DcMotor.class, "fl_drive");
        leftBack = hwMap.get(DcMotor.class, "bl_drive");
        rightFront = hwMap.get(DcMotor.class, "fr_drive");
        rightBack = hwMap.get(DcMotor.class, "br_drive");

        //flyLaunch = hwMap.get(DcMotor.class,"fly_wheel");
        //leftBoost = hwMap.get(CRServo.class,"left_boost");
        //rightBoost = hwMap.get(CRServo.class,"right_boost");


        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        //flyLaunch.setDirection((DcMotorSimple.Direction.FORWARD));

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //flyLaunch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * This moves the robot with mecanum, with the x and y functioning as coordinates to move to relative to the robot, This assumes a perfect center of mass
     * @param power_y This is where you put the y axis of the joystick, showing the y coordinate to move to
     * @param power_x This is where you put the x axis of the joystick, showing the x coordinate to move to
     */
    public void move(double power_y, double power_x){
        rightFront.setPower((-power_x + (-power_y)));
        rightBack.setPower(((power_x) + (-power_y)));
        leftFront.setPower(((power_x) + (-power_y)));
        leftBack.setPower((-power_x + (-power_y)));


    }

    /**
     * This turns the robot in place, assuming a perfect center of mass
     * @param turn_pow Put the power of turning in place, with positive to the right and negative to the left
     */
    public void turn(double turn_pow) {
        leftFront.setPower(turn_pow);
        rightFront.setPower(-turn_pow);
        leftBack.setPower(turn_pow);
        rightBack.setPower(-turn_pow);
    }
    //public void boost(double boost_pow){
     //   leftBoost.setPower(boost_pow);
    //    rightBoost.setPower(boost_pow);
    //}
    //public void launch(double launch_pow){
        //flyLaunch.setPower(launch_pow);
   // }

    //public void full_launch(double launch_pow) {
    //    launch(1);
    //    boost(1);
   // }
}
