package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
/**
 * Waits for a number of seconds.
 */
public class CommandWait extends Command {
Timer timer = new Timer();
private double time;
/**
 * 
 * @param amount Time in seconds for the robot to wait.
 */
    public CommandWait(double amount) {
    	time = amount;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     * Resets and starts the timer.
     */
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
