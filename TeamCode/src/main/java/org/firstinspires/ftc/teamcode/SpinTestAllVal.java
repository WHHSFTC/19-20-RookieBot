package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TestingAllValsFromSpin", group="Linear Op Mode")
public class SpinTestAllVal extends LinearOpMode {
    private ElapsedTime runTime = new ElapsedTime();

    private CRServo crs = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        crs = hardwareMap.crservo.get("crs");

        telemetry.addData("Status", "Initialization DONE");
        telemetry.update();

        double power = 0;
        int i = 0;
        runTime.reset();
        waitForStart();
        boolean pressed = false;
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            if (gamepad1.a) {
                pressed = !pressed;
            }
            if (gamepad1.x) {
                power = -1.0;
            }
            if (gamepad1.y) {
                power = 0;
            }
            power = Math.max(-.5, Math.min(.5, power));
            crs.setPower(power);
            telemetry.addData("RunTime", runTime.toString());
            telemetry.addData("Servo Power", power);
            telemetry.update();
            if (i++ >= 1000 && pressed) {
                power += .05;
                i = 0;
            }
        }
        telemetry.addData("TOTAL RUNTIME", runTime.toString());
        runTime.reset();
        telemetry.update();
    }
}