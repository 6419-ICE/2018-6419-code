package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	Encoder liftEncoder;
private	VictorSP upThingy, downThingy;
//private boolean close;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


public Elevator() {
	super("Elevator", 2, 0, 0);
	upThingy = new VictorSP(RobotMap.UP_LIFT);
	upThingy.setInverted(true);
	
	downThingy = new VictorSP(RobotMap.DOWN_LIFT);  //pulling the elevator down makes this motor go clockwise
	 liftEncoder = new Encoder(RobotMap.LIFT_ENCODER_CHANNEL_A, RobotMap.LIFT_ENCODER_CHANNEL_B);
	 liftEncoder.reset();
	 
	
}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double amount) {
    	upThingy.set(amount);
    }
    public void stopLift() {
    	upThingy.setSpeed(0);
    }
  
    public void resetEncoder() {
    	liftEncoder.reset();
    	
    }
    
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return liftEncoder.getRate();
	}
	@Override
	protected void usePIDOutput(double output) {
		upThingy.set(output);
	}

}

