package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class TeleopControls extends Command {
	DifferentialDrive chassisDrive;
	TeleopElevator liftCommand;
    public TeleopControls() {
    	requires(Robot.chassis);
    	requires(Robot.elevator);
    	requires(Robot.topIntake);
    	liftCommand = new TeleopElevator();
    }
    

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	Robot.chassis.disable();
    	liftCommand.initialize();
    	Robot.chassis.resetEncoders();
    	chassisDrive = Robot.chassis.getDrive();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {

    	Joystick drive = Robot.m_oi.getDriverStick();
    	double driveThrottle = (Math.abs((drive.getThrottle()+2)));
    	driveThrottle = Math.sqrt(driveThrottle);
    	
    	
    	chassisDrive.arcadeDrive(-drive.getRawAxis(1)/driveThrottle, drive.getRawAxis(2)/driveThrottle, true);
    
    	liftCommand.teleopControls();
    	liftCommand.execute();
    	Robot.intake.setPOV(drive.getPOV());
    	
    	if(drive.getRawButton(9))
    		Robot.topIntake.set(.7);
    	else if (drive.getRawButton(10))
    		Robot.topIntake.set(-.7);
    	else
    		Robot.topIntake.stop();
    		
    	SmartDashboard.putNumber("Left Encoder: ", Robot.chassis.getLeftPosition());
    	SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightPosition());
    	
    	SmartDashboard.putData(chassisDrive);
SmartDashboard.putNumber("Gyro heading", Robot.chassis.getHeading());
    	SmartDashboard.putNumber("Drive Throttle", driveThrottle);
    	SmartDashboard.updateValues();
    
    	
    }
    public void teleopControls() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	
    }
    
}
