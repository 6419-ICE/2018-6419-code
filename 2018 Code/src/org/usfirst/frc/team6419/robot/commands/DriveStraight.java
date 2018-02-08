package org.usfirst.frc.team6419.robot.commands;

import java.util.Date;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
 
public class DriveStraight extends Command {

	long OldDate = new Date().getTime();
	long targetTime;
	double power;
    public DriveStraight(long targetTime, double power) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        targetTime = targetTime;
        power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OldDate = new Date().getTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.tankDrive(power, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return new Date().getTime() - OldDate >= targetTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
