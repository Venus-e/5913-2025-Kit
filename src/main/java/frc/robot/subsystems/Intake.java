// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private TalonSRX intake = new TalonSRX(5);
  public String intakeState = "Not moving";

  public Intake() {
    intake.configFactoryDefault();
    intake.setInverted(false);
    intake.clearStickyFaults();
    intake.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Intake State", intakeState);
  }

  public void Out() {
    intake.set(ControlMode.PercentOutput, Constants.IntakeSpeed);
    intakeState = "Out";
  }

  public void In() {
    intake.set(ControlMode.PercentOutput, -1 * Constants.IntakeSpeed);
    intakeState = "In";
  }

  public void Stop() {
    intake.set(ControlMode.PercentOutput, 0);
    intakeState = "Not moving";
  }
}