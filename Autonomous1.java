package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="MoveAuto",group="LinearOpMode")
public class Autonomous1 extends LinearOpMode{
   
     private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    // private DcMotor intakeMotor;
    // private DcMotor shootMotor;
    private void drive(double y, double x, double rx, long timeMs) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);

        frontLeftMotor.setPower((y + x + rx) / denominator);
        backLeftMotor.setPower((y - x + rx) / denominator);
        frontRightMotor.setPower((y - x - rx) / denominator);
        backRightMotor.setPower((y + x - rx) / denominator);

        sleep(timeMs);

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }
 @Override
    public void runOpMode() {
       
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeftMotor = hardwareMap.dcMotor.get("top_left");
        backLeftMotor = hardwareMap.dcMotor.get("bottom_left");
        frontRightMotor = hardwareMap.dcMotor.get("top_right");
        backRightMotor = hardwareMap.dcMotor.get("bottom_right");
        // intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        // shootMotor = hardwareMap.dcMotor.get("shootMotor");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // shootMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        drive(0.6, 0, 0, 1000);
        telemetry.addData("Status", "Running");
        telemetry.update();
       
        drive(0, 0, 2, 2000);
       
         while (opModeIsActive()) {
           
         }
       
    }
}