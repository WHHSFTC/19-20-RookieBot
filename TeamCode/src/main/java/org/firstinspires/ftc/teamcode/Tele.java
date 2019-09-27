package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Tele extends LinearOpMode {
    Robot robot;
    public void runOpMode()
    {
        robot = new Robot(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            double center = gamepad1.right_stick_x;
            double power = -gamepad1.left_stick_y;
            double leftPower = 0, rightPower = 0;
            boolean turnLeft = gamepad1.left_bumper;
            boolean turnRight = gamepad1.right_bumper;
            if (!(turnLeft || turnRight)) {
                robot.setPowers(power, power);
                continue;
            }
            /* leftRadius = sqrt((-width/2 - center*3)^2 + (length/2)^2)
            *  rightRadius = sqrt((width/2 - center*3)^2 + (length/2)^2)
            *  ltr = leftRadius / rightRadius
        *
            *
            *
            * */
            double max = Math.max(Math.max(Math.abs(leftPower), Math.abs(rightPower)), 0);
            leftPower /= max;
            rightPower /= max;


        }
    }
}
