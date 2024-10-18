package evenement;

import simulation.DonneeSimulation;

public abstract class Evenement implements Comparable<Evenement>{
    protected int robotId;
    protected long startDate;

    protected EvenementState state;

    public Evenement(long startDate, int robotId) {
        this.state = EvenementState.NOT_STARTED;
        this.startDate = startDate;
        this.robotId = robotId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public int getRobotId(){ return robotId;}
    public abstract void launchExcution(DonneeSimulation d);
    public abstract void finishExecution();


    @Override
    // return positif if event starts earlier
    public int compareTo(Evenement e) {
        if (this.startDate > e.getStartDate()){
            return 1;
        } else if (this.startDate < e.getStartDate()){
            return -1;
        } else {
            return 0;
        }
    }
}
