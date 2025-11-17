package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.security.KeyStore;

@Autonomous
public class autoBaseLeave extends LinearOpMode {

    basicFunctions robotStuff;


    @Override
    public void runOpMode() throws InterruptedException {
        robotStuff = new basicFunctions();
        robotStuff.init(hardwareMap);
        waitForStart();
        robotStuff.move(1,0.5);
        sleep(500);
        robotStuff.move(0,0.5);
    }
}
