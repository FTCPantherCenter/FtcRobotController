package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class autoBaseLeave extends OpMode {
basicFunctions funct = new basicFunctions();

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;




    //initialization
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "fl_drive");
        leftBack = hardwareMap.get(DcMotor.class, "bl_drive");
        rightFront = hardwareMap.get(DcMotor.class, "fr_drive");
        rightBack = hardwareMap.get(DcMotor.class, "br_drive");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    public void start() {
        funct.full_move(1);
        ElapsedTime timer = new ElapsedTime();
        while (timer.milliseconds() < 200){
        }
        funct.full_move(0);
    }
    public void loop(){

    }
}
