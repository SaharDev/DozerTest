/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.coreCommands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class moveElevator extends CommandBase {
  /**
   * Creates a new moveElevator.
   */
  private final ElevatorSubsystem es;
  private final int endHight;
  private final double power;
  private final boolean isUp;
  
  public moveElevator(int endHight, ElevatorSubsystem es) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.es = es;
    this.endHight = endHight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (this.es.getPos() > this.endHight){
      this.isUp = true;
      this.power = power + this.es.getNF();
    }
    else{
      this.isUp = false;
      this.power = this.es.getNF() - this.es.getNF();
    }
    if(isUp){
      this.es.set(this.power);
    }
    else{
      this.es.set(-this.power);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.es.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(this.isUp){
      return this.es.getPos() >= this.endHight;
    }
    return this.es.getPos() <= this.endHight;
  }
}
