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

  private TalonSRX cameraMotor = new TalonSRX(Constants.cameraMotorID);
  
  public CameraPan() {
    cameraMotor.configFactoryDefault();
    cameraMotor.setInverted(false);
    cameraMotor.clearStickyFaults();
    cameraMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Right Stick Y", driverController.getRightY());
  }

  public void panCamera (double setMotor) {
    cameraMotor.set(ControlMode.PercentOutput, setMotor * Constants.cameraPanSpeed);
  }

  public void Stop () {
    cameraMotor.set(ControlMode.PercentOutput, 0);
  }
}