/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.coreCommands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import sun.font.TrueTypeFont;

public class moveElevator extends CommandBase {
  /**
   * Creates a new moveElevator.
   */
  private final ElevatorSubsystem es;
  private int hight;
  private final int change;
  private final int endPos;
  private final boolean isUp;
  private final double power;

  
  public moveElevator(ElevatorSubsystem es,int change, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.es = es;
    this.hight = this.es.getPos();
    this.change = change;
    this.endPos = this.hight + this.change;
    this.power = power;

    if(this.change > 0){
      this.isUp = true;
    }else{
      this.isUp = false;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(this.isUp){
      this.es.set(this.power);
    }else{
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
    this.es.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(this.isUp){
      return this.endPos <= this.es.getPos();
    }
    else{
      return this.endPos >= this.es.getPos();
    }
  }
}
