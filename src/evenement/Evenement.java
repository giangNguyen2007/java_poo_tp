package evenement;

public abstract class Evenement implements Comparable<Evenement>{
    private long date;

    public Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public abstract void execute();


    @Override
    public int compareTo(Evenement e) {
        return (int)this.date - (int)e.getDate();
    }
}
