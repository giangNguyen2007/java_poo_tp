package evenement;

import simulation.environment.Direction;
import simulation.Simulateur;
import simulation.robot.RobotAction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class EvenementGestionnaire {

    private ArrayList<LinkedList<RobotAction>> trajetRobots;
    private PriorityQueue<Evenement> filEvenement;
    private Simulateur sim;



    public EvenementGestionnaire(Simulateur sim){
        this.filEvenement = new PriorityQueue<Evenement>();
        this.trajetRobots = new ArrayList<LinkedList<RobotAction>>();
        this.sim = sim;
    }

    public void addTrajetRobots(int robotId, LinkedList<RobotAction> trajet){
        if (robotId <= this.trajetRobots.size() - 1){
            throw new IllegalArgumentException("robot's trajet has already been set");
        } else {
            this.trajetRobots.add(trajet);
        }
    }

    public void setTrajetRobots(ArrayList<LinkedList<RobotAction>> trajetRobots){
        this.trajetRobots = trajetRobots;
    }

    public void initFilEvent(){
        for (int i = 0; i < trajetRobots.size(); i++) {
            RobotAction action = trajetRobots.get(i).poll();
            Evenement e = this.createEvent(0, i, action);
            this.addNewEvent(e, -1);
        }
    }

    public void addNewEvent(Evenement event, long simulationDate){
        if (event.getStartDate() > simulationDate){
            this.filEvenement.add(event);
        }
    }

    public long nextEventDate(){
        return filEvenement.peek().getStartDate();
    }

    public Evenement createEvent(long startDate, int robotId, RobotAction action){
        Evenement res = null;
        switch (action){
            case MOVE_EST :
                res = new MoveRobotEvenement(startDate, robotId, Direction.EST);
                break;
            case MOVE_OUEST:
                res = new MoveRobotEvenement(startDate, robotId, Direction.OUEST);
                break;
            case MOVE_NORD :
                res = new MoveRobotEvenement(startDate, robotId, Direction.NORD);
                break;
            case MOVE_SUD :
                res = new MoveRobotEvenement(startDate, robotId, Direction.SUD);
                break;
        }
        return res;
    }

    private RobotAction findRobotNextAction(int robotId){
        if (!trajetRobots.get(robotId).isEmpty()){
            return trajetRobots.get(robotId).poll();
        } else {
            return null;
        }
    }

    public void execute(long currentDate){
        while (this.nextEventDate() == currentDate){
            Evenement currentEvent = filEvenement.poll();
            // if event not started, start the event
            if (currentEvent.state == EvenementState.NOT_STARTED){
                currentEvent.launchExcution(sim.getDonneeSimulation());
                this.addNewEvent(currentEvent, currentDate);

            } else if (currentEvent.state == EvenementState.ONGOING){
                currentEvent.finishExecution();

                // add next robot event
                if (findRobotNextAction(currentEvent.getRobotId()) != null){
                    long nextEventStartDate = currentDate + 1;
                    int robotId = currentEvent.getRobotId();
                    RobotAction nextAction = findRobotNextAction(currentEvent.getRobotId());
                    Evenement e = createEvent(nextEventStartDate, robotId, nextAction);
                    addNewEvent(e, currentDate);
                }
            }
        }
    }

}
