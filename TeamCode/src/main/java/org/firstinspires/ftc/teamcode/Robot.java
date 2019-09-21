package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
	DcMotor motorLF;
	DcMotor motorLB;
	DcMotor motorRB;
	DcMotor motorRF;
	HardwareMap hwMap;
	static final double DRIVE_WIDTH = 1234; // todo measure
	static final double DRIVE_LENGTH = 1234;
	public Robot(HardwareMap _hwMap)
	{
		hwMap = _hwMap;
		motorLF = hwMap.get(DcMotor.class, "motorLF");
		motorLB = hwMap.get(DcMotor.class, "motorLB");
		motorRB = hwMap.get(DcMotor.class, "motorRB");
		motorRF = hwMap.get(DcMotor.class, "motorRF");

		motorRF.setDirection(DcMotor.Direction.FORWARD);
		motorRB.setDirection(DcMotor.Direction.FORWARD);
		motorLF.setDirection(DcMotor.Direction.REVERSE);
		motorLB.setDirection(DcMotor.Direction.REVERSE);
	}
	public void setPowers(double leftPower, double rightPower)
	{
		motorLF.setPower(leftPower);
		motorLB.setPower(leftPower);
		motorRB.setPower(rightPower);
		motorRF.setPower(rightPower);
	}
	public void linearDrive(double power, double distance)
	{
		motorLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		// todo finish this
	}
	public void turn(double power, double degrees)
	{
		// todo write this
	}}
