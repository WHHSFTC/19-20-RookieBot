package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="PrototypeServo", group="Linear Op Mode")
public class PoroTypeServo extends LinearOpMode {
        private ElapsedTime runTime = new ElapsedTime();

        private Servo servo = null;
        @Override
        public void runOpMode() {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            servo = hardwareMap.servo.get("servo");

            telemetry.addData("Status", "Initialization DONE");
            telemetry.update();

            runTime.reset();
            waitForStart();

            while (opModeIsActive()) {
                telemetry.addData("Status", "Running");
                telemetry.update();
                if (gamepad1.a) {
                    servo.setPosition(1.0);
                    telemetry.addData("Servo Position", 1.0);
                }
                if (gamepad1.b) {
                    servo.setPosition(.5);
                    telemetry.addData("Servo Position", .5);
                }
                if (gamepad1.x) {
                    servo.setPosition(0.0);
                    telemetry.addData("Servo Position", 0);
                }
                telemetry.update();
            }
            telemetry.addData("TOTAL RUNTIME", runTime.toString());
            telemetry.update();
        }
}
