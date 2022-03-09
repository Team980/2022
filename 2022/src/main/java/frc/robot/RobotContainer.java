// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForwardCommand;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Finder;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Targeting;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final Drivetrain drivetrain = new Drivetrain();
  private final Shifter shifter  = new Shifter (drivetrain);
  private final Shooter shooter = new Shooter();
  private final Collector collector = new Collector();
  private final Conveyor conveyor = new Conveyor();
  private final Climb climber = new Climb();
  private final Finder tracker = new Finder(true);
  private final Targeting targetingSystem = new Targeting();

  private final XboxController xBox = new XboxController(2);

  private final Joystick wheel = new Joystick(0);
  private final Joystick throttle = new Joystick(1);
  private final Joystick prajBox = new Joystick(4);

  private final DriveForwardCommand driveForwardCommand = new DriveForwardCommand(drivetrain);
  //TODO need auto commands for shoot then taxi, shoot and find ball, shoot-find-shoot, and find-shoot-shoot

  SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    autoChooser.setDefaultOption("Drive Forward", driveForwardCommand);

    SmartDashboard.putData(autoChooser);

    drivetrain.setDefaultCommand(new RunCommand(
      () -> drivetrain.driveRobot(throttle.getY(), wheel.getX()), 
      drivetrain
      ));
    shifter.setDefaultCommand(new RunCommand(
      () -> shifter.autoShift(),
       shifter
       ));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //TODO switch this to PrajBox switches
    new JoystickButton(xBox, Button.kRightBumper.value).whenPressed(new RunCommand(shifter::setHighGear, shifter) );
    new JoystickButton(xBox, Button.kLeftBumper.value).whenPressed(new RunCommand(shifter::setLowGear, shifter) );
    new JoystickButton(xBox, Button.kB.value).whenPressed(new InstantCommand(shifter::autoShift, shifter) );

    //TODO need control for collector, conveyor, shooter, also raise and lower collector

    //TODO need Climber control, use safety prajbox switch to enable and xbox to deploy and retract
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }
}
