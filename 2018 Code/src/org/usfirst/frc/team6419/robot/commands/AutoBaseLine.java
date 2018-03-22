package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBaseLine extends CommandGroup {
	public AutoBaseLine() {
		System.out.println("Auto base line");
		// drives straight at the switch.
		addSequential(new CommandEncoderDrive(-160));
		
		//test commit
	}
}
