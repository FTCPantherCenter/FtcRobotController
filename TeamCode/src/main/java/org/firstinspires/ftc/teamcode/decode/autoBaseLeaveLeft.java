package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(preselectTeleOp = "BaseMecanumDriveUsingClass")
public class autoBaseLeaveLeft extends LinearOpMode {
    basicFunctions robotStuff;

    public void time_check(double start,double thereshold){
        if (getRuntime() - start >= thereshold){
            return;
        }else{
            time_check(start,thereshold);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            robotStuff.move(0.5, 0.5);
            double time = getRuntime();
            time_check(time,1);
            robotStuff.move(0, 0);
            stop();

        }
    }
}
