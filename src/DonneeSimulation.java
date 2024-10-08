public class DonneeSimulation {
    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;

    public DonneeSimulation(Carte carte, Robot[] robots, Incendie[] incendies) {
        this.carte = carte;
        this.robots = robots;
        this.incendies = incendies;
    }



    public Case getRobotPosition(int i){
        return this.robots[i].getPosition();
    }

    public void moveRobot(int i, Direction direction){
        Case oldPosition = this.getRobotPosition(i);
        Case voisin = carte.findVoisin(robots[i].getPosition(), direction);
        //System.out.println("voisin " + direction + "=" + voisin.getLigne() + " " + voisin.getColonne());
        if (voisin != null){
            robots[i].setPosition(voisin);
            System.out.println("Move Robot from" + oldPosition + " to " + this.getRobotPosition(i));
        }
    }

    public Carte getCarte() {
        return carte;
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

