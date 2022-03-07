// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
  private WPI_TalonSRX collect;

  private WPI_TalonSRX grabber;

  //private AnalogInput ballDetector;
  private double distance;

  /** Creates a new Collector. */
  public Collector() {
    collect = new WPI_TalonSRX(7);

    grabber = new WPI_TalonSRX(8);

    //ballDetector = new AnalogInput(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //distance = ballDetector.getVoltage();
    //SmartDashboard.putNumber("distance in volts", distance);
  }
  /*
  public double getDistance(){
    return distance;
  }
  */
  public void forward() {
    collect.set(-1);
    grabber.set(-1);
  }

  public void reverse() {
    collect.set(1);
    grabber.set(1);
  }

  public void stop() {
    collect.stopMotor();
    grabber.stopMotor();
  }
}
