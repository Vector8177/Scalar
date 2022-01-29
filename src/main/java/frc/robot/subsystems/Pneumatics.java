package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase{
    private  Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

  /** Creates a new DriveTrian. */
  public Pneumatics() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double compresserPressure(){
     return compressor.getPressure();
  }

}