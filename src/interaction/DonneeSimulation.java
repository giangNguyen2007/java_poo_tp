package interaction;

import environment.Direction;
import robot.Robot;
import environment.Carte;
import environment.Case;
import environment.Incendie;

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

    public void moveRobot(int i, Direction direction){
        Case oldPosition = this.getRobotPosition(i);
        Case voisin = carte.findVoisin(robots[i].getPosition(), direction);
        //System.out.println("voisin " + direction + "=" + voisin.getLigne() + " " + voisin.getColonne());
        if (voisin != null){
            // move only if voisin is not occupied by another robot
            if (voisin.getRobot() == null){
                robots[i].setPosition(voisin);
                voisin.setRobot(robots[i]);
                System.out.println("Move Robot from" + oldPosition + " to " + this.getRobotPosition(i));
            } else {
                System.out.println("Can not move robot :" + voisin + "is occupied");
            }
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

