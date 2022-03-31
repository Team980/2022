// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Targeting;

public class AimAuto extends CommandBase {
  private Drivetrain drivetrain;
  private Targeting targeting;

  /** Creates a new Aim. */
  public AimAuto(Drivetrain drivetrain, Targeting targeting) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.targeting = targeting;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targeting.ledOn(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    targeting.ledOn(true);
    if(targeting.getValidTarget() == 1){
      drivetrain.driveRobot(0, (targeting.getX() - 2)/20);
    }
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    targeting.ledOn(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(targeting.getX()) < 1 && targeting.getValidTarget() == 1){
      return true;
    }
    return false;
  }
}
