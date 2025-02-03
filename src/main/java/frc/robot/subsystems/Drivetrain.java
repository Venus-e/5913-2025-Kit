// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;

public class Drivetrain extends SubsystemBase {
  public final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort); 

  private TalonSRX driveLeftFront = new TalonSRX(Constants.drivetrainLeftFrontID);
  private TalonSRX driveLeftBack = new TalonSRX(Constants.drivetrainLeftBackID);
  private TalonSRX driveRightFront = new TalonSRX(Constants.drivetrainRightFrontID);
  private TalonSRX driveRightBack = new TalonSRX(Constants.drivetrainRightBackID);

  public boolean active = false;

  //Motor controller settings
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
    SmartDashboard.putBoolean("Movement Enabled", active);
    SmartDashboard.putNumber("Left Stick Y", driverController.getLeftY());
    SmartDashboard.putNumber("Left Stick X", driverController.getLeftX());
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
    active = false;
  }
}