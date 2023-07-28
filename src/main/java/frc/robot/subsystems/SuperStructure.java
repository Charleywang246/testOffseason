// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import frc.stealedLib.PID;

public class SuperStructure extends SubsystemBase {

  private final CANSparkMax intake;
  private final WPI_TalonFX extend;
  private final WPI_CANCoder cancoder;

  private final PID extendPID;

  public static class GridPos {
    public static double top = 0;
    public static double mid = -11000;
    public static double bottom = -32000;
    public static double init = -35000;
  }
  //我想這個應該是用來記住手臂在特定高度時CANCoder的直?
  //所以我想應該要是角度之類的

  public static enum ArmStates{
    top,
    mid,
    bottom,
    init
    //加了個initstate作為初始
  }

  public SuperStructure() {
    intake = new CANSparkMax(Constants.SuperStructure.intake, MotorType.kBrushless);
    extend = new WPI_TalonFX(Constants.SuperStructure.extend);

    //🤣🤣🤣🤣
    cancoder = new WPI_CANCoder(0);
    cancoder.configMagnetOffset(0.0);
    //🤣🤣🤣🤣

    intake.setInverted(false);
    extend.setInverted(false);

    extendPID = new PID(0.00005, 0, 0.00005, 0, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("CanCoder Pos", getPosition());
  }

  public void armExtendByPID(double error){
    armExtend(extendPID.calculate(error));
    SmartDashboard.putNumber("armExtendOutput", extendPID.calculate(error));
  }

  public void armExtend(double speed) {
    extend.set(speed);
  }

  public void intakeRoll(){
    intake.set(0.3);
  }

  public void intakeStop(){
    intake.set(0);
  }

  public void intakeSet(double speed) {
    intake.set(speed);
  }

  public double getPosition() {
    return cancoder.getAbsolutePosition();
  }
}
