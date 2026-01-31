package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is an auto scoring class for the close starting position, for blue team
 */
@Autonomous(preselectTeleOp = "BaseMecanumDriveUsingClass")
public class autoScoreCloseBlue extends OpMode {
    /**
     * This creates the initial doStuff controller from the basicFunctions class
     */
    basicFunctions doStuff;
    /**
     * This creates the timer to control the timing of the autonomous period
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
     * This is the actual movement code for the autonomous period
     * First the robot moves forward for a third of a second, around to the end of the large triangle
     * Then the robot speeds the motor up and fires the loaded balls
     * Afterwards, the robot leaves the launch zone by moving right
     */
    @Override
    public void start() {
        super.start();
        doStuff.move(-0.6,0);
        timer.reset();
        while(timer.seconds()<=0.8){
            telemetry.addData("Timer: ",0.3-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.move(0,0);
        doStuff.launch(0.70);
        timer.reset();
        while(timer.seconds()<=4){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(1);
        timer.reset();
        while(timer.seconds()<=0.1){
            telemetry.addData("Timer: ",0.25-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(0);
        timer.reset();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(1);
        timer.reset();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(0);
        doStuff.move(0.5,1);
        timer.reset();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.move(0,0);
        while(timer.seconds()<=3){
            telemetry.addData("Timer: ",3-timer.seconds());
            telemetry.clearAll();
        }
        doStuff.move(-0.5,-1);
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        doStuff.move(0,0);
        telemetry.clearAll();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(1);
        timer.reset();
        while(timer.seconds()<=0.25){
            telemetry.addData("Timer: ",0.25-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(0);
        timer.reset();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(1);
        timer.reset();
        while(timer.seconds()<=1){
            telemetry.addData("Timer: ",1-timer.seconds());
            telemetry.clearAll();
        }
        telemetry.clearAll();
        doStuff.boost(0);
        doStuff.move(0,0);
        telemetry.addData("Autonomous Complete","");
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
     * This stops all motors from moving, and servos
     */
    @Override
    public void stop() {
        super.stop();
        doStuff.move(0,0);
        doStuff.launch(0);
        doStuff.boost(0);
    }
}
