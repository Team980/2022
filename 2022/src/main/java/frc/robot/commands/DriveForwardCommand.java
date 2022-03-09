// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveForwardCommand extends CommandBase {
  private Drivetrain drivetrain;
  private final double DISTANCE_TO_DRIVE = 5;//in ft
  //TODO need to find actual taxi distance

  /** Creates a new DriveForwardCommand. */
  public DriveForwardCommand(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.driveRobot(0.5, -drivetrain.getYPR()[0] / 45);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(drivetrain.getLeftDistance() >= DISTANCE_TO_DRIVE || drivetrain.getRightDistance() >= DISTANCE_TO_DRIVE) {
      return true;
    }
    return false;
  }
}
