package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
    private final TalonFX climber;

    public static class ClimbConfigs {
        public static final int climberId = 15;
        public static final InvertedValue climberInversion = InvertedValue.Clockwise_Positive;
        public static final NeutralModeValue climberNeutralMode = NeutralModeValue.Brake;
        public static final double positionStatusFrame = 0.05;
        public static final double velocityStatusFrame = 0.01;
    }
    // Constructor that sets configs for climber
    public ClimberSubsystem() {
        climber = new TalonFX(ClimbConfigs.climberId, "rio");
        var climberConfigurator = climber.getConfigurator();
        var configs = new TalonFXConfiguration();
        configs.MotorOutput.Inverted = ClimbConfigs.climberInversion;
        configs.MotorOutput.NeutralMode = ClimbConfigs.climberNeutralMode;
        climberConfigurator.apply(configs);
    }
    private void setClimberSpeed(double speed) {
        climber.set(speed);
    }
    private void stopClimber() {
        climber.stopMotor();
    }

    // Spins climber at the set speed, can be both in and out depending on speed
    public Command spinClimber(double speed) {
        return startEnd(() -> setClimberSpeed(speed), this::stopClimber);
    }

}