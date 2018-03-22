package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

    public AutoCenter() {
    	//Turn based off of which side the switch is on, then place the block.
    	double toTurn = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L' ? -27: 27;
//    	addSequential(new CommandEncoderDrive(-20));
    	addSequential(new CommandWait(1));
    	addSequential(new CommandPidTurn(toTurn));
    	addSequential(new CommandEncoderDrive(-100));
    
    	
    }

}
