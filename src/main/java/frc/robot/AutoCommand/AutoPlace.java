package frc.robot.AutoCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SuperStructure;


public class AutoPlace extends CommandBase{
    private final SuperStructure ss;
    private final Timer timer = new Timer();

    public AutoPlace(SuperStructure ss) {
        this.ss = ss;
        addRequirements(ss);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("AutoCommand AutoPlace start");
    }

    @Override
    public void execute() {

        if(timer.get() < 2.5){
            ss.armExtendByPID(SuperStructure.GridPos.top - ss.getPosition());
            System.out.println("AutoCommand AutoPlace: arm extending...");
        }

        if(timer.get() > 2.5 && timer.get() < 3){
            ss.armExtendByPID(SuperStructure.GridPos.top - ss.getPosition());
            ss.intakeSet(-0.3);
            System.out.println("AutoCommand AutoPlace: cube releasing...");
        }

        if(timer.get() > 3) {
            ss.armExtendByPID(SuperStructure.GridPos.init - ss.getPosition());
            System.out.println("AutoCommand AutoPlace: arm shortening...");
        }
    }

    @Override
    public void end(boolean interrupted) {
        ss.armExtend(0);
        System.out.println("AutoCommand AutoPlace end");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    public boolean isFinished() {
        if(timer.get() > 5)return true;
        else return false;
    }
}
