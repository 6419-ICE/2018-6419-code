package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for position 2 on the field; in the near left position.
 */
public class AutoNearLeft extends CommandGroup {

    public AutoNearLeft() 
    {
//   If the switch is on the left, then do this.
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')
    	{
    		addSequential(new CommandEncoderDrive(-140));
    		addSequential(new CommandDump());
    	}
// Otherwise do this.   	
    	else 
    	{ 
    		addSequential(new CommandEncoderDrive(-10));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-106.5));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-130));
    		addSequential(new CommandPidTurn(-45));
    		addSequential(new CommandEncoderDrive(-15));
    		addSequential(new CommandDump());
    	
    	}
    
    	

    }
}
