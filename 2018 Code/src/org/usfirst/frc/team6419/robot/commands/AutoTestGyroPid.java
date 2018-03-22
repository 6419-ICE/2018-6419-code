package org.usfirst.frc.team6419.robot.commands;

/**
 *
 */
public class AutoTestGyroPid extends AutoBaseLine {
	public AutoTestGyroPid() {
		System.out.println("Test gyro");
		addSequential(new CommandPidTurn(90));
		
		addSequential(new CommandPidTurn(-90));

		
	}
@Override
public String toString() {
	return "Test gyro pid: ";
}
}
