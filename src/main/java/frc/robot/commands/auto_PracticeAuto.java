package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class auto_PracticeAuto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public auto_PracticeAuto() {
    addCommands(
        new MoveDirection(1),
        new TurnFull(90),
        new MoveDirection(1),
        new TurnFull(-90));
  }
}