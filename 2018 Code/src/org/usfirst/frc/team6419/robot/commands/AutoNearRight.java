package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoNearRight extends CommandGroup {

    public AutoNearRight() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    		addSequential(new CommandEncoderDrive(-140));
    		addSequential(new CommandDump());
    	}
    	else { 
    		addSequential(new CommandEncoderDrive(-10));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-106.5));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-130));
    		addSequential(new CommandPidTurn(45));
    		addSequential(new CommandEncoderDrive(-15));
    		addSequential(new CommandDump());
    	
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
