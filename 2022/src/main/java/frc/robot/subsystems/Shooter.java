// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private WPI_TalonSRX flywheel;
  private Encoder shooterEncoder; 
  public Shooter() {
    flywheel = new WPI_TalonSRX(10);
    shooterEncoder = new Encoder(4, 5, true, EncodingType.k4X);
    shooterEncoder.setDistancePerPulse(1 / 2048);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("shooter rps", shooterEncoder.getRate());
  }

  public void fire() {
    flywheel.set(1);
  }

  public void stop() {
    flywheel.stopMotor();
  }
}
