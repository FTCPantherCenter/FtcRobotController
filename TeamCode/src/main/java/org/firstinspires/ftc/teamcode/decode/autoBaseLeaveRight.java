package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This class moves the robot forward and right, for a basic autonomous score
 */
@Autonomous(preselectTeleOp = "BaseMecanumDriveUsingClass")
public class autoBaseLeaveRight extends OpMode {

    /**
     * This is my controller class with all basic movements for the bot.
     */
    basicFunctions robotStuff;
    /**
     * The timer is used to move the motors for controlled periods of time
     */
    ElapsedTime timer = new ElapsedTime();

    /**
     * This init simply creates the basicFunctions class and uses it to init the hardware map
     */
    public void init(){
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);

    }

    /**
     * This is the actual movement part of autonomous, moving the robot right and slight less forward, for 0.35 seconds
     */
    @Override
    public void start() {
        super.start();
        robotStuff.move(0.25,0.3);
        timer.reset();
        if(timer.seconds()<=0.35){
            telemetry.addData("Timer: ",0.25-timer.seconds());
        }
        robotStuff.move(0,0);
        telemetry.addData("Autonomous Complete","");
        stop();
    }

    /**
     * Loop is required for class
     */
    @Override
    public void loop() {
        stop();
    }

    /**
     * Stops all motors from moving completely
     */
    @Override
    public void stop() {
        super.stop();
        robotStuff.move(0,0);
        robotStuff.launch(0);
        robotStuff.boost(0);
    }
}
