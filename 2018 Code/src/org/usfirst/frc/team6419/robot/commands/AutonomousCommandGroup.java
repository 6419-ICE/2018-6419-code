package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
		//hopefully Drives 30 inches forward.
		addSequential(new PidTurn(90));
		
		//test commit
	}
}
