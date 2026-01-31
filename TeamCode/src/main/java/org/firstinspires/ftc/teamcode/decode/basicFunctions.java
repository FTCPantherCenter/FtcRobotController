package org.firstinspires.ftc.teamcode.decode;



import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


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
        DcMotor leftFront = null;
        DcMotor rightFront = null;
        DcMotor leftBack = null;
        DcMotor rightBack = null;
        DcMotor flyLaunch = null;
        CRServo leftBoost = null;
        CRServo rightBoost = null;
        boolean launchStart = false;
    }


    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor flyLaunch = null;
    CRServo leftBoost = null;
    CRServo rightBoost = null;
    boolean launchStart = false;

    /**
     * This is the init method meant to be run in the init method of the OpMode, defining all the motors and settings
     * @param hwMap This is the parameter which the HardwareMap created by the init method should be passed through
     */
    public void init(HardwareMap hwMap){
        leftFront = hwMap.get(DcMotor.class, "fl_drive");
        leftBack = hwMap.get(DcMotor.class, "bl_drive");
        rightFront = hwMap.get(DcMotor.class, "fr_drive");
        rightBack = hwMap.get(DcMotor.class, "br_drive");

        flyLaunch = hwMap.get(DcMotor.class,"fly_wheel");
         leftBoost = hwMap.get(CRServo.class,"left_boost");
         rightBoost = hwMap.get(CRServo.class,"right_boost");


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
    }

    /**
     * This is the method for the robots movement, using a basic mecanum drive
     * @param power_y This is the speed at which the robot will move forward or backwards at, with a positive value moving the robot forward
     * @param power_x This is the speed at which the robot will move left or right at, with a positive value moving the robot right
     */
    public void move(double power_y, double power_x){
        rightFront.setPower((-power_x + (-power_y)));
        rightBack.setPower(((power_x) + (-power_y)));
        leftFront.setPower(((power_x) + (-power_y)));
        leftBack.setPower((-power_x + (-power_y)));


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
