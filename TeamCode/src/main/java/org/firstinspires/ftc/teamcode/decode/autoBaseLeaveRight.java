package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(preselectTeleOp = "BaseMecanumDriveUsingClass")
public class autoBaseLeaveRight extends LinearOpMode {
    basicFunctions robotStuff;


    @Override
    public void runOpMode() throws InterruptedException {
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();
        while (opModeIsActive()){
            robotStuff.move(-0.5, -0.5);
            timer.reset();
            while( timer.seconds()<= 1.0){
                telemetry.addData("Time",timer.seconds());
                telemetry.update();
            }
            robotStuff.move(0, 0);
            break;


        }
    }
}
