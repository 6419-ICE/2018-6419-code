/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6419.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6419.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team6419.robot.commands.GameAuto;
import org.usfirst.frc.team6419.robot.commands.TeleopCommand;
import org.usfirst.frc.team6419.robot.commands.TestEncoder;
import org.usfirst.frc.team6419.robot.commands.TestGyroPid;
import org.usfirst.frc.team6419.robot.subsystems.Chassis;
import org.usfirst.frc.team6419.robot.subsystems.Claw;
import org.usfirst.frc.team6419.robot.subsystems.Elevator;
import org.usfirst.frc.team6419.robot.subsystems.Intake;
import org.usfirst.frc.team6419.robot.subsystems.TopIntake;

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
//	public static Claw claw;
	public static Intake intake;
	public static TopIntake topIntake;
	public static OI m_oi;
	TeleopCommand command;
	Command m_autonomousCommand;
	SendableChooser<String> m_chooser;
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		

		chassis = new Chassis();
		elevator = new Elevator();
		intake = new Intake();
	//	claw = new Claw();
		topIntake = new TopIntake();
		m_oi = new OI();

		m_chooser = new SendableChooser<>();

		m_chooser.addObject("Test Gyro", "TGP");
			m_chooser.addObject("Test Encoder", "TE");
		SmartDashboard.putData("Chooser:", m_chooser);
		command = new TeleopCommand();
		LiveWindow.add(chassis);
		LiveWindow.add(elevator);
		LiveWindow.add(intake);
		LiveWindow.add(topIntake);
	//	LiveWindow.add(claw);
	//	System.out.println("adding chooser");
		//CameraServer.getInstance().startAutomaticCapture();
		System.out.println("AUTO CHOOSER: " + m_chooser);
//		m_autonomousCommand =(Command) m_chooser.getSelected();
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

//		m_chooser.addObject("Test Gyro", new TestGyroPid());
//			m_chooser.addObject("Test Encoder", new TestEncoder());
		chassis.resetGyro();
//		m_autonomousCommand = m_chooser.getSelected();
		switch (m_chooser.getSelected()) {
		case "TGP":
			m_autonomousCommand = new TestGyroPid();
			break;
		case "TE":
			m_autonomousCommand = new TestEncoder();
			break;
		}
		 String gameData = DriverStation.getInstance().getGameSpecificMessage();
		ScaleInformation.setPOSITION( DriverStation.getInstance().getLocation());
		ScaleInformation.setAlliance(DriverStation.getInstance().getAlliance());
		char switchPosition, scalePosition, opponentSwitchLocation;
		 
		 switchPosition = gameData.charAt(0);
		 scalePosition = gameData.charAt(1);
		 opponentSwitchLocation = gameData.charAt(2);
		 ScaleInformation.setSWITCH_LOCATION(switchPosition);
		 ScaleInformation.setSCALE_LOCATION(scalePosition);
		 ScaleInformation.setOPPONENT_SWITCH_LOCATION(opponentSwitchLocation);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
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
