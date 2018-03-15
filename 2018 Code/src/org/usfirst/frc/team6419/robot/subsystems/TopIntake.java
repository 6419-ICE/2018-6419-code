package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem consists of the flipper motor on the top of the robot.
 */
public class TopIntake extends Subsystem {
private VictorSP motor;
    
public TopIntake() {
	this.setName("Top intake");
	motor = new VictorSP(RobotMap.TOP_INTAKE_MOTOR);
}
/**
 * Sets the raw power to the lift. 
 * @param power
 */
public void set(double power) {
	motor.set(power);
}
/**
 * Sets the power to the default for the lift based off a boolean. 
 * @param out
 */
public void set(boolean out) {
	if(out)motor.set(.7);
	else motor.set(-.7);
}
/**
 * Stops setting power to the motor.
 * <warning> Make sure you call this method after setting power to the motor or it will stall and burn up.
 */
public void stop() {
	motor.stopMotor();
}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

