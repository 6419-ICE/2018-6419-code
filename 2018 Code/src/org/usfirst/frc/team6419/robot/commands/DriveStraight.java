
package org.usfirst.frc.team6419.robot.commands;

import java.util.Date;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
 
public class DriveStraight extends Command {
	Date date;
	long OldDate;


	long OldDate = new Date().getTime();
	long targetTime;
	double power;
    public DriveStraight(long targetTime, double power) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        this.targetTime = targetTime;
        this.power = power;
        targetTime = targetTime;
        power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	date = new Date();
    	OldDate = date.getTime();
    	OldDate = new Date().getTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	date = new Date();
    	Robot.chassis.drive(power);
    	SmartDashboard.putNumber("Time", date.getTime() - OldDate);
    	Robot.chassis.tankDrive(power, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (date.getTime()) - OldDate >= targetTime;
        
        return new Date().getTime() - OldDate >= targetTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
}
