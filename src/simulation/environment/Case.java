package simulation.environment;

import simulation.robot.Robot;

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    private Incendie incendie;

    // occupying robot, null by default
    // objectif: avoid two robots occupying same case
    private Robot occupyingRobot;

    private CaseState state;

    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
        this.occupyingRobot = null;
        this.state = CaseState.LIBRE;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Incendie getIncendie() {
        return incendie;
    }

    public NatureTerrain getNature() {
        return nature;
    }

    @Override
    public String toString() {
        return "Case{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                ", nature=" + nature +
                '}';
    }

    public Robot getOccupyingRobot() {
        return occupyingRobot;
    }

    // return false if the case is already occupied
    public void setOccupyingRobot(Robot robot) {
        if (robot != null){
            this.occupyingRobot = robot;
            this.state = CaseState.OCCUPIED;
        }
    }

    public void liberate(){
        this.occupyingRobot = null;
        this.state = CaseState.LIBRE;
    }

    public CaseState getState() {
        return state;
    }

    public void setState(CaseState state) {
        this.state = state;
    }



    public void setIncendie(Incendie incendie) {
        this.incendie = incendie;
    }

//    public void eteindreIncendie(I) {
//        this.incendie = incendie;
//    }
}
