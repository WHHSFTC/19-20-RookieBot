package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name ="MotorSpoolTest", group="Linear Op Mode")
//@Disabled
public class MotorSpoolTest extends LinearOpMode {

    private ElapsedTime runTime = new ElapsedTime();

    private DcMotor yi = null;
    private DcMotor er = null;
    private DcMotor san = null;
    private DcMotor si = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        yi = hardwareMap.get(DcMotor.class, "1"); //this will be port 0
        er = hardwareMap.get(DcMotor.class, "2"); //this will be port 1
        san = hardwareMap.get(DcMotor.class, "3");//this will be port 2
        si = hardwareMap.get(DcMotor.class, "4"); //this will be port 3

        yi.setDirection(DcMotor.Direction.FORWARD);
        er.setDirection(DcMotor.Direction.FORWARD);
        san.setDirection(DcMotor.Direction.FORWARD);
        si.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialization DONE");
        telemetry.update();

        runTime.reset();
        waitForStart();

        while (opModeIsActive()) {
            double power;
            double m;

            power = -gamepad1.right_stick_y;
            m = Math.max(Math.abs(power), 1.0);

            power /= m;

            yi.setPower(power);
            er.setPower(power);
            san.setPower(power);
            si.setPower(power);

            telemetry.addData("Status", "Run Time: " + runTime.toString());
            telemetry.addData("Motor Power", "Power: (%.2f)", power);
            telemetry.update();

        }
    }
}
