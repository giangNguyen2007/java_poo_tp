package simulation;

import simulation.environment.*;
import simulation.robot.Robot;

public class DonneeSimulation {
    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;

    public DonneeSimulation(Carte carte, Robot[] robots) {
        this.carte = carte;
        this.robots = robots;
//        this.incendies = incendies;
    }

    public Carte getCarte() {
        return carte;
    }

    public Case getRobotPosition(int i){
        return this.robots[i].getPosition();
    }

    public Robot getRobot(int robotId){
        return this.robots[robotId];
    }

    // change robot state, and return estimated moving duration


    // return null if can not move robot
    public MoveRobotAction createMoveRobotAction(int robotId, Direction direction){
        Case destination = this.carte.findVoisin(robots[robotId].getPosition(), direction);
        if (destination != null && destination.getState() == CaseState.LIBRE){
            return new MoveRobotAction(robots[robotId], destination, this.carte.getTailleCase());
        } else {
            return null;
        }
    }





    public void eteindreIncendie(int idRobot){
        Robot r = robots[idRobot];
        Incendie incendie = r.getPosition().getIncendie();
        if ( incendie!= null){
            if (r.getQteEau() > incendie.getCoutEau()){
                r.setQteEau(r.getQteEau()-incendie.getCoutEau());

            }
        }
    }
}

