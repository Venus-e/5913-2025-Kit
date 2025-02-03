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

public class CameraPan extends SubsystemBase {
  public final CommandXboxController driverController =
    new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private TalonSRX yaw = new TalonSRX(Constants.cameraYawID);
  private TalonSRX pitch = new TalonSRX(Constants.cameraPitchID);
  
  public CameraPan() {
    yaw.configFactoryDefault();
    pitch.configFactoryDefault();

    yaw.setInverted(false);
    pitch.setInverted(false);

    yaw.clearStickyFaults();
    pitch.clearStickyFaults();

    yaw.setNeutralMode(NeutralMode.Brake);
    pitch.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Right Stick Y", driverController.getRightY());
    SmartDashboard.putNumber("Right Stick X", driverController.getRightX());
  }

  public void panCamera (double setYaw, double setPitch) {
    yaw.set(ControlMode.PercentOutput, setYaw * Constants.cameraPanSpeed);
    pitch.set(ControlMode.PercentOutput, setPitch * Constants.cameraPanSpeed);
  }

  public void Stop () {
    yaw.set(ControlMode.PercentOutput, 0);
    pitch.set(ControlMode.PercentOutput, 0);
  }
}