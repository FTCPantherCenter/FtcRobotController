package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.pedropathing.ftc.localization.localizers.TwoWheelLocalizer;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Vector;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(8.1);
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .xVelocity(340.110849)
            .rightFrontMotorName("fr_drive")
            .leftFrontMotorName("fl_drive")
            .leftRearMotorName("bl_drive")
            .rightRearMotorName("br_drive")
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .maxPower(1);

    public static DriveEncoderConstants localizerConstants = new DriveEncoderConstants()
            .turnTicksToInches(0.24648)
            .strafeTicksToInches(0.012264)
            .forwardTicksToInches(0.1676)
            .robotWidth(17.5)
            .robotLength(14)
            .rightFrontMotorName("fr_drive")
            .leftFrontMotorName("fl_drive")
            .leftRearMotorName("bl_drive")
            .rightRearMotorName("br_drive")
            .rightFrontEncoderDirection(Encoder.REVERSE)
            .leftFrontEncoderDirection(Encoder.REVERSE)
            .leftRearEncoderDirection(Encoder.FORWARD)
            .rightRearEncoderDirection(Encoder.FORWARD );


    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .driveEncoderLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();


}}
