/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6419.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Port numbers
		public static final int LEFT_FRONT_DRIVE = 1; //correct 
		public static final int RIGHT_FRONT_DRIVE = 2; //correct
		public static final int RIGHT_BACK_DRIVE = 3;//correct
		public static final int LEFT_BACK_DRIVE = 4;//correct
		
		public static final int UP_LIFT = 7;
		public static final int DOWN_LIFT = 9;
		public static final int LEFT_INTAKE = 4;
		public static final int RIGHT_INTAKE = 5;
	//	public static int GRIPPY_THINGY = 6;
		
		public static final int TOP_INTAKE_MOTOR = 6;
		
		public static final int LIFT_ENCODER_CHANNEL_A = 0;
		public static final int LIFT_ENCODER_CHANNEL_B = 1;
		
		public static final int LEFT_ENCODER_PING = 2;
		public static final int LEFT_ENCODER_ECHO = 3;
		public static final int RIGHT_ENCODER_PING = 4;
		public static final int RIGHT_ENCODER_ECHO = 5;
	//Motors themselves
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

}



