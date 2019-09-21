package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MoveTele", group="Linear OpMode")
public class MoveTele extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor motorRF = null;
    private DcMotor motorRB = null;
    private DcMotor motorLB = null;
    private DcMotor motorLF = null;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorRF  = hardwareMap.get(DcMotor.class, "motor_rf");
        motorLF = hardwareMap.get(DcMotor.class, "motor_lf");

        motorRB  = hardwareMap.get(DcMotor.class, "motor_rb");
        motorLB = hardwareMap.get(DcMotor.class, "motor_lb");

        motorRF.setDirection(DcMotor.Direction.FORWARD);
        motorRB.setDirection(DcMotor.Direction.FORWARD);

        motorLF.setDirection(DcMotor.Direction.REVERSE);
        motorLB.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("status: ", "initialization DONE");
        telemetry.update();
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double leftPower;
            double rightPower;

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            leftPower = drive+turn;
            rightPower = drive-turn;

            double m = Math.max(Math.max(Math.abs(leftPower), Math.abs(rightPower)),1.0);

            leftPower /= m;
            rightPower /= m;

            motorLF.setPower(leftPower);
            motorLB.setPower(leftPower);

            motorRF.setPower(rightPower);
            motorRB.setPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
