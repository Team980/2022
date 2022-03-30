// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Targeting;
import frc.robot.subsystems.VelocityControlledShooter;

public class FireCargoAuto extends CommandBase {
  /** Creates a new FireCargo. */
  private VelocityControlledShooter shooter;
  private Conveyor conveyor;
  private Targeting targeting;

  private int timeCounter;
  private boolean initialShot;
  private double spinToRange;

  public FireCargoAuto(VelocityControlledShooter shooter, Conveyor conveyor, boolean initialShot, Targeting targeting) {
    this.shooter = shooter;
    this.conveyor = conveyor;
    this.initialShot = initialShot;
    this.targeting = targeting;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(conveyor);

    timeCounter = 0;
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(initialShot) {
      conveyor.runConveyor(-1);
      shooter.setSetpoint(36);
      timeCounter++;
    }
    else {
      conveyor.runConveyor(-1);
      spinToRange = 1.89 * targeting.calcRange() + 24;
      shooter.setSetpoint(spinToRange);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timeCounter >= 100 || shooter.getMeasurement() < 32) {
      return true;
    }
    return false;
  }
}
