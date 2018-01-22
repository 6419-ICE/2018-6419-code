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
		drive = new DifferentialDrive(left, right);
		setPercentTolerance(5);
		
	}
	
    public void initDefaultCommand() {
    	 
        
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    private void initChassis() {
		frontLeft = new VictorSP(RobotMap.FRONT_LEFT_DRIVE);
		backLeft = new VictorSP(RobotMap.BACK_LEFT_DRIVE);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_DRIVE);
		rightBack = new VictorSP(RobotMap.RIGHT_BACK_DRIVE);
		rightFront.setInverted(true);
		backLeft.setInverted(true);
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
		left.set(output);
		right.set(-output);
	}
    
    
}

