package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This is a teleop implication of the basicFunctions class
 * We begin by declaring but not defining basic functions out of any methods, then define basic functions, named do Stuff here, in the init method
 * We also run the doStuff init method with the HardwareMap from the OpMode init method
 * The loop integrates the turn and move methods, with the left stick x controlling turning, and the right stick x and y controlling staffing
 * The move method in doStuff is used to stop the robot
 */

@TeleOp(name = "Base Mecanum Drive Using Class", group = "Drive")
public class BaseMecanumDriveUsingClass extends OpMode {
basicFunctions doStuff;

    /**
     * This is where the doStuff class is created/defined and the initialization of motors happens
     */
    public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);
    }


    @Override
    //basic movement and motor control
    /**
     * The main loop for the robot control. This includes launcher, intake, and drivetrain control. The left stick is used for turning, while the right stick is used for all strafing movement
     * The right bumper is used to launch the ball(s) in the launcher
     * The right trigger is used to activate intake(WIP)
     */
    public void loop() {

        if (gamepad1.left_stick_x != 0.0 ){
            //turning with left joystick horizontal
            doStuff.turn(gamepad1.left_stick_x);
        }


        if (gamepad1.right_stick_x != 0.0 || gamepad1.right_stick_y != 0.0) {
            doStuff.move(gamepad1.right_stick_y,gamepad1.right_stick_x);
        }

        //if (gamepad1.right_bumper){
        //  doStuff.full_launch(1);
       // }
        if (gamepad1.right_stick_x == 0.0 || gamepad1.right_stick_y == 0.0) {
            doStuff.move(0,0);
        }

    }
    //stop button pressed
    public void stop(){
        doStuff.move(0,0);
    }
}
