package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This is our main driving class, a tele-op program using the basicFunctions class
 * The drive is mecanum based, with moving and turning abilities
 * This class also includes controls for the flywheel and launch servos
 */
@TeleOp(name = "Base Mecanum Drive Using Class", group = "Drive")
public class BaseMecanumDriveUsingClass extends OpMode {

    /**
     * This is the basicFunctions controller class, called doStuff here
     */
    basicFunctions doStuff;

    /**
     * Here the doStuff class is defined and the motors are initialized to the hardware map
     */
    public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);
    }

    /**
     * This is the main loop of the class with all needed controls
     * It includes mecanum movement, turning, fly wheel, and boost servo controls
     */
    @Override
    public void loop() {

        if (gamepad1.left_stick_x != 0.0 ){
            //turning with left joystick horizontal
            doStuff.turn(gamepad1.left_stick_x);
        }


        if (gamepad1.right_stick_x >= 0.1 || gamepad1.right_stick_x <= -0.1 ) {
            if(gamepad1.right_stick_y >=0.1 || gamepad1.right_stick_y <= -0.1){
                doStuff.move(-gamepad1.right_stick_y,-gamepad1.right_stick_x);
            }else{
                doStuff.move(0,-gamepad1.right_stick_x);
            }
        }else if(gamepad1.right_stick_y >=0.1 || gamepad1.right_stick_y <= -0.1){
            doStuff.move(-gamepad1.right_stick_y,0);
        }

        if (gamepad1.x){
            doStuff.launch_binary(true);
        }
        if (gamepad1.right_trigger>0) {
            doStuff.launch(gamepad1.right_trigger);
            telemetry.addData("Launch %: ",gamepad1.right_trigger);
        }else if (gamepad1.left_trigger>0){
            doStuff.launch(gamepad1.left_trigger/1.4);
            telemetry.addData("Launch %: ",gamepad1.left_trigger/1.4);
        }else if (doStuff.launchStart){
            doStuff.launch(0.7);
        }

        if (gamepad1.a){
            doStuff.boost_left(1);
            doStuff.boost_right(1);
        }else{
            doStuff.boost_left(0);
            doStuff.boost_right(0);
        }
        if (gamepad1.right_stick_x == 0.0 || gamepad1.right_stick_y == 0.0) {
            doStuff.move(0,0);
        }

    }
    //stop button pressed

    /**
     * This stops all motors when the stop button is pressed
     */
    public void stop(){
        doStuff.move(0,0);
        doStuff.launch(0);
        doStuff.boost(0);
    }
}
