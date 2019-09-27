package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Auto extends LinearOpMode {
	public Robot robot;
	public void runOpMode()
	{
		robot = new Robot(hardwareMap);
		waitForStart();

		/* drive forward 18 in
		 * turn 90* right
		 * drive forward 18 in
		 * turn 90* left
		 * drive backward 18 in
		 * turn 270* right
		 * drive forward 18 in
		 * turn 270* left
		 */
	}

}
