// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shifter extends SubsystemBase {
  private Solenoid shifter;
  private Drivetrain drivetrain; 
  private final double SHIFT_POINT_HIGH = 4.5;
  private final double SHIFT_POINT_LOW = 4.0;
  /** Creates a new Shifter. */
  public Shifter(Drivetrain drivetrain) {
    shifter = new Solenoid(PneumaticsModuleType.CTREPCM, 0); // high gear = 0, low gear = 1
    this.drivetrain = drivetrain;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void autoShift(){
    if(!isHighGear()&&(Math.abs(drivetrain.getLeftSpeed()) > SHIFT_POINT_HIGH || Math.abs(drivetrain.getRightSpeed()) > SHIFT_POINT_HIGH )){
      shifter.set(true);
    }
    else if(isHighGear()&&(Math.abs(drivetrain.getLeftSpeed()) > SHIFT_POINT_LOW || Math.abs(drivetrain.getRightSpeed()) > SHIFT_POINT_LOW )){
      shifter.set(false);
    }
  }

  public boolean isHighGear(){
    return shifter.get();
  }

  public void setLowGear(){
    shifter.set(false);
  }

  public void setHighGear(){
    shifter.set(true);
  }
}
