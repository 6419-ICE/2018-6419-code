package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the chassis using a PID loop.
 */
public class CommandPidTurn extends Command {
double degrees;
/**
 * 
 * @param degrees The amount of rotation for the robot to turn in degrees. Positive is clockwise.
 */
	public CommandPidTurn(double degrees) {
		this.degrees = degrees;

		
	}

		@Override
		public void initialize() {
			Robot.chassis.initGyroPid();
			Robot.chassis.enable();

			Robot.chassis.setSetpoint(degrees);
			Robot.chassis.resetGyro();
		}
		@Override 
		public void execute() {

			SmartDashboard.putNumber("Chassis Output", Robot.chassis.getPosition());
			
		}
		@Override
		public boolean isFinished() {
			return Math.abs(Robot.chassis.getPIDController().getError()) < 5;
//			return Robot.chassis.onTarget();
		}
		@Override
		public void end() {
			Robot.chassis.disable();
			Robot.chassis.stop();
		}
}
