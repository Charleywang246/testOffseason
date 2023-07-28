// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleChassis extends CommandBase {

  private final Chassis drive;
  private final XboxController driver;
  int speed = 1;
  int turn = 1;

  public TeleChassis(Chassis drive, XboxController driver) {
    this.drive = drive;
    addRequirements(drive);
    this.driver = driver;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Command TeleChassis start");
    System.out.println("==================================================");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.arcadeDrive(driver.getRawAxis(Constants.JoystickConstants.leftStick_Y)*speed, driver.getRawAxis(Constants.JoystickConstants.rightStick_X)*turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
