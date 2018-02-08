package org.usfirst.frc.team6419.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *Contains all of the sensors in the robot.
 */
public class SensorSubsystem extends Subsystem {
	private VictorSP lift;
	private VictorSP leftIntakeWheel, rightIntakeWheel;
	private SpeedControllerGroup intakeThingy;
	
	public SensorSubsystem() {
		lift = new VictorSP()
	}
	
		
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

