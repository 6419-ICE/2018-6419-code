package org.usfirst.frc.team6419.robot.commands;

import org.usfirst.frc.team6419.robot.ScaleInformation;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class GoToSwitch extends Command {
	private int pos;
	private Scheduler scheduler;
    public GoToSwitch(int position) {
    	
    	pos = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	scheduler = Scheduler.getInstance();
    	try {
			scheduler.wait(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	switch(pos) {
    	case 1:
    		if(ScaleInformation.getSWITCH_LOCATION() == 'L') {
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchMiddle));
    			scheduler.add(new PidTurn(90));
    			scheduler.add(new EncoderDrive(20));
    		}
    		else if(ScaleInformation.getSWITCH_LOCATION() == 'R') {
    			scheduler.add(new AcrossField(pos));
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    		
    		}
    		break;
    	case 2:
    		if(ScaleInformation.getSWITCH_LOCATION() == 'L') {
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    		}
    		else if(ScaleInformation.getSWITCH_LOCATION() == 'R') {
    			scheduler.add(new AcrossField(pos));
    			scheduler.add(new PidTurn(90));
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    		}
    		break;
    	case 3:
    		if(ScaleInformation.getSWITCH_LOCATION() == 'L') {
    			scheduler.add(new PidTurn(AutonomousValues.TURN_MIDDLE_TO_LEFT));
    			scheduler.add(new EncoderDrive(AutonomousValues.CENTER_TO_SWITCH));
    			
    		}
    		else if(ScaleInformation.getSWITCH_LOCATION() == 'R') {
    			scheduler.add(new PidTurn(-AutonomousValues.TURN_MIDDLE_TO_LEFT));
    			scheduler.add(new EncoderDrive(AutonomousValues.CENTER_TO_SWITCH));
    		}
    		break;
    	case 4:
    		if(ScaleInformation.getSWITCH_LOCATION() == 'L') {
    			scheduler.add(new AcrossField(pos));
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    			
    			
    		}
    		else if(ScaleInformation.getSWITCH_LOCATION() == 'R') {
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    		}
    		break;
    	case 5:
    		if(ScaleInformation.getSWITCH_LOCATION() == 'L') {
    			scheduler.add(new AcrossField(pos));
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchWall));
    	
    		}
    		else if(ScaleInformation.getSWITCH_LOCATION() == 'R') {
    			scheduler.add(new EncoderDrive(AutonomousValues.wallToSwitchMiddle));
    			scheduler.add(new PidTurn(-90));
    			scheduler.add(new EncoderDrive(20));
    			
    		}
    		break;
    	default: 
    		break;
    	}
    	scheduler.add(new Dump());
   	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
