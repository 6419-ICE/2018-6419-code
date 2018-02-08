package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
 /*
 */
public class Chassis extends Subsystem {
private	ADXRS450_Gyro gyro;
	
	
private	 VictorSP frontLeft;
private	 VictorSP backLeft;
private	 VictorSP rightFront;
private	 VictorSP rightBack;


private	 SpeedControllerGroup left, right;
private    DifferentialDrive drive;
	 // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Chassis() {
		initChassis();
		initGyro();
		drive = new DifferentialDrive(left, right);

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
		frontLeft = new VictorSP(RobotMap.LEFT_FRONT_DRIVE);
		backLeft = new VictorSP(RobotMap.LEFT_BACK_DRIVE);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_DRIVE);
		rightBack = new VictorSP(RobotMap.RIGHT_BACK_DRIVE);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		rightFront.setInverted(false);
		backLeft.setInverted(false);
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
    
    
}

