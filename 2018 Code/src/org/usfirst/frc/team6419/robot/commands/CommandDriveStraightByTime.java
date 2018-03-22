package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
 
public class CommandDriveStraightByTime extends Command {
	long initTime, killtime;
	double power;
    public CommandDriveStraightByTime(long targetTime, double power) {
    
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        killtime = targetTime;
        
       this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.tankDrive(power, power);
    	SmartDashboard.putNumber("time: ", System.currentTimeMillis() - initTime);
    	SmartDashboard.updateValues();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - initTime >= killtime;
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
