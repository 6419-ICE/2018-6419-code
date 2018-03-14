package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.Robot;
import org.usfirst.frc.team6419.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Chassis extends PIDSubsystem {
private	ADXRS450_Gyro gyro;
private final double TICKS_TO_INCH = 4096/(6*Math.PI);
private	 WPI_VictorSPX leftBack, rightBack;
private WPI_TalonSRX leftFront, rightFront;

	public Chassis() {
		super("Chassis", .0275, .005, 0);
		initChassis();
		initGyro();
	
		this.setAbsoluteTolerance(6);
		initDrivePid();

		SmartDashboard.putData(this);
	}

    public void initDefaultCommand() {
        
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void initChassis() {
  		leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE);
  		leftBack = new WPI_VictorSPX(RobotMap.LEFT_BACK_DRIVE);
  	
  	
  		leftBack.follow(leftFront);
  		
  		rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
  		rightBack = new WPI_VictorSPX(RobotMap.RIGHT_BACK_DRIVE);
  		
  		rightBack.follow(rightFront);

leftFront.setSafetyEnabled(false);
rightFront.setSafetyEnabled(false);
leftBack.setSafetyEnabled(false);
rightBack.setSafetyEnabled(false);
      }
 
    
	public void driveStraight(double power) {
		leftFront.set(ControlMode.PercentOutput, power);
		rightFront.set(ControlMode.PercentOutput, power);
	} 
	public void stop() {
		leftFront.set(ControlMode.PercentOutput, 0);
		rightFront.set(ControlMode.PercentOutput, 0);
	}

    /**
     * 
     * @param Amount to go forward. power positive is forward
     * @param Amount to turn the robot. turn positive is counter clockwise
     */
    public void arcadeDrive(double power, double turn) {
    	double left, right;
    	 left  = power + turn;
         right = power - turn;

         // Normalize the values so neither exceed +/- 1.0
        double  max = Math.max(Math.abs(left), Math.abs(right));
         if (max > 1.0)
         {
             left /= max;
             right /= max;
         }
    	
 
    	
    	
    	leftFront.set(ControlMode.PercentOutput, left);
    	rightFront.set(ControlMode.PercentOutput, right);
    	
    	
    	
    }
  
    public void tankDrive(double powerLeft, double powerRight) {
    }
   
 private void initGyro() {
		gyro = new ADXRS450_Gyro();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);

		gyro.calibrate();
		this.setOutputRange(-1, 1);
		this.setAbsoluteTolerance(6);
    }    
    public double getHeading() {
    	return gyro.getAngle();
    }
    public void resetGyro() {
    	gyro.reset();
    }

public void initGyroPid() {
	this.leftBack.follow(leftFront);
//	leftBack.setInverted(false);
//	leftFront.setInverted(false);
	rightBack.follow(rightFront);
//	rightBack.setInverted(true);
//	rightFront.setInverted(true);
	this.setOutputRange(-.5, .5);
}
public void initDrivePid() {
	int timeout = 10; 
	int pidSlot = 0;

	
leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidSlot,
			timeout);
rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidSlot, timeout);

leftFront.setSensorPhase(true);
rightFront.setSensorPhase(true);

leftFront.configNominalOutputForward(0,timeout);
leftFront.configNominalOutputReverse(0, timeout);
leftFront.configPeakOutputForward(.5, timeout);
leftFront.configPeakOutputReverse(-.5, timeout);

rightFront.configNominalOutputForward(0, timeout);
rightFront.configNominalOutputReverse(0, timeout);
rightFront.configPeakOutputForward(.5, timeout);
rightFront.configPeakOutputReverse(-.5, timeout);

leftFront.config_kF(pidSlot, 0.0, timeout);
leftFront.config_kP(pidSlot, 2, timeout);
leftFront.config_kI(pidSlot, 0.0, timeout);
leftFront.config_kD(pidSlot, 0.0, timeout);
//config pid: This is the pid that will be used for the robot.
rightFront.config_kF(pidSlot, 0.0, timeout);
rightFront.config_kP(pidSlot, 2, timeout);
rightFront.config_kI(pidSlot, 0.0, timeout);
rightFront.config_kD(pidSlot, 0.0, timeout);
/*
 * ------------------------------------------------------------------------------------------------------
 * Broken part
 */


}
public void initEncoderDriveMotors() {
//	leftFront.setInverted(false);
//	leftBack.setInverted(false);
//	leftFront.setInverted(false);
//	rightFront.setInverted(false);
	leftBack.follow(leftFront);
	//leftFront.follow(rightFront);
	rightBack.follow(rightFront);

}
public void startGyroPid(double degrees) {

}
public void enableControllers() {

}


/**
 * motors drive to position
 * @param inches
 */
public void startDrivePid(double inches) {
	inches = inches *TICKS_TO_INCH;
	System.out.println("Inches:  " +inches);
	leftFront.set(ControlMode.Position, inches);
	rightFront.set(ControlMode.Position, -inches);

}
public DifferentialDrive getDrive() {
	
	leftBack.follow(leftFront);
	rightBack.follow(rightFront);
	
	return new DifferentialDrive(leftFront, rightFront);
}
public void initTeleop() {
//	leftFront.setInverted(false);
//	rightFront.setInverted(false);
//	leftBack.setInverted(false);
//	rightBack.setInverted(false);
	leftBack.follow(leftFront);
	rightBack.follow(rightFront);
}

@Override
protected double returnPIDInput() {
	// TODO Auto-generated method stub
	 return this.getHeading();
}

@Override
protected void usePIDOutput(double output) {
	System.out.println("Error: " +this.getPIDController().getError());

	leftFront.pidWrite(output);
	rightFront.pidWrite(output);
//	leftFront.set(ControlMode.PercentOutput, output);
//	rightFront.set(ControlMode.PercentOutput, -output);
}
public double getLeftPosition() {
	return leftFront.getSelectedSensorPosition(0);
	
}
public double getRightPosition() {
	return rightFront.getSelectedSensorPosition(0);
}
public double getLeftError() {
	return leftFront.getClosedLoopError(0);
}
public double getRightError() {
	return rightFront.getClosedLoopError(0);
}
public double getLeftSpeed() {
	return leftFront.getSelectedSensorVelocity(0);
	
}
public double getRightSpeed() {
	return rightFront.getSelectedSensorVelocity(0);
}

public double getLeftDistance() {
	return leftFront.getSelectedSensorPosition(0);
}
public double getRightDistance() {
	return rightFront.getSelectedSensorPosition(0);
}
public void resetEncoders() {
	leftFront.setSelectedSensorPosition(0, 0, 0);
	rightFront.setSelectedSensorPosition(0, 0, 0);
}

public double getAverageSpeed() {
	return (Math.abs(getLeftSpeed()) +Math.abs(getRightSpeed()))/2;
}
public double getAverageError() {
	return (Math.abs(getLeftError())+Math.abs(getRightError()))/2 ;

}
public double getAveragePosition() {
	return (getLeftPosition() - getRightPosition())/2;
}


    
    
    
}

