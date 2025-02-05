// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {

  public final String limelight = "limelight";

  public Camera() {
    HttpCamera camera = new HttpCamera("kitLimelight", "10.59.13.76:5800");
    CameraServer.addCamera(camera);
  }

  @Override
  public void periodic() {
  }
}