package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MoveTeleMecanumDeBounce", group="Linear OpMode")
public class MoveTeleDeBounce extends LinearOpMode{

    private ElapsedTime runTime = new ElapsedTime();

    private DcMotor motorRF = null;
    private DcMotor motorRB = null;
    private DcMotor motorLB = null;
    private DcMotor motorLF = null;

    private Servo arm = null;
    private Servo wrist = null;
    private Servo inner = null;
    private Servo outer = null;

    private DcMotor yi = null;
    private DcMotor er = null;
    private DcMotor san = null;
    private DcMotor si = null;

    private CRServo leftServo = null;
    private CRServo rightServo = null;

    private float xPow, yPow, zPow;

    private double deadZone = .05;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorRF  = hardwareMap.get(DcMotor.class, "motor_rf");
        motorLF = hardwareMap.get(DcMotor.class, "motor_lf");

        motorRB  = hardwareMap.get(DcMotor.class, "motor_rb");
        motorLB = hardwareMap.get(DcMotor.class, "motor_lb");

        arm = hardwareMap.servo.get("arm");
        wrist = hardwareMap.servo.get("wrist");
        inner = hardwareMap.servo.get("inner");
        outer = hardwareMap.servo.get("outer");

        yi = hardwareMap.get(DcMotor.class, "1"); //this will be port 0
        er = hardwareMap.get(DcMotor.class, "2"); //this will be port 1
        san = hardwareMap.get(DcMotor.class, "3");//this will be port 2
        si = hardwareMap.get(DcMotor.class, "4"); //this will be port 3

        leftServo = hardwareMap.crservo.get("left");
        rightServo = hardwareMap.crservo.get("right");

        motorRF.setDirection(DcMotor.Direction.FORWARD);
        motorRB.setDirection(DcMotor.Direction.FORWARD);
        motorLF.setDirection(DcMotor.Direction.FORWARD);
        motorLB.setDirection(DcMotor.Direction.FORWARD);

        yi.setDirection(DcMotor.Direction.FORWARD);
        er.setDirection(DcMotor.Direction.FORWARD);
        san.setDirection(DcMotor.Direction.FORWARD);
        si.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("status: ", "initialization DONE");
        telemetry.update();

        double armPos = 0.0;
        double wristPos = 0.0;
        double innerPos = 0.0;
        double outerPos = 0.0;

        boolean in = false, out = false, pIn = false, pOut = false;

        waitForStart();
        runTime.reset();

        while (opModeIsActive()) {
            getJoyStickValues();

            double pwr;
            double m;

            double theta = Math.atan2(yPow, xPow);
            double power = Math.pow(Math.max(Math.abs(xPow), Math.abs(yPow)),2);
            double zPower = Math.pow(Math.abs(zPow),2);
            double x = Math.cos(theta);
            double y = Math.sin(theta);

            double z = Math.signum(zPow);

            wristPos =
                    gamepad2.a ? .33 :
                            gamepad2.y ? .66 :
                                    gamepad2.b ? 0 :
                                            gamepad2.x ? 1 :
                                                    wristPos;
            
            if (gamepad2.right_bumper && !pOut) {
                out = !out;
                outer.setPosition(out ? 0 : .5);
            }
            if (gamepad2.left_bumper && !pIn) {
                in = !in;
                inner.setPosition(in ? .5 : 0);
            }

            pIn = gamepad2.left_bumper;
            pOut = gamepad2.right_bumper;

            armPos =
                    gamepad2.dpad_down ? .33 :
                            gamepad2.dpad_left ? 1 :
                                    gamepad2.dpad_right ? 0 :
                                            gamepad2.dpad_up ? .66 :
                                                    armPos;

            pwr = -gamepad2.right_stick_y;
            m = Math.max(Math.abs(pwr), 1.0);

            pwr /= m;

            yi.setPower(pwr);
            er.setPower(pwr);
            san.setPower(pwr);
            si.setPower(pwr);

            if (gamepad1.a) {
                leftServo.setPower(.5);
                rightServo.setPower(-.5);
                telemetry.addData("Servo Power", "left (%.2f), right (%.2f)", .5, -.5);
            }
            if (gamepad1.b) {
                leftServo.setPower(0);
                rightServo.setPower(0);
                telemetry.addData("Servo Power", "left (%.2f), right (%.2f)", 0, 0);
            }
            if (gamepad1.y) {
                leftServo.setPower(-.5);
                rightServo.setPower(.5);
                telemetry.addData("Servo Power", "left (%.2f), right (%.2f)", -.5, .5);
            }

            arm.setPosition(armPos);
            wrist.setPosition(wristPos);
            inner.setPosition(innerPos);
            outer.setPosition(outerPos);

            motorLF.setPower(power * (-y-x) + zPower*z);
            motorRF.setPower(power * (y-x) + zPower*z);
            motorRB.setPower(power * (y+x) + zPower*z);
            motorLB.setPower(power * (-y+x) + zPower*z);

            telemetry.addData("xpow", xPow);
            telemetry.addData("ypow", yPow);
            telemetry.addData("zpow", zPow);
            telemetry.addData("power", power);
            telemetry.addData("x",x);
            telemetry.addData("y",y);
            telemetry.addData("motorLF",motorLF.getPower());
            telemetry.addData("motorRF",motorRF.getPower());
            telemetry.addData("motorLB",motorLB.getPower());
            telemetry.addData("motorRB",motorRB.getPower());
            telemetry.addData("Runtime", runTime.toString());
            telemetry.addData("Servo Positions", "ARM: (%.2f) WRIST: (%.2f) INNER: (%.2f) OUTER: (%.2f)", armPos, wristPos, innerPos, outerPos);
            telemetry.addData("Motor Power", "Power: (%.2f)", pwr);
            telemetry.update();
        }
    }
    public void getJoyStickValues(){
        yPow = gamepad1.left_stick_y;
        xPow = gamepad1.left_stick_x;
        zPow = gamepad1.right_stick_x;

        xPow = Math.abs(xPow) < deadZone ? 0 : xPow;
        yPow = Math.abs(yPow) < deadZone ? 0 : yPow;

    }
}
