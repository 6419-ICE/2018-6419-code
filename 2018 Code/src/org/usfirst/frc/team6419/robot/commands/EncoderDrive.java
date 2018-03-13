package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderDrive extends Command {
	double distance;
	//private final int ticksPerInch = 682;
    public EncoderDrive(double inches) {
    	
    	requires(Robot.chassis);
    	distance = inches * .96;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Encoder Drive Starting");
    	Robot.chassis.resetEncoders();
    	Robot.chassis.initEncoderDriveMotors();
    	Robot.chassis.initDrivePid();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder Drive Error: ", Robot.chassis.getAverageError());
    	SmartDashboard.putNumber("Left Encoder: ", Robot.chassis.getLeftDistance());
    	SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightDistance());

    	Robot.chassis.startDrivePid(distance);

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.chassis.getAverageError()) < 50;
    	//   	return Robot.chassis.getAverageError() < 400;
    	//    	return Robot.chassis.getError() < 3 && Robot.chassis.getError() > -3;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
