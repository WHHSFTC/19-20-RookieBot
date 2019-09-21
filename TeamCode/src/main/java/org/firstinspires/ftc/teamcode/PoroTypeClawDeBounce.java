package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="ClawTime", group="Linear Op Mode")
public class PoroTypeClawDeBounce extends LinearOpMode {

    private Servo arm = null;
    private Servo wrist = null;
    private Servo inner = null;
    private Servo outer = null;

    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        arm = hardwareMap.servo.get("arm");
        wrist = hardwareMap.servo.get("wrist");
        inner = hardwareMap.servo.get("inner");
        outer = hardwareMap.servo.get("outer");

        telemetry.addData("Status", "Initialization DONE");
        telemetry.update();

        double armPos = 0.0;
        double wristPos = 0.0;
        double innerPos = 0.0;
        double outerPos = 0.0;
        boolean in = false;
        boolean out = false;
        boolean pIn = false;
        boolean pOut = false;
        runTime.reset();
        waitForStart();

        while(opModeIsActive()) {
            if (gamepad1.a) wristPos = .33;
            if (gamepad1.y) wristPos = .66;
            if (gamepad1.b) wristPos = 0;
            if (gamepad1.x) wristPos = 1;
            if (gamepad1.right_bumper && !pOut) {
                out = !out;
                outer.setPosition(out ? 0 : .5);
            }
            if (gamepad1.left_bumper && !pIn) {
                in = !in;
                inner.setPosition(in ? .5 : 0);
            }
            pIn = gamepad1.left_bumper;
            pOut = gamepad1.right_bumper;
            if (gamepad1.dpad_down) armPos = .33;
            if (gamepad1.dpad_left) armPos = 1;
            if (gamepad1.dpad_right) armPos = 0;
            if (gamepad1.dpad_up) armPos = .66;
            arm.setPosition(armPos);
            wrist.setPosition(wristPos);
            inner.setPosition(innerPos);
            outer.setPosition(outerPos);
            telemetry.addData("Runtime", runTime.toString());
            telemetry.addData("Servo Positions", "ARM: (%.2f) WRIST: (%.2f) INNER: (%.2f) OUTER: (%.2f)", armPos, wristPos, innerPos, outerPos);
            telemetry.update();
        }
    }
}