package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class DebugClass extends OpMode {
    basicFunctions controller;
    public void init(){
        controller = new basicFunctions();
        controller.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.y){
            controller.single_control(0);
        }else if(gamepad1.x){
            controller.single_control(1);
        }else if(gamepad1.b){
            controller.single_control(2);
        }else if(gamepad1.a){
            controller.single_control(3);
        }else{
            controller.single_control(4);
        }
    }
}

