package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
		addSequential(new DriveStraight(1000, .5));
		
		//test commit
	}
}
