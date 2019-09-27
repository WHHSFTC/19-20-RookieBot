package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="FlyWheelTest", group="Linear Op Mode")
public class FlyWheelTest extends LinearOpMode {

    private ElapsedTime runTime = new ElapsedTime();

    private CRServo leftServo = null;
    private CRServo rightServo = null;

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftServo = hardwareMap.crservo.get("left");
        rightServo = hardwareMap.crservo.get("right");

        telemetry.addData("Status", "Initialization DONE");
        telemetry.update();

        runTime.reset();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Running", "TRUE");
            telemetry.update();
            if (gamepad1.a) {
                leftServo.setPower(.5);
                rightServo.setPower(-.5);
                telemetry.addData("Servor Power", "left (%.2f), right (%.2f)", .5, -.5);
            }
            if (gamepad1.b) {
                leftServo.setPower(0);
                rightServo.setPower(0);
                telemetry.addData("Servor Power", "left (%.2f), right (%.2f)", 0, 0);
            }
            if (gamepad1.y) {
                leftServo.setPower(-.5);
                rightServo.setPower(.5);
                telemetry.addData("Servor Power", "left (%.2f), right (%.2f)", -.5, .5);
            }
            telemetry.addData("Status", "Run Time: " + runTime.toString());
            telemetry.update();
        }
    }
}
