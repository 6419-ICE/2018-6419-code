package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;
import org.usfirst.frc.team6419.robot.OI;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class TeleopCommand extends Command {
	
    public TeleopCommand() {
    	requires(Robot.chassis);
    	requires(Robot.elevator);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Joystick drive = Robot.m_oi.joystick;
    	Joystick lift = Robot.m_oi.joystick2;
    	double driveThrottle = (Math.abs((drive.getThrottle()+2)));
    	Robot.chassis.arcadeDrive(drive.getRawAxis(1)/driveThrottle, drive.getRawAxis(2)/driveThrottle);
    	SmartDashboard.putNumber("Gyro heading", Robot.chassis.getHeading());
    	SmartDashboard.putNumber("Drive Throttle", driveThrottle);
    	SmartDashboard.updateValues();
    	Robot.elevator.moveLift(lift.getRawAxis(1));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
