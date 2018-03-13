package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
VictorSP leftIntake, rightIntake;
public Intake() {
	this.setName("Intake");
	leftIntake = new VictorSP(RobotMap.LEFT_INTAKE);
	rightIntake = new VictorSP(RobotMap.RIGHT_INTAKE);
	leftIntake.setInverted(true);
	rightIntake.setInverted(true);
}
public void set(double power) {
	leftIntake.set(power);
	rightIntake.set(power);
}
public void setPOV(double POV) {
	if(POV < 90 || POV > 270) {
		set(1);
	}
	else if(POV > 90 || POV < 270) {
		set(-1);
	}
	else {
		set(0);
	}
}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

