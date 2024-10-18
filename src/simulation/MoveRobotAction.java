package simulation;

import simulation.environment.Case;
import simulation.environment.CaseState;
import simulation.environment.Direction;
import simulation.robot.Robot;
import simulation.robot.RobotState;

public class MoveRobotAction {
    private Robot robot;
    private Case destination;

    private int distance;

    public MoveRobotAction(Robot robot, Case destination, int distance) {
        this.robot = robot;
        this.distance = distance;
        this.destination = destination;
    }

    public void finish(){
        robot.setPosition(destination);
        System.out.println("Move" + robot + " to " + robot.getPosition());
    }

    public void launch() {
        robot.setState(RobotState.MOVING);
        destination.setState(CaseState.TARGETED);
    }

    public long getDuration() {
        return distance / robot.getVitesse();
    }
}
