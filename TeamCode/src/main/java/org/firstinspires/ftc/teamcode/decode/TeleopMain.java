package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is our main driving class, a tele-op program using the basicFunctions class
 * The drive is mecanum based, with moving and turning abilities
 * This class also includes controls for the flywheel and launch servos
 */
@TeleOp(name = "Base Mecanum Drive Using Class", group = "Drive")
public class TeleopMain extends OpMode {

    /**
     * This is the basicFunctions controller class, called doStuff here
     */
    basicFunctions doStuff;

    boolean dpad_active = false;
    ElapsedTime timer = new ElapsedTime();

    public void boost_controls(ElapsedTime counter) {
        while (true ) {
            if (counter.seconds() <= 0.4) {
                if (counter.seconds() <= 0.20) {
                    doStuff.boost(1);
                } else {
                    doStuff.boost(0);
                }

            } else {
                return;
            }
        }
    }
    /**
     * Here the doStuff class is defined and the motors are initialized to the hardware map
     */
    public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);
        timer.reset();
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


        if (gamepad1.right_stick_x >= 0.2 || gamepad1.right_stick_x <= -0.2 ) {
            if(gamepad1.right_stick_y >=0.2 || gamepad1.right_stick_y <= -0.2){
                doStuff.move(-gamepad1.right_stick_y,-gamepad1.right_stick_x);
            }else{
                doStuff.move(0,-gamepad1.right_stick_x);
            }
        }else if(gamepad1.right_stick_y >=0.2 || gamepad1.right_stick_y <= -0.2){
            doStuff.move(-gamepad1.right_stick_y,0);
        }


        if (gamepad1.right_trigger>0) {
            doStuff.launch(gamepad1.right_trigger);
            telemetry.addData("Launch %: ",gamepad1.right_trigger);
        }else if (gamepad1.left_trigger>0){
            doStuff.launch(gamepad1.left_trigger/1.475);
            telemetry.addData("Launch %: ",gamepad1.left_trigger/1.475);
        }

        if (gamepad1.dpadUpWasPressed()){
            doStuff.launch(1);
            dpad_active = true;
        }else if(gamepad1.dpadLeftWasPressed()){
            dpad_active = true;
            doStuff.launch(1/1.475);
        }else if (gamepad1.dpadDownWasPressed()){
            doStuff.launch(0);
            dpad_active = false;
        }

        if (gamepad1.aWasPressed()){
            if (timer.seconds()<=0.4){
                if(timer.seconds()<=0.20){
                    doStuff.boost(1);
                }else{
                    doStuff.boost(0);

                }
            }else{
                timer.reset();
                boost_controls(timer);
            }
        }else if (!doStuff.launchStart&&!gamepad1.bWasPressed()&&!gamepad1.aWasPressed()&&gamepad1.right_trigger==0&&gamepad1.left_trigger==0&&!gamepad1.yWasPressed()&&!gamepad1.dpad_left&&!gamepad1.dpad_up&&!dpad_active){
            doStuff.launch(0);
        }

        if (gamepad1.bWasPressed()){
            if (timer.seconds() <= 0.4){
                telemetry.addData("Firing already happening!","");
            }else{
                timer.reset();
                boost_controls(timer);
                timer.reset();
                boost_controls(timer);
                timer.reset();
                boost_controls(timer);
            }
        }
        if (gamepad1.right_stick_x == 0.0 || gamepad1.right_stick_y == 0.0) {
            doStuff.move(0,0);
        }

        if (gamepad1.y){
            doStuff.launch(-0.4);
            doStuff.boost(-0.3);
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

