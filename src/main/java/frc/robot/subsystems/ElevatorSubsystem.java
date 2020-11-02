/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  /**
   * Creates a new ElevatorSubsystem.
   */

  private final WPI_TalonSRX master;
  private final int maxHight;
  private final int minHight;
  private final double nf;

  
  public ElevatorSubsystem() {
    this.master = new WPI_TalonSRX(Constants.kElevatorPort);
    this.configMotor();
    this.stop();
    this.nf = 0.5;
  }

  public void configMotor(){
    this.master.setNeutralMode(NeutralMode.Brake);
    this.master.setInverted(InvertType.None);
    this.master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    this.master.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    this.master.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyClosed, Constants.kMiddleLeftPort);
    }
  
  public void set(double power) {
    if(power > 0 && canMoveUp()){
      this.master.set(ControlMode.PercentOutput,power);
    }
    else if(canMoveDown()){
      this.master.set(ControlMode.PercentOutput,power);
    }
  }

  public void stop() {
    this.master.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean canMoveDown(){
    return this.minHight < getPos();
  }

  public boolean canMoveUp(){
    return this.maxHight > getPos();
  }
  
  public int getPos(){
    return this.master.getSelectedSensorPosition();
  }

  public double getNF(){
    return this.nf;
  }
}
