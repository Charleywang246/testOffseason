package frc.robot.AutoCommand;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class AutoMove extends CommandBase{

    private final Chassis chassis;
    private final Timer timer = new Timer();

    public AutoMove(Chassis chassis){
        this.chassis = chassis;
        addRequirements(chassis);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("AutoCommand AutoMove start");
    }

    @Override
    public void execute() {
        if(timer.get() < 3){
            chassis.arcadeDrive(-0.5, 0);
            System.out.println("AutoCommand AutoMove: robot moving backword...");
        }
    }

    @Override
    public void end(boolean interrupted) {
        chassis.arcadeDrive(0, 0);
        System.out.println("AutoCommand AutoMove end");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    public boolean isFinished() {
        if(timer.get() > 3)return true;
        else return false;
    }
}
