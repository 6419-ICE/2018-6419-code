package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for position 5 on the field; on the far right.
 */
public class AutoFarRight extends CommandGroup {

    public AutoFarRight() {
    	System.out.println("auto far right");
// If the switch is on the left, do this.   	
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    		addSequential(new CommandEncoderDrive(-168));
    		addSequential(new CommandPidTurn(-90));
    		addSequential(new CommandEncoderDrive(-20));
    		addSequential(new CommandDump());
    		addSequential(new CommandEncoderDrive(20));
    		addSequential(new CommandPidTurn(90));
    		addSequential(new CommandEncoderDrive(-127));
    	}
//  Otherwise, just go straight to the edge.  	
    	else {
    		addSequential(new CommandEncoderDrive(-295));
    	}
     
    }
}
