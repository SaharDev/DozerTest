/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class feader extends SubsystemBase {
  /**
   * Creates a new feader.
   */

  private final QPI_VictorSPX master;

  public feeder() {
    this.master = new WPI_VictorSPX(Constants.kFeederPort);
    this.configMotor();
    
    
  }

  public void configMotor(){
    this.master.setNeutralMode(Neutral.Mode.Brake);
    this.master.setInverted(InvertType.None);
    stop();
  }

  public void set(double power){
    this.master.set(ControlMode.PercentOutput, power);
  } 
  public void stop(){
    this.master.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
