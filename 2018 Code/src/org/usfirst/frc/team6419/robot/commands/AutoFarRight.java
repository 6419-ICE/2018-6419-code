package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFarRight extends CommandGroup {

    public AutoFarRight() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    		addSequential(new CommandEncoderDrive(-168));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-20));
    		addSequential(new CommandDump());
    		addSequential(new CommandEncoderDrive(20));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-127));
    	}
    	else {
    		addSequential(new CommandEncoderDrive(-295));
    	}
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
