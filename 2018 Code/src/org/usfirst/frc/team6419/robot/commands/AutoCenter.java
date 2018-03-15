package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class AutoCenter extends AutoBaseLine {

    public AutoCenter() {
    	//Turn based off of which side the switch is on, then place the block.
    	double toTurn = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L' ? -30: 30;
    	addSequential(new CommandEncoderDrive(10));
    	addSequential(new CommandPidTurn(toTurn));
    	addSequential(new CommandEncoderDrive(69));
    	addSequential(new CommandPidTurn(-toTurn));
    	addSequential(new CommandEncoderDrive(70.25));
    	
    }

}
