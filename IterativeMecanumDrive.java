package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Mecanum Drive (Shooter Encoder)", group = "OpMode")
public class IterativeMecanumDriveEncoder extends OpMode {

    private DcMotor frontLeftMotor;
    private VoltageSensor voltageSensor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor intakeMotor;
    private DcMotorEx shootMotor;
    private DcMotor geckoWheel;
   // private
    @Override
    public void init() {
        frontLeftMotor = hardwareMap.dcMotor.get("top_left");
        backLeftMotor = hardwareMap.dcMotor.get("bottom_left");
        frontRightMotor = hardwareMap.dcMotor.get("top_right");
        backRightMotor = hardwareMap.dcMotor.get("bottom_right");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        shootMotor = hardwareMap.get(DcMotorEx.class, "shootMotor");
        geckoWheel = hardwareMap.dcMotor.get("geckoWheel");
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //shootMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //geckoWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        // NEW: shooter holds a target speed using its encoder (cable must be plugged in)
        shootMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop() {

        //scaling system for shooting motor
        // NEW: instead of scaling power by voltage, we read the real shooter speed
        double target = RobotConstants.SHOOTER_TARGET_TPS;
        double velocity = shootMotor.getVelocity();
        boolean atSpeed = Math.abs(velocity - target) <= target * RobotConstants.SHOOTER_TOLERANCE;

        double speed;
        if (gamepad2.right_bumper) {
            speed = RobotConstants.SLOW_SPEED;
        } else {
            speed = RobotConstants.DRIVE_SPEED;
        }

        double y = -gamepad2.left_stick_y;
        double x = gamepad2.left_stick_x * RobotConstants.STRAFE_GAIN;
        double rx = gamepad2.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setPower(speed * frontLeftPower);
        backLeftMotor.setPower(speed * RobotConstants.BACK_LEFT_TRIM * backLeftPower);
        frontRightMotor.setPower(speed * frontRightPower);
        backRightMotor.setPower(speed * backRightPower);
        // intakeMotor.setPower(gamepad2.left_trigger);
        if (gamepad2.right_trigger > RobotConstants.TRIGGER_THRESHOLD) {
            shootMotor.setVelocity(target);
            // only feed once the shooter is up to speed (hold A to feed anyway)
            if (atSpeed || gamepad2.a) {
                geckoWheel.setPower(RobotConstants.GECKO_POWER);
            } else {
                geckoWheel.setPower(0.0);
            }
        }
        else {
            geckoWheel.setPower(0.0);
            shootMotor.setVelocity(0);
        }
        if (gamepad2.left_bumper) {
            intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            intakeMotor.setPower(RobotConstants.OUTTAKE_POWER);
        } else {
            intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            intakeMotor.setPower(gamepad2.left_trigger);
        }

        telemetry.addData("Battery V", "%.2f", voltageSensor.getVoltage());
        telemetry.addData("Shooter velocity", "%.0f", velocity);
        telemetry.addData("Shooter target", "%.0f", target);
        telemetry.addData("Shooter at speed", atSpeed);
    }

}