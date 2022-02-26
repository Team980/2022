// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private MotorControllerGroup leftDrive;
  private MotorControllerGroup rightDrive;
  private DifferentialDrive robotDrive;
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  

  public Drivetrain() {
    var leftTop = new WPI_TalonSRX(3);
    var leftBack = new WPI_TalonSRX(1);
    var leftFront = new WPI_TalonSRX(5);
    leftDrive = new MotorControllerGroup(leftTop, leftBack, leftFront);

    leftEncoder = new Encoder(0, 1, false, EncodingType.k4X); //come back to false bit, switch if forward is negative and vise versa
    leftEncoder.setDistancePerPulse( (Math.PI / 3.0) / 2048.0 );

    var rightTop = new WPI_TalonSRX(4);
    var rightBack = new WPI_TalonSRX(2);
    var rightFront = new WPI_TalonSRX(6);

    rightDrive = new MotorControllerGroup(rightTop, rightBack, rightFront);
    rightDrive.setInverted(true);

    rightEncoder = new Encoder(2, 3, false, EncodingType.k4X); //come back to false bit, switch if forward is negative and vise versa
    rightEncoder.setDistancePerPulse( (Math.PI / 3.0) / 2048.0 );

    robotDrive = new DifferentialDrive(leftDrive, rightDrive);
  }

  public void driveRobot(double move, double turn) {
      robotDrive.arcadeDrive(-move, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Speed", leftEncoder.getRate() );
    SmartDashboard.putNumber("Right Speed", rightEncoder.getRate() );

    SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance() );
    SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance() );
  }

  public void stop(){
    leftDrive.stopMotor();
    rightDrive.stopMotor();
  }

  public double getLeftSpeed() {
    return leftEncoder.getRate();
  }

  public double getRightSpeed() {
    return rightEncoder.getRate();
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightDistance() {
    return rightEncoder.getDistance();
  } 
}
