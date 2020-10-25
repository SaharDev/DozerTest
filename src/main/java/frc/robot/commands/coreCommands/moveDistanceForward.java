/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.coreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class moveDistanceForward extends CommandBase {
  /**
   * Creates a new moveDistanceForward.
   */

  private final int distance;
  private final double speed; 
  private final DriveTrain dt;
  private final int endPoint;
  private final boolean isForward;

  /**
   * 
   * @param distance negative distance is driving back
   * @param speed speed always positive, speed > 0
   * @param dt
   */
  public moveDistanceForward(int distance, int speed, DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.speed = speed;
    this.dt = dt;
    this.endPoint = dt.getRawLeftPosition() + this.distance;
    
    if(this.distance > 0){
      this.isForward = true;
    }
    else{
      this.isForward = false;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (isForward){
      this.dt.tankDrive(speed, speed, 1);
    }
    else{
      this.dt.tankDrive(-speed,-speed, 1);
    }
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.dt.tankDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(isForward){
      return this.dt.getRawLeftPosition() >= this.endPoint;
    }
    return this.dt.getRawLeftPosition() <= this.endPoint;
  }
}
