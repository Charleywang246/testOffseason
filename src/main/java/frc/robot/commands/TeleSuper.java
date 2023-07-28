// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SuperStructure;
import frc.robot.subsystems.SuperStructure.ArmStates;
import frc.robot.subsystems.SuperStructure.GridPos;
import frc.stealedLib.PID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleSuper extends CommandBase {
  
  private final SuperStructure ss;
  private final XboxController operator;
  private boolean roll;

  double GP = GridPos.init;
  ArmStates AS = ArmStates.init;

  public TeleSuper(SuperStructure ss, XboxController operator) {
    this.ss = ss;
    addRequirements(ss);
    this.operator = operator;
    roll = false;
  }

  @Override
  public void initialize() {
    System.out.println("Command TeleSuper start");
    System.out.println("=============================================");
  }

  @Override
  public void execute() {

    //intake部分
    if(operator.getAButtonPressed()){
      roll = !roll;
    }
    if(roll)ss.intakeRoll();
    else ss.intakeStop();

    //extend部分
    double theoPos = 0;
    double currentPos = ss.getPosition();

    // 當operator按下特定按鈕時設定ArmState
    if(operator.getBButtonPressed())AS = ArmStates.top;
    else if(operator.getXButtonPressed())AS = ArmStates.mid;
    else if(operator.getYButtonPressed())AS = ArmStates.bottom;

    // 透過Armstate設定目標高度
    switch (AS) {

      case top:
        theoPos = GridPos.top;
        break;
    
      case mid:
        theoPos = GridPos.mid;
        break;

      case bottom:
        theoPos = GridPos.bottom;
        break;

      case init:
        theoPos = GridPos.init;
        break;
    }

    double error = theoPos - currentPos;
    ss.armExtendByPID(error);

    SmartDashboard.putNumber("armTheoPos", theoPos);
    SmartDashboard.putNumber("armCurrentPos", currentPos);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
