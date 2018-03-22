package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives to a position using the Chassis's encoders running on their internal PID controllers.
 */
public class CommandEncoderDrive extends Command {
	double distance;
	/**
	 * 
	 * @param inches The number of inches for the robot to drive.
	 */
    public CommandEncoderDrive(double inches) {
    	
    	requires(Robot.chassis);
// It seems that the robot consistently goes 4% to far, so we accounted for that error by factoring 
// that out using multiplication. 
    	distance = inches * .96;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   
    	System.out.println("Encoder Drive Starting ");
    	Robot.chassis.resetEncoders();
//    	Robot.chassis.resetEncoders();
//    	Robot.chassis.resetEncoders();
//    	Robot.chassis.resetEncoders();
    	Robot.chassis.initEncoderDriveMotors();
//    	Robot.chassis.startDrivePid(distance);


    }

    protected void execute() {
    	Robot.chassis.startDrivePid(distance);
//  update smart dashboard.
    	SmartDashboard.putNumber("Encoder Drive Error: ", Robot.chassis.getAverageError());
    	SmartDashboard.putNumber("Left Encoder: ", Robot.chassis.getLeftPosition());
    	SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightPosition());


    	
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.chassis.getRightError()) < 10;
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
