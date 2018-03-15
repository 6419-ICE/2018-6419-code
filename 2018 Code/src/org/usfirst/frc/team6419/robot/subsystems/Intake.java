package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This subsystem is the wheel that brings in the blocks to our robot.
 */
public class Intake extends Subsystem {
VictorSP intakeWheel;
public Intake() {
	this.setName("Intake");
	intakeWheel = new VictorSP(RobotMap.INTAKE_MOTOR);
}
/**
 * Sets the raw power to the intake wheel.
 * @param power
 */
public void set(double power) {
	intakeWheel.set(power);
}
/**
 * Sets the power based off the POV position passed in.
 * @param POV The POV number to be used, where -1<= POV < 360
 * 
 */
public void setPOV(double POV) {
	if((POV < 90 && POV>= 0)|| POV > 270) {
		set(.4);
	}
	else if(POV > 90 || (POV < 270 && POV > 0)) {
		set(-.4);
	}
	else if(POV == -1) {
		set(0);
	}
	else {
	}
}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

