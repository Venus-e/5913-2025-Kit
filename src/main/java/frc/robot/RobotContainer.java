// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  public final Drivetrain drivetrain = new Drivetrain();
  public final Intake intake = new Intake();

  public final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort); 

  public RobotContainer() {
    SmartDashboard.putNumber("Joystick Y", driverController.getLeftY());
    SmartDashboard.putNumber("Joystick X", driverController.getLeftX());
    configureBindings();
  }

  private void configureBindings() {
    driverController.povUp().whileFalse(drivetrain.runEnd(
      () -> drivetrain.Move(driverController.getLeftY(), driverController.getLeftX()),
      () -> drivetrain.Stop()));
    driverController.y().whileTrue(intake.runEnd(
      () -> intake.Out(),
      () -> intake.Stop())); 
    driverController.a().whileTrue(intake.runEnd(
      () -> intake.In(),
      () -> intake.Stop()));    
  }

  public Command getAutonomousCommand() {
    return null;
  }
}