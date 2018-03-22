/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6419.robot;


import org.usfirst.frc.team6419.robot.commands.AutoCenter;
import org.usfirst.frc.team6419.robot.commands.AutoFarLeft;
import org.usfirst.frc.team6419.robot.commands.AutoFarRight;
import org.usfirst.frc.team6419.robot.commands.AutoNearLeft;
import org.usfirst.frc.team6419.robot.commands.AutoNearRight;
import org.usfirst.frc.team6419.robot.commands.AutoTestEncoder;
import org.usfirst.frc.team6419.robot.commands.AutoTestGyroPid;
import org.usfirst.frc.team6419.robot.commands.CommandEncoderDrive;
import org.usfirst.frc.team6419.robot.commands.TeleopControls;
import org.usfirst.frc.team6419.robot.subsystems.Chassis;
import org.usfirst.frc.team6419.robot.subsystems.Elevator;
import org.usfirst.frc.team6419.robot.subsystems.Intake;
import org.usfirst.frc.team6419.robot.subsystems.TopIntake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Chassis chassis;
	public static Elevator elevator;
	public static Intake intake;
	public static TopIntake topIntake;
	public static OI m_oi;
	TeleopControls command;
	Command m_autonomousCommand;
	SendableChooser<String> m_chooser;
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		
//Initialize all subsystems
		chassis = new Chassis();
		elevator = new Elevator();
		intake = new Intake();
		topIntake = new TopIntake();
		m_oi = new OI();
//Create a chooser for the autonomous modes.
		m_chooser = new SendableChooser<String>();
		m_chooser.addObject("Far Left Auto", "auto1");
		m_chooser.addObject("Near Left Auto", "auto2");
		m_chooser.addObject("Center Auto", "auto3");
		m_chooser.addObject("Right Auto", "auto 4");
		m_chooser.addObject("Far Right Auto", "auto5");
		m_chooser.addObject("Test Gyro", "TGP");
		m_chooser.addObject("Test Encoder", "TE");
//Add data to the shuffleboard	
		SmartDashboard.putData("Chooser:", m_chooser);
		command = new TeleopControls();
		LiveWindow.add(chassis);
		LiveWindow.add(elevator);
		LiveWindow.add(intake);
		LiveWindow.add(topIntake);
// Add camera to the shuffleboard.		

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		SmartDashboard.putData("Auto mode", m_chooser);

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		chassis.resetGyro();
		Timer gameDataTimer = new Timer();
		gameDataTimer.reset();
		gameDataTimer.start();
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		gameData = ("NULL".equalsIgnoreCase(gameData)) ? null : gameData;

		while(true) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			gameData = ("NULL".equalsIgnoreCase(gameData)) ? null : gameData;
			
			if(gameData == null && gameDataTimer.get() < 0.2) {
				Timer.delay(.02);
			}
			else {
				break;
			}
		
		}

// Initialize the Command group to use based off of position.
		switch (m_chooser.getSelected()) {
		case "TGP":
			System.out.println("Test gyro pid");
			m_autonomousCommand = new AutoTestGyroPid();
			break;
		case "TE":
			System.out.println("Test encoder");
			m_autonomousCommand = new AutoTestEncoder();
			break;
		case "auto1":
			System.out.println("Auto 1");
			m_autonomousCommand = new AutoFarLeft();
			break;
		case "auto2":
			System.out.println("Auto 2");
			m_autonomousCommand = new AutoNearLeft();
			break;
		case "auto3":
			System.out.println("Auto 3");
			m_autonomousCommand = new AutoCenter();		
			break;
		case "auto4":
			System.out.println("Auto 4");
			m_autonomousCommand = new AutoNearRight();
			break;
		case "auto5":
			System.out.println("Auto 5");
			m_autonomousCommand = new AutoFarRight();
			break;
		default:
			m_autonomousCommand = new CommandEncoderDrive(140);
			break;
		}
		

		System.out.println("Command: " +m_autonomousCommand.getName());
		// schedule the autonomous command
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		chassis.resetGyro();

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	//	if (m_autonomousCommand != null) {
		//	m_autonomousCommand.cancel();
		//}
		if(command != null) command.start();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	
	}
}
