/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.coreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class turnAngles extends CommandBase {
  /**
   * Creates a new turnAngles.
   */

  private final double speed;
  private final DriveTrain  dt;
  private double endDeg;
  private final double deg;


  /**
   * 
   * @param speed
   * @param dt
   * @param deg cant be equal to 0 (deg != 0)
   */
  public turnAngles(double speed, DriveTrain dt, double deg) {
    // Use addRequirements() here to declare subsystem dependencies.
    
    this.speed = speed;
    this.dt = dt;
    this.deg = deg;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.endDeg = dt.getHeading() + this.deg;
    
    if(deg > 0){
      this.dt.tankDrive(this.speed, -this.speed, 1);
    }
    else{
      this.dt.tankDrive(-this.speed, this.speed, 1);
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
    
    if(deg > 0){ // turning right 
      return dt.getHeading() >= endDeg;
    }
    return dt.getHeading() <= endDeg; // turning left
  }
}
