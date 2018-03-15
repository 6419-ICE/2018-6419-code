package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command is used in teleop command to separate the controls for the chassis and the elevator for the sake
 * of readability.
 */
public class TeleopElevator extends Command {
private double liftSpeed;
private double intakeSpeed;
private double clawSpeed;

    public TeleopElevator() {
    	requires(Robot.elevator);
    	requires(Robot.intake);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.elevator.resetEncoder();
    //	Robot.elevator.setSetpoint(0);
   // 	Robot.elevator.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.elevator.setSetpoint(liftSpeed);
  //  	Robot.elevator.enable();
    	Robot.elevator.set(liftSpeed);
    	Robot.intake.set(intakeSpeed);
    	
    }
    public void teleopControls() {
    	Joystick j = Robot.m_oi.getElevatorStick();
    	if(j.getPOV() != -1 && (j.getPOV() < 90 || j.getPOV() > 270) )
    		intakeSpeed = .5;
    	else if(j.getPOV() != -1)
    		intakeSpeed = -.5;
    	else
    		intakeSpeed = 0;
    
    	liftSpeed = j.getY();
    	
    
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
