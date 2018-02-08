package org.usfirst.frc.team6419.robot.commands;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDGyroTurn extends PIDSubsystem {

    // Initialize your subsystem here
    public PIDGyroTurn( double setPoint) {
    	super(1, 0, 0);
    	setSetpoint(setPoint);
    	setPercentTolerance(5);
    	setOutputRange(-1, 1);
    	
    	
    	
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
    	
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
