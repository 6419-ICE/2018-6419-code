package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestGyroPid extends AutonomousCommandGroup {
	public TestGyroPid() {
		addSequential(new PidTurn(90));
		
	}
@Override
public String toString() {
	return "Test gyro pid: ";
}
}
