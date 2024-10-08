package evenement;

import environment.Direction;
import interaction.*;

public class MoveRobotEvenement extends Evenement {
    private DonneeSimulation donneeSimulation;
    private int robotId;
    private Direction direction;
    public MoveRobotEvenement(long date, DonneeSimulation donneeSimulation, int robtId, Direction direction) {
        super(date);
        this.donneeSimulation = donneeSimulation;
        this.robotId = robtId;
        this.direction = direction;
    }

    @Override
    public void execute() {
        this.donneeSimulation.moveRobot(this.robotId, this.direction);
    }
}
