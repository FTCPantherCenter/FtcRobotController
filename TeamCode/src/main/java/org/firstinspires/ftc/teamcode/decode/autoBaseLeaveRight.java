package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autoBaseLeaveRight extends LinearOpMode {

    basicFunctions robotStuff;

    @Override
    public void runOpMode() throws InterruptedException {
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            robotStuff.move(1, 0.5);
            sleep(500);
            robotStuff.move(0, 0);
            stop();

        }
    }
}
