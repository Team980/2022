// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnRobot extends CommandBase {
  private Drivetrain drivetrain;
  private double turnAmount;

  /** Creates a new TurnRobot. */
  public TurnRobot(Drivetrain drivetrain, double turnAmount) {
    this.drivetrain = drivetrain;
    this.turnAmount = turnAmount;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetYaw(turnAmount);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.driveRobot(0, drivetrain.getYPR()[0]/55);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(drivetrain.getYPR()[0]) < 5) {
        return true;
    }
    return false;
  }
}
