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

/**
 * This contains the code for a differential drive on a robot using two TalonSRXs and two VictorSPs in the
 * CAN bus.
 * 
 * 
 * @author FRC-6419
 *
 */
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
//Not used
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   /**
    * Initializes the motos that the chassis uses. It also sets the back motors to follow front.
    * Currently the front uses talon SRXs for the built in PID features. 
    */
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
 
    /**
     * 
     * @param power: the power to set to the motors.
     */
	public void driveStraight(double power) {
		leftFront.set(ControlMode.PercentOutput, power);
		rightFront.set(ControlMode.PercentOutput, power);
	} 
	/**
	 * Sets the power output to the motors to zero.
	 * This does not completely stop the robot.
	 */
	public void stop() {
		leftFront.set(ControlMode.PercentOutput, 0);
		rightFront.set(ControlMode.PercentOutput, 0);
	}

    /**
     * This sends power to the drive train based off of input.
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
   
    /**
     * Prepares the gyroscope for competition.
     * Called once before the match.
     */
 private void initGyro() {
		gyro = new ADXRS450_Gyro();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);

		gyro.calibrate();
    }    
    public double getHeading() {
    	return gyro.getAngle();
    }
    public void resetGyro() {
    	gyro.reset();
    }
/**
 * Initializes the PID controller on the chassis for turning.
 */
public void initGyroPid() {
	this.setAbsoluteTolerance(6);

	this.leftBack.follow(leftFront);

	rightBack.follow(rightFront);

	this.setOutputRange(-.5, .5);
}
public void initDrivePid() {
	int timeout = 10; 
	int pidSlot = 0;
//Select the sensor to use on the Talons.
	leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidSlot,
		timeout);
	rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidSlot, timeout);

	leftFront.setSensorPhase(true);
	rightFront.setSensorPhase(true);
//Config output for the Talons.
	// left motor
	leftFront.configNominalOutputForward(0,timeout);
	leftFront.configNominalOutputReverse(0, timeout);
	leftFront.configPeakOutputForward(.5, timeout);
	leftFront.configPeakOutputReverse(-.5, timeout);
	//right motor
	rightFront.configNominalOutputForward(0, timeout);
	rightFront.configNominalOutputReverse(0, timeout);
	rightFront.configPeakOutputForward(.5, timeout);
	rightFront.configPeakOutputReverse(-.5, timeout);
// Sets the P, I, D, and F coefficients for the controller.
	leftFront.config_kF(pidSlot, 0.0, timeout);
	leftFront.config_kP(pidSlot, 2, timeout);
	leftFront.config_kI(pidSlot, 0.0045, timeout);
	leftFront.config_kD(pidSlot, 0.0, timeout);
//config pid: This is the pid that will be used for the robot.
	rightFront.config_kF(pidSlot, 0.0, timeout);
	rightFront.config_kP(pidSlot, 2, timeout);
	rightFront.config_kI(pidSlot, 0.0045, timeout);
	rightFront.config_kD(pidSlot, 0.0, timeout);
}
/**
 * Ensures the back motors are following the front for encoder driving.
 */
public void initEncoderDriveMotors() {

	leftBack.follow(leftFront);
	rightBack.follow(rightFront);

}

/**
 * This starts the motors to drive forward a specified number of inches.
 * @param inches the number of inches for the robot to drive straight for.
 */
public void startDrivePid(double inches) {
	inches = inches *TICKS_TO_INCH;
	leftFront.set(ControlMode.Position, inches);
	rightFront.set(ControlMode.Position, -inches);

}
/**
 * This prepares motors for differential drive and returns a new instance of 
 * DifferentialDrive for teleoperation.
 * 
 * @return
 */
public DifferentialDrive getDrive() {
	
	leftBack.follow(leftFront);
	rightBack.follow(rightFront);
	
	return new DifferentialDrive(leftFront, rightFront);
}

@Override
protected double returnPIDInput() {
	 return this.getHeading();
}

@Override
protected void usePIDOutput(double output) {
/*Turns the robot using the output from the encoders. 
 * Since the motors move in opposite directions, using the same sign output causes the robot to turn.
 */

	leftFront.pidWrite(output);
	rightFront.pidWrite(output);
}
/**
 * 
 * @return the left encoder's position in ticks.
 */
public double getLeftPosition() {
	return leftFront.getSelectedSensorPosition(0);
	
}
/**
 * 
 * @return The right encoder's position in ticks.
 */
public double getRightPosition() {
	return rightFront.getSelectedSensorPosition(0);
}
/**
 * 
 * @return the left talon's closed loop error in ticks.
 */
public double getLeftError() {
	return leftFront.getClosedLoopError(0);
}
/**
 * 
 * @return The right Talon's closed loop error in ticks
 */
public double getRightError() {
	return rightFront.getClosedLoopError(0);
}
/**
 * 
 * @return The speed of the left encoder in robot units.
 */
public double getLeftSpeed() {
	return leftFront.getSelectedSensorVelocity(0);
	
}
/**
 * 
 * @return the speed of the right encoder in robot units.
 */
public double getRightSpeed() {
	return rightFront.getSelectedSensorVelocity(0);
}
/**
 * Sets the left and right encoders' positions to 0.
 */
public void resetEncoders() {
	leftFront.setSelectedSensorPosition(0, 0, 10);
	rightFront.setSelectedSensorPosition(0, 0, 10);
}
/**
 * 
 * @return The average speed of the encoders in robot units.
 */
public double getAverageSpeed() {
	return (Math.abs(getLeftSpeed()) +Math.abs(getRightSpeed()))/2;
}
/**
 * 
 * @return The average closed loop error of the encoders in ticks.
 */
public double getAverageError() {
	return (Math.abs(getLeftError())+Math.abs(getRightError()))/2 ;

}
/**
 * 
 * @return The average position of the encoders in ticks.
 */
public double getAveragePosition() {
	return (getLeftPosition() - getRightPosition())/2;
}


    
    
    
}

