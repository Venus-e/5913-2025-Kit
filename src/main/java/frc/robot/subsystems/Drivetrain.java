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

public class Drivetrain extends SubsystemBase {

  private TalonSRX driveLeftFront = new TalonSRX(1);
  private TalonSRX driveLeftBack = new TalonSRX(2);
  private TalonSRX driveRightFront = new TalonSRX(3);
  private TalonSRX driveRightBack = new TalonSRX(4);

  public boolean active = false;

  public Drivetrain() {
    driveLeftFront.configFactoryDefault();
    driveLeftBack.configFactoryDefault();
    driveRightFront.configFactoryDefault();
    driveRightBack.configFactoryDefault();

    driveLeftFront.setInverted(false);
    driveLeftBack.setInverted(false);
    driveRightFront.setInverted(true);
    driveRightBack.setInverted(true);

    driveLeftFront.clearStickyFaults();
    driveLeftBack.clearStickyFaults();
    driveRightFront.clearStickyFaults();
    driveRightBack.clearStickyFaults();

    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Initialized", active);
  }

  public void Move(double forward, double rotate) {
    driveLeftFront.set(ControlMode.PercentOutput, (forward + rotate) * Constants.maxSpeed);
    driveLeftBack.set(ControlMode.PercentOutput, (forward + rotate) * Constants.maxSpeed);
    driveRightFront.set(ControlMode.PercentOutput, (forward - rotate) * Constants.maxSpeed);
    driveRightBack.set(ControlMode.PercentOutput, (forward - rotate) * Constants.maxSpeed);
    active = true;
  }

  public void Stop() {
    driveLeftFront.set(ControlMode.PercentOutput, 0);
    driveLeftBack.set(ControlMode.PercentOutput, 0);
    driveRightFront.set(ControlMode.PercentOutput, 0);
    driveRightBack.set(ControlMode.PercentOutput, 0);
  }
}