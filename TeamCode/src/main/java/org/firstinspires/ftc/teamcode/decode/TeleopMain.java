package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This is our main driving class, a tele-op program using the basicFunctions class
 * The drive is mecanum based, with moving and turning abilities
 * This class also includes controls for the flywheel and launch servos
 */
@TeleOp(name = "TeleopMain", group = "Drive")
public class TeleopMain extends OpMode {

    /**
     * This is the basicFunctions controller class, called doStuff here
     */
    basicFunctions doStuff;
    double shot_Counter = 0;
    boolean do_shot_counter = false;
    boolean balls_loaded = false;
    double dpad_mode = 0;
    boolean dpad_active = false;
    ElapsedTime timer = new ElapsedTime();
    ElapsedTime sense_timer = new ElapsedTime();
    public void boost_controls(ElapsedTime counter,double cooldown) {
        while (true ) {
            if (counter.seconds() <= cooldown) {
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
    public double get_average(DcMotor motor1,DcMotor motor2, DcMotor motor3, DcMotor motor4){
        return (Math.abs(motor1.getPower()) + Math.abs(motor2.getPower()) + Math.abs(motor3.getPower()) + Math.abs(motor4.getPower()))/4;
    }
    /**
     * Here the doStuff class is defined and the motors are initialized to the hardware map
     */
    public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);
        timer.reset();
        sense_timer.reset();
    }

    /**
     * This is the main loop of the class with all needed controls
     * It includes mecanum movement, turning, fly wheel, and boost servo controls
     */
    @Override
    public void loop() {

        double left_y = Math.abs(gamepad1.left_stick_y) < 0.25 ? 0.0 : gamepad1.left_stick_y;
        double left_x = Math.abs(gamepad1.left_stick_x) < 0.25 ? 0.0 : gamepad1.left_stick_x;
        double right_x = Math.abs(gamepad1.right_stick_x) < 0.25 ? 0.0 : gamepad1.right_stick_x;


        //doStuff.move(left_y,left_x,right_x);


        if (gamepad1.rightBumperWasPressed()){
            if(do_shot_counter){
                do_shot_counter = false;
            }else {
                do_shot_counter = true;
                shot_Counter = 0;
            }
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
            dpad_mode = 1;
        }else if(gamepad1.dpadLeftWasPressed()){
            dpad_active = true;
            doStuff.launch(1/1.52);
            dpad_mode = 2;
        }else if (gamepad1.dpadDownWasPressed()){
            doStuff.launch(0);
            dpad_mode = 10;
            dpad_active = false;
        }else if (gamepad1.dpadRightWasPressed()){
            dpad_mode = 4;
            doStuff.launch(0.3);
            dpad_active = true;
        }

        telemetry.addData("Fly Power : ", doStuff.flyLaunch.getPower());


        if (gamepad1.aWasPressed()){
            if (timer.seconds()<=0.4){
                if(timer.seconds()<=0.20){
                    doStuff.boost(1);
                }else{
                    doStuff.boost(0);

                }
            }else{
                timer.reset();
                boost_controls(timer,0.4);
            }
        }else if (!balls_loaded&&!doStuff.launchStart&&!gamepad1.bWasPressed()&&!gamepad1.aWasPressed()&&gamepad1.right_trigger==0&&gamepad1.left_trigger==0&&!gamepad1.yWasPressed()&&!gamepad1.dpad_left&&!gamepad1.dpad_up&&!dpad_active&&!gamepad1.dpad_right ){
            doStuff.launch(0);
        }


        if (gamepad1.xWasPressed()){
            telemetry.addData("X on Gamepad 1 Was Pressed","");
            if (timer.seconds() <= 1.2){
                telemetry.addData("Firing already happening!","");
            }else{
                timer.reset();
                boost_controls(timer,1.2);
                timer.reset();
                boost_controls(timer,1.2);
                timer.reset();
                boost_controls(timer,1.2);
            }
        }
        if (left_x == 0.0 || left_y == 0.0||right_x == 0.0) {
            doStuff.move(0,0,0);
        }

        if (gamepad1.y){
            doStuff.launch(-0.4);
            doStuff.boost(-0.3);
        }

        telemetry.addData("Average Motor Power: ",get_average(doStuff.leftBack, doStuff.leftFront,doStuff.rightBack, doStuff.rightFront));

        if (do_shot_counter){
            telemetry.addData("Shot Counter Active","");
            if (shot_Counter == 3){
                doStuff.launch(0);
                shot_Counter = 0;
            }
            telemetry.addData("Shot Count: ",shot_Counter);
        }else {
            telemetry.addData("Shot Counter Deactivated","");
        }

        if(doStuff.get_distance(DistanceUnit.CM)>=7){
            sense_timer.reset();
            doStuff.launch(1/1.52);
        }
        if(sense_timer.seconds()<=0.5){
            balls_loaded = true;
        }else{
            balls_loaded = false;
        }

        if (balls_loaded){
            doStuff.launch(1/1.52);
        }
        doStuff.driveFieldRelative(left_y,left_x,right_x);
    }
    //stop button pressed

    /**
     * This stops all motors when the stop button is pressed
     */
    public void stop(){
        doStuff.move(0,0,0);
        doStuff.launch(0);
        doStuff.boost(0);
    }
}

