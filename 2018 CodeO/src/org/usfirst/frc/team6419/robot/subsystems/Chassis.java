package org.usfirst.frc.team6419.robot.subsystems;

import java.util.Date;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
 /*
 */
public class Chassis extends PIDSubsystem {
private	ADXRS450_Gyro gyro;
	Date date;
	long startTime;
private	 VictorSP frontLeft;
private	 VictorSP backLeft;
private	 VictorSP rightFront;
private	 VictorSP rightBack;
private	 SpeedControllerGroup left, right;
private    DifferentialDrive drive;
	 // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Chassis() {
		super(1, 0, 0);
		initChassis();
		initGyro();
		setPercentTolerance(5);
		setInputRange(-180, 180);
		setOutputRange(-1, 1);
		getPIDController().setContinuous(false);

	}
	public void initTeleop() {
		drive = new DifferentialDrive(left, right);

	}
    public void initDefaultCommand() {
    	 
        
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void drive(double speed) {
    	SmartDashboard.putNumber("power", speed);
    	left.set(speed);
    	right.set(speed);
    }
    /**
     * 
     * @param Amount to go forward. power positive is forward
     * @param Amount to turn the robot. turn positive is counter clockwise
     */
    public void arcadeDrive(double power, double turn) {
    	turn = -turn;
    	drive.arcadeDrive(power, turn, true);
		SmartDashboard.putNumber("turn power", turn);
		SmartDashboard.putNumber("drive", power);
    }
    public void tankDrive(double left, double right) {
    	drive.tankDrive(left, right);
    }
    private void initGyro() {
		gyro = new ADXRS450_Gyro();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);

		gyro.calibrate();
    }    
    @Override
    public void periodic() {
    	SmartDashboard.putData(getPIDController());
    }
    private void initChassis() {
		frontLeft = new VictorSP(RobotMap.FRONT_LEFT_DRIVE);
		frontLeft.setInverted(false);
		backLeft = new VictorSP(RobotMap.BACK_LEFT_DRIVE);
		backLeft.setInverted(false);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_DRIVE);
		rightFront.setInverted(false);
		rightBack = new VictorSP(RobotMap.RIGHT_BACK_DRIVE);
		rightBack.setInverted(false);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		right = new SpeedControllerGroup(rightFront,rightBack );
		left.setInverted(true);
		right.setInverted(true);
    }
   
    public double getHeading() {
    	return gyro.getAngle();
    }
    public void resetGyro() {
    	gyro.reset();
    }
    public ADXRS450_Gyro getGyro() {
    	return gyro;
    }
    public void turn(double power) {
    	drive.arcadeDrive(0, power);
    }

    
    
    
	@Override
	protected double returnPIDInput() {
		return getHeading();
	}

	@Override
	protected void usePIDOutput(double output) {
		left.pidWrite(-output);
		right.pidWrite(-output);
		SmartDashboard.putNumber("PID: ", output);
	}
    
    
}

