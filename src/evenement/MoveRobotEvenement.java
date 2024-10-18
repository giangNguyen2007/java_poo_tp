package evenement;

import simulation.environment.Direction;
import simulation.*;

public class MoveRobotEvenement extends Evenement {

    private Direction direction;
    private MoveRobotAction action;
    public MoveRobotEvenement(long date, int robotId, Direction direction) {
        super(date, robotId);
        this.direction = direction;
    }


    @Override
    public void launchExcution(DonneeSimulation d) {

        // if move is possible : case is not already occupied
        // then calculate movement duration,
        this.action = d.createMoveRobotAction(robotId, direction);
        if (action != null){
            this.setStartDate(this.startDate + action.getDuration());
            action.launch();
            this.state = EvenementState.ONGOING;

        } else {
            // if move not possible, abandon action
            this.state = EvenementState.ABANDONNED;
        }
    }

    @Override
    public void finishExecution() {
         if (this.state == EvenementState.ONGOING) {
                this.action.finish();
                this.state = EvenementState.FINISHED;
         }
    }

}
