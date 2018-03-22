package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.DriverStation;

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
