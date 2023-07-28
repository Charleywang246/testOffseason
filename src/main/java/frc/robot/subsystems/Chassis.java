// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  
  private final WPI_TalonSRX LF;
  private final WPI_TalonSRX RF;
  private final WPI_TalonSRX LR;
  private final WPI_TalonSRX RR;

  MotorControllerGroup left;
  MotorControllerGroup right; 
  DifferentialDrive drive;

  public Chassis() {
    LF = new WPI_TalonSRX(Constants.Chassis.LFMotor);
    RF = new WPI_TalonSRX(Constants.Chassis.RFMotor);
    LR = new WPI_TalonSRX(Constants.Chassis.LRMotor);
    RR = new WPI_TalonSRX(Constants.Chassis.RRMotor);
    left = new MotorControllerGroup(LF, LR);
    right = new MotorControllerGroup(RF, RR);
    drive = new DifferentialDrive(left, right);        

    RF.setInverted(true);
    RR.setInverted(true);
    LF.setInverted(false);
    LR.setInverted(false);
  }

  @Override
  public void periodic(){}

  public void arcadeDrive(double x, double z){
    drive.arcadeDrive(-x, z, false);
    SmartDashboard.putNumber("chassis xSpeed", x);
    SmartDashboard.putNumber("chassis zSpeed", z);
  }
}