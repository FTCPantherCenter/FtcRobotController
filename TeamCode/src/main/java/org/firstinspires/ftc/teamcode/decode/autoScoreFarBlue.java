package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is an auto program for the close starting position, shooting the balls and leaving the launch zone, for blue team
 */
@Autonomous
public class autoScoreFarBlue extends OpMode{
    /**
     * This is the basic functions controller class, called doStuff here
     */
    basicFunctions doStuff;

    /**
     * This is a timer used to control the timing of the autonomous program
     */
    ElapsedTime timer = new ElapsedTime();

    /**
     * This defines the doStuff controller and initializes the motors to the hardware map
     */
    @Override
    public void init() {
        doStuff = new basicFunctions();
        doStuff.init(hardwareMap);

    }

    /**
     * This is where the movement code for the program is
     * What this program does is it first moves the robot forward and slightly to the left, for 0.5 seconds
     * Afterwards the robot turns a small amount for a quarter of a second
     * Next the robot speeds up the flywheel, and then fires the loaded balls by powering the servos
     * After that, the robot turns opposite of what it previously did and moves slightly back, but not fully to the wall
     */
    @Override
    public void start() {
        super.start();
        doStuff.move(-1,0.1);
        timer.reset();
        if(timer.seconds()<=0.5){
            telemetry.addData("Time Left",0.5-timer.seconds());
        }
        doStuff.move(0,0);
        doStuff.turn(-1);
        timer.reset();
        if(timer.seconds()<=0.25){
            telemetry.addData("Time Left",0.25-timer.seconds());
        }
        doStuff.turn(0);
        doStuff.launch(0.7);
        timer.reset();
        if(timer.seconds()<=1){
            telemetry.addData("Time Left",1-timer.seconds());
        }
        doStuff.boost(1);
        timer.reset();
        if(timer.seconds()<=1){
            telemetry.addData("Time Left",1-timer.seconds());
        }
        doStuff.boost(0);
        doStuff.launch(0);
        doStuff.turn(1);
        timer.reset();
        if(timer.seconds()<=0.25){
            telemetry.addData("Time Left",0.25-timer.seconds());
        }
        doStuff.turn(0);
        doStuff.move(0.5,-0.1);
        if(timer.seconds()<=0.5){
            telemetry.addData("Time Left",0.5-timer.seconds());
        }
        doStuff.move(0,0);
        doStuff.launch(0);
        doStuff.boost(0);
        telemetry.addData("Autonomous Completed","");
        stop();
    }

    /**
     * This is the loop method required by the Op Mode
     */
    @Override
    public void loop() {
        stop();
    }

    /**
     * This stops all motors and servos
     */
    @Override
    public void stop() {
        super.stop();
        doStuff.move(0,0);
        doStuff.launch(0);
        doStuff.boost(0);
    }
}
