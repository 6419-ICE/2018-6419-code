package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestEncoder extends CommandGroup {

    public AutoTestEncoder() {
    	System.out.println("test encoder");
    addSequential(new CommandEncoderDrive(30));
    addSequential(new CommandEncoderDrive(-30));
    }
}
