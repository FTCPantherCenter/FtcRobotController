//teleop code, right joystick for all movements, attempt to combine forward movement and strafing up and down on joystick for direct forward and back and sides for strafing
//Check FullStop
//old code


package org.firstinspires.ftc.teamcode.intoTheDeep.teleop;



import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
//Asd.
public class teleopControlsV5 extends OpMode {





    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;

    double sensitivity = 1.00;



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

    sensitivity = 1.00;
    }


     public void fullStop() {
         //full stop function
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        leftFront.setPower(0);
    }
    public void startLeft(){
        leftBack.setPower(sensitivity);
        leftFront.setPower(-sensitivity);
        rightFront.setPower(sensitivity);
        rightBack.setPower(-sensitivity);
    }
    public void startRight(){
        leftBack.setPower(-sensitivity);
        leftFront.setPower(sensitivity);
        rightFront.setPower(-sensitivity);
        rightBack.setPower(sensitivity);
    }

    @Override
    //basic movement and motor control
    public void loop() {



        //make sensitivity change loop with side triggers


        if (gamepad1.left_bumper){

            if (sensitivity>=0.10){
                sensitivity = sensitivity - 0.05;
            }
        }
        if (gamepad1.left_trigger != 0.0){

            if (sensitivity<=0.95){
                sensitivity = sensitivity + 0.05;
            }
        }

        if (gamepad1.right_trigger != 0.0){
            fullStop();
            leftBack.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
            leftFront.setPower(0);
        }

        if (gamepad1.left_stick_x != 0.0 ){
            //turning with left joystick horizontal
            leftFront.setPower(gamepad1.left_stick_x * sensitivity);
            rightFront.setPower(-gamepad1.left_stick_x * sensitivity );
            leftBack.setPower(gamepad1.left_stick_x * sensitivity );
            rightBack.setPower(-gamepad1.left_stick_x * sensitivity );
        }else {
            if (gamepad1.right_stick_y == 0){
                if (gamepad1.right_stick_x == 0){
                    fullStop();
                    //if nothing pressed stop
                    rightFront.setPower(0);
                    rightBack.setPower(0);
                    leftFront.setPower(0);
                    leftBack.setPower(0);
                }
            }
        }

        if (gamepad1.right_stick_x != 0.0) {
            if (gamepad1.right_stick_y != 0.0) {
                //both ways are pushed
            rightFront.setPower((-gamepad1.right_stick_x + (-gamepad1.right_stick_y)) * sensitivity );
            rightBack.setPower(((gamepad1.right_stick_x) + (-gamepad1.right_stick_y)) * sensitivity );
            leftFront.setPower(((gamepad1.right_stick_x) + (-gamepad1.right_stick_y)) * sensitivity );
            leftBack.setPower((-gamepad1.right_stick_x + (-gamepad1.right_stick_y))* sensitivity );
            } else {
                //side to side only
            rightFront.setPower(-gamepad1.right_stick_x * sensitivity );
            rightBack.setPower(gamepad1.right_stick_x * sensitivity );
            leftFront.setPower(gamepad1.right_stick_x * sensitivity );
            leftBack.setPower(-gamepad1.right_stick_x * sensitivity );
        }
        } else {
            if (gamepad1.right_stick_y != 0.0) {
                //only up or down
                rightFront.setPower(-gamepad1.right_stick_y * sensitivity );
                leftFront.setPower(-gamepad1.right_stick_y * sensitivity );
                leftBack.setPower(-gamepad1.right_stick_y * sensitivity );
                rightBack.setPower(-gamepad1.right_stick_y * sensitivity );
            } else{
                if (gamepad1.left_stick_x == 0){
                    fullStop();
                    leftBack.setPower(0);
                    leftFront.setPower(0);
                    rightFront.setPower(0);
                    rightBack.setPower(0);
                }
            }
        }

    }
    //stop button pressed
    public void stop(){
        sensitivity = 0.00;
        fullStop();
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        fullStop();
    }

}

//basic strafing and turning
//requires equal weight and mecanum wheels
