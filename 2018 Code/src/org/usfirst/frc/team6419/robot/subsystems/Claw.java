package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *@deprecated
 */
public class Claw extends Subsystem {
VictorSP claw;

public Claw() {
	this.setName("Claw");
	//claw = new VictorSP(RobotMap.GRIPPY_THINGY);
}
public void set(double power) {
	claw.set(power);
}
public void stop() {
	claw.stopMotor();
}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

