// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;

public class CollectCargoCommand extends CommandBase {
  //private Finder finder;
  private Collector collector;
  private Drivetrain drivetrain;
  private Conveyor conveyor;

  private final double DISTANCE_WHEN_CAUGHT = 0.3; // in volts
  //TODO find real distance when ball is captured

  /** Creates a new CollectCargoCommand. */
  public CollectCargoCommand(Collector collector, Drivetrain drivetrain, Conveyor conveyor) {
    //this.finder = finder;
    this.collector = collector;
    this.drivetrain = drivetrain;
    this.conveyor = conveyor;

    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(finder);
    addRequirements(collector);
    addRequirements(drivetrain);
    addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    collector.forward();
    drivetrain.driveRobot(0.4, 0);
    conveyor.up();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
    collector.stop();
    conveyor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { // TODO finish finisher
     if(collector.getDistance() < DISTANCE_WHEN_CAUGHT) {
       return true;
     } 
    return false;
  }
}
