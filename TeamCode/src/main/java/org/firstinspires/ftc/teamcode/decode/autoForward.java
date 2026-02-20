package org.firstinspires.ftc.teamcode.decode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This class moves the robot forward directly for a basic autonomous score
 */
@Autonomous(preselectTeleOp = "BaseMecanumDriveUsingClass")
public class autoForward extends OpMode {
    /**
     * This is my controller class for the robot
     */
    basicFunctions robotStuff;
    /**
     * This is the timer I use for controlling the autonomous movements
     */
    ElapsedTime timer = new ElapsedTime();

    /**
     * This creates robotStuff from the basicFunctions class, and initializes the hardware map using robotStuff
     */
    public void init(){
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);

    }

    /**
     * This is where the movement code is, moves the robot forward at full speed for a quarter of a second
     */
    @Override
    public void start() {
        super.start();
        robotStuff.move(1,0,0);
        timer.reset();
        if(timer.seconds()<=0.25){
            telemetry.addData("Timer: ",0.25-timer.seconds());
        }
        robotStuff.move(0,0,0);
        telemetry.addData("Autonomous Complete","");
        stop();
    }

    /**
     * Required loop method by OpMode
     */
    @Override
    public void loop() {
        stop();
    }

    /**
     * This stops all motors from moving completely
     */
    @Override
    public void stop() {
        super.stop();
        robotStuff.move(0,0,0);
        robotStuff.launch(0);
        robotStuff.boost(0);
    }
    }

