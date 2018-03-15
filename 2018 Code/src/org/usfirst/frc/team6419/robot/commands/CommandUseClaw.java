package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandUseClaw extends Command{
	private double power;
	public CommandUseClaw(boolean close) {
	//	requires(Robot.claw);
		if(close) 
			power = .2;
		else
			power = -.7;
	}
	@Override
	public void initialize() {
		
	}
	@Override
	public void execute() {
	//Robot.claw.set(power);	
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void end() {
		
	}

}
