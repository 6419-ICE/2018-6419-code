package org.usfirst.frc.team6419.robot.subsystems;

import org.usfirst.frc.team6419.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TopIntake extends Subsystem {
private VictorSP motor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
public TopIntake() {
	this.setName("Top intake");
	motor = new VictorSP(RobotMap.TOP_INTAKE_MOTOR);
}
public void set(double power) {
	motor.set(power);
}
public void stop() {
	motor.stopMotor();
}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

