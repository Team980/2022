// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Finder;
// import static frc.robot.Constants.*;

public class BallSeeker extends CommandBase {
  /** Creates a new BallSeeker. */
  private Drivetrain drivetrain;
  private Finder finder;
  private int[] dize;
  private final double SIZE_WHEN_CAUGHT = 73.0; 
  private final double MIN_DRIVE_POWER = 0.3;

  public BallSeeker(Drivetrain drivetrain, Finder finder) {
    this.drivetrain = drivetrain;
    this.finder = finder;

    dize = new int[2];

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(finder);
  } //end BallSeeker

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dize = finder.findClosestCargo();
    if (dize[0] == 160) {
      drivetrain.driveRobot(0, 0.5);
    } //end if 
    else {
      drivetrain.driveRobot(0.75*(1-(dize[1] / SIZE_WHEN_CAUGHT)) + MIN_DRIVE_POWER , 1.5 * (dize[0] / 157.0) );
      //drivetrain.driveRobot(0, 1.25 * (dize[0] / 157.0) );
    } //end else
  } //end execute

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveRobot(0, 0);
  } //ends end

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (dize[1] >= SIZE_WHEN_CAUGHT) {
      return true;
    } //end if
    return false;
  } //end isFinished
}