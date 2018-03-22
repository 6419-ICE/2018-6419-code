package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is an autonomous for position 4 on the field. 
 * That is in the inside of the right of the field.
 * 
 */
public class AutoNearRight extends CommandGroup {

    public AutoNearRight() {
    	System.out.println("auto near right");
// If the switch is to the right, do this.    	
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') 
    	{
    		addSequential(new CommandEncoderDrive(-140));
    		addSequential(new CommandDump());
    	}
// Otherwise, do this.   	
    	else 
    	{ 
    		addSequential(new CommandEncoderDrive(-10));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-106.5));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-130));
    		addSequential(new CommandPidTurn(45));
    		addSequential(new CommandEncoderDrive(-15));
    		addSequential(new CommandDump());
    	}

    }
}
