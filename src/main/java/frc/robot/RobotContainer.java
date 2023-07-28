// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.TeleSuper;
import frc.robot.commands.TeleChassis;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.SuperStructure;
import frc.robot.AutoCommand.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  //subsystems
  private final Chassis m_drive = new Chassis();
  private final SuperStructure m_ss = new SuperStructure();

  //auto Commands
  private final AutoPlace autoPlace = new AutoPlace(m_ss);
  private final AutoMove autoMove = new AutoMove(m_drive);

  //controller
  private final XboxController driver = new XboxController(0);
  private final XboxController operator = new XboxController(1);


  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings(){
    m_drive.setDefaultCommand(new RunCommand(()->{
      m_drive.arcadeDrive(driver.getLeftY(), driver.getRightX());
    }, m_drive));
    
    /**
    m_ss.setDefaultCommand(new TeleSuper(m_ss, operator));
     */

    /**
    m_ss.setDefaultCommand(new RunCommand(()->{
      m_ss.armExtend(operator.getLeftY() * 0.5);
      m_ss.intakeSet(operator.getRightY() * 0.3);
    }, m_ss));
    */
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      autoPlace, autoMove
    );
  }
}
