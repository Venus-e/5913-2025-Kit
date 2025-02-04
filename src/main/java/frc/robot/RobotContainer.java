// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  public final Drivetrain drivetrain = new Drivetrain();
  public final Intake intake = new Intake();
  public final CameraPan cameraPan = new CameraPan();

  private SlewRateLimiter xLimiter = new SlewRateLimiter(40);
  private SlewRateLimiter yLimiter = new SlewRateLimiter(40);

  public final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort); 

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    driverController.povUp().whileFalse(drivetrain.runEnd(
      () -> drivetrain.Move(yLimiter.calculate(
              MathUtil.applyDeadband(driverController.getLeftY(), 0.1)),
            xLimiter.calculate(
              MathUtil.applyDeadband(driverController.getLeftX(), 0.1))),
      () -> drivetrain.Stop()));
    driverController.y().whileTrue(intake.runEnd(
      () -> intake.Out(),
      () -> intake.Stop())); 
    driverController.a().whileTrue(intake.runEnd(
      () -> intake.In(),
      () -> intake.Stop())); 
    driverController.povUp().whileFalse(cameraPan.runEnd(
      () -> cameraPan.panCamera(driverController.getRightY()),
      () -> cameraPan.Stop()));   
  }

  public Command getAutonomousCommand() {
    return null;
  }
}