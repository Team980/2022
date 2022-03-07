// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Targeting extends SubsystemBase {
  private final double HEIGHT_OF_TARGET = 8.67;
  private final double HEIGHT_OF_CAMERA = 4.0; //TODO find actual height 
  private final double MOUNT_ANGLE = 45.0; //TODO find actual angle
  private final double SHOOTING_RANGE = 15.0; //TODO find actual range

  private NetworkTable limelight;
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;

  private double x;
  private double y;
  private double a;

  /** Creates a new Targeting. */
  public Targeting() {
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
    tx = limelight.getEntry("tx");
    ty = limelight.getEntry("ty");
    ta = limelight.getEntry("ta");

    x = 100;
    y = 100;
    a = -1;

    limelight.getEntry("ledMode").setNumber(1);
    limelight.getEntry("camMode").setNumber(0);
    limelight.getEntry("pipeline").setNumber(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    x = tx.getDouble(0);
    y = ty.getDouble(0);
    a = ta.getDouble(0);

    SmartDashboard.putNumber("limelight x", x);
    SmartDashboard.putNumber("limelight y", y);
    SmartDashboard.putNumber("limelight a", a);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getA() {
    return a;
  }

  public void ledControl(boolean on) {
    NetworkTableEntry led = limelight.getEntry("ledMode");
    if(on) {
      led.setNumber(3);
    } else {
      led.setNumber(1);
    }
  }

  public double calcRange() {
    double d = (HEIGHT_OF_TARGET - HEIGHT_OF_CAMERA) / Math.tan(Math.toRadians(MOUNT_ANGLE + y));
    return d;       
  }

  public double distanceToRange() {
    return calcRange() - SHOOTING_RANGE;
  }
}