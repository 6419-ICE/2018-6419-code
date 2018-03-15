package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for position 1 on the field; this is on the far left.
 */
public class AutoFarLeft extends CommandGroup {

    public AutoFarLeft() 
    {
// If the driver station is on the left, do this.   	
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')
    	{
    		addSequential(new CommandEncoderDrive(-168));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-20));
    		addSequential(new CommandDump());
    		addSequential(new CommandEncoderDrive(20));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-127));
    	}
//Otherwise do this.    	
    	else 
    	{
    		addSequential(new CommandEncoderDrive(-295));
    	}

    }
}
