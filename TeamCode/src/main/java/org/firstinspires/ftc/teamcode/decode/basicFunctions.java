package org.firstinspires.ftc.teamcode.decode;



import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * This is my main class for controlling the robot
 * In this class are the motor powers are set and other classes control the robot indirectly using this
 */
public class basicFunctions{
    /**
     * This is the constructor
     * It defines all the motors as null, and creates a new launchStart boolean for a later method
     * The variables are defined in lower camel case
     */
    public void basicFunctions() {
        IMU imu = null;
        DcMotor leftFront = null;
        DcMotor rightFront = null;
        DcMotor leftBack = null;
        DcMotor rightBack = null;
        DcMotorEx flyLaunch = null;
        CRServo leftBoost = null;
        CRServo rightBoost = null;
        try {
          //  DistanceSensor distanceSensor = null;
        } catch (Exception e) {
          //  boolean sensorFail = true;
          //  DistanceSensor distanceSensor = null;
        }

     //   boolean launchStart = false;
    }


    IMU imu = null;

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotorEx flyLaunch = null;
    CRServo leftBoost = null;
    CRServo rightBoost = null;
    boolean launchStart = false;
    /*
    public boolean try_sensor(){
        try {
           // DistanceSensor distanceSensor = null;
        } catch (Exception e) {
           // boolean sensorFail = true;
           // return false;
        }
       // return true;
    }

     */

   // DistanceSensor distanceSensor = null;
    /**
     * This is the init method meant to be run in the init method of the OpMode, defining all the motors and settings
     * @param hwMap This is the parameter which the HardwareMap created by the init method should be passed through
     */


    public void init(HardwareMap hwMap){
        //boolean failed =try_sensor();
        leftFront = hwMap.get(DcMotor.class, "fl_drive");
        leftBack = hwMap.get(DcMotor.class, "bl_drive");
        rightFront = hwMap.get(DcMotor.class, "fr_drive");
        rightBack = hwMap.get(DcMotor.class, "br_drive");

        flyLaunch = hwMap.get(DcMotorEx.class,"fly_wheel");
         leftBoost = hwMap.get(CRServo.class,"left_boost");
         rightBoost = hwMap.get(CRServo.class,"right_boost");
         imu = hwMap.get(IMU.class,"imu");


        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        flyLaunch.setDirection((DcMotorSimple.Direction.FORWARD));

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyLaunch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /*
        if(!failed){
         //   DistanceSensor distanceSensor = hwMap.get(DistanceSensor.class,"distance_sensor");
        }

         */
        //DistanceSensor distanceSensor = hwMap.get(DistanceSensor.class,"distance_sensor");

        RevHubOrientationOnRobot revOrientation =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(revOrientation));
    }

    /*
    public double get_distance(DistanceUnit distanceUnit){
       // return distanceSensor.getDistance(distanceUnit);
    }

     */

    public double get_velocity(DcMotorEx motor,AngleUnit angleUnit){
        return motor.getVelocity(angleUnit);
    }
    public void driveFieldRelative(double forward, double right, double rotate){
        double robotAngle = getHeading(AngleUnit.RADIANS);

        //this part below turns from cartesian to polar
        //cartesian we know from forward and right, we use trig to get the length to travel and the angle from current robot positon
        //this uses the tan(theta) = opposite/adjacent to get the radians of the angle formed between current robot looking position and goal position
        double theta = Math.atan2(forward,right);
        //gets length of the hypotenuse, or "strength" to move forward, using pythagorean thereom
        double r = Math.hypot(forward,right);


        //gets difference in angles
        theta = AngleUnit.normalizeRadians(theta-robotAngle);


        //reverses the trig
        double newForward = r * Math.sin(theta);
        double newRight = r * Math.cos(theta);

        //moves the robots to the new coordinates passing in rotation offset
        move(newForward,newRight,rotate);
    }

    /**
     * This is the method for the robots movement, using a basic mecanum drive
     * @param power_y This is the speed at which the robot will move forward or backwards at, with a positive value moving the robot forward
     * @param power_x This is the speed at which the robot will move left or right at, with a positive value moving the robot right
     */
    public void move(double power_y, double power_x,double turn_pow){
        rightFront.setPower((-power_x + (-power_y))-(turn_pow));
        rightBack.setPower(((power_x) + (-power_y))-(turn_pow));
        leftFront.setPower(((power_x) + (-power_y))+(turn_pow));
        leftBack.setPower((-power_x + (-power_y))+(turn_pow));


    }


    public double getHeading(AngleUnit angleUnit){
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }
    /**
     * This is the method used to turn the robot on its own axis
     * @param turn_pow This is the speed, or power, or the turn, with a positive value turning the robot right
     */
    public void turn(double turn_pow) {
        rightFront.setPower(turn_pow);
        rightBack.setPower(-turn_pow);
        leftFront.setPower(turn_pow);
        leftBack.setPower(-turn_pow);
    }

    /**
     * This is the method used to power the left launch servo, an extension of CRServo.setPower, reversing the power
     * @param boost_pow This value is inverted and used to launch the ball into the flywheel, should be kept consistent between servos
     */
    public void boost_left(double boost_pow){
       leftBoost.setPower(-boost_pow);
    }

    /**
     * This is the method used to power the right launch servo, used as an extension of CRServo.setpower
     * @param boost_pow This is the power at which the servo pushes the ball into the flywheel, should be consistent between servos
     */
    public void boost_right(double boost_pow){
        rightBoost.setPower(boost_pow);
    }

    /**
     * This method is used to power the flywheel
     * @param launch_pow This is the speed that the flywheel moves at, out of 1
     */
    public void launch(double launch_pow){
        flyLaunch.setPower(launch_pow);
    }

    /**
     * This is a method using both of the servo powering methods to power both simultaneously
     * @param boost_pow This should be the power that the servos push the ball into the flywheel, almost always 1
     */
    public void boost(double boost_pow){
        boost_right(boost_pow);
        boost_left(boost_pow);
    }

    /**
     * This is a method that is used to alternate a variable between true and false when pressed
     * It is used for a binary,on or off flywheel control
     * @param doit This should always be true when calling the method, a toggle for it
     */
    public void launch_binary(boolean doit){
        if (doit) {

            if (!launchStart) {
                boolean launchStart = true;
            } else {
                boolean launchStart = false;
            }
        }
    }

    /**
     * This is used to singly control each drivetrain motor, used in the Debug Class
     * @param motorC This value correlates to the motor being powered as follows: 0- Front Right, 1- Back Right, 2- Front Left, 3- Left Back.
     *               All other values make the motors unpowered
     */
    public void single_control(int motorC){
        if(motorC==0){
            rightFront.setPower(1);
        }else if(motorC==1){
            rightBack.setPower(1);
        }else if(motorC==2){
            leftFront.setPower(1);
        }else if(motorC==3){
            leftBack.setPower(1);
        }else{
            rightFront.setPower(0);
            rightBack.setPower(0);
            leftFront.setPower(0);
            leftBack.setPower(0);
        }

    }
}
