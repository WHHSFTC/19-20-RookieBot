package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="SpinnyTestTime", group="Linear Op Mode")
public class PoroTypeSpin extends LinearOpMode {
    private ElapsedTime runTime = new ElapsedTime();

    private CRServo crs = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        crs = hardwareMap.crservo.get("crs");

        telemetry.addData("Status", "Initialization DONE");
        telemetry.update();

        runTime.reset();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            double power = -gamepad1.right_stick_y/2.0;
            power = Math.max(-.5, Math.min(0.5, power));
            crs.setPower(power);

            telemetry.addData("Runtime", runTime.toString());
            telemetry.addData("Servo Power", power);
            telemetry.update();
        }
    }
}
