package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PidTurn extends Command {
double degrees;
	public PidTurn(double degrees) {
		this.degrees = degrees;

		
	}

		@Override
		public void initialize() {
			Robot.chassis.initGyroPid();
			Robot.chassis.enable();

			Robot.chassis.setSetpoint(degrees);

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
