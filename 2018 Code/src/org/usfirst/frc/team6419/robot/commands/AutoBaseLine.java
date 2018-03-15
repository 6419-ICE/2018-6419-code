package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBaseLine extends CommandGroup {
	public AutoBaseLine() {
		//hopefully Drives 30 inches forward.
		addSequential(new CommandEncoderDrive(-160));
		
		//test commit
	}
}
