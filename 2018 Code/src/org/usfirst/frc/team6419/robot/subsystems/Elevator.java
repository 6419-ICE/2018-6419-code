package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
private	VictorSP upThingy, downThingy;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


public Elevator() {
	upThingy = new VictorSP(RobotMap.UP_LIFT);
	downThingy = new VictorSP(RobotMap.DOWN_LIFT);  //pulling the elevator down makes this motor go clockwise
}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveLift(double amount) {
    	upThingy.set(amount);
    	upThingy.setInverted(true);
    	downThingy.set(-amount);
    }
}

