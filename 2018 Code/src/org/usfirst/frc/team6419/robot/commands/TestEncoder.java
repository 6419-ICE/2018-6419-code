package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class TestEncoder extends CommandGroup {

    public TestEncoder() {
    	addSequential(new EncoderDrive(-10));
    	addSequential(new Wait(1));
    	addSequential(new PidTurn((DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')?
    			30 : -30));
    	addSequential(new Wait(1));
    	addSequential(new EncoderDrive(-60));
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
