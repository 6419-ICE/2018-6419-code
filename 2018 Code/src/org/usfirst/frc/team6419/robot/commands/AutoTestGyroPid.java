package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTestGyroPid extends AutoBaseLine {
	public AutoTestGyroPid() {
		addSequential(new CommandPidTurn(90));
		
		addSequential(new CommandPidTurn(-90));

		
	}
@Override
public String toString() {
	return "Test gyro pid: ";
}
}
