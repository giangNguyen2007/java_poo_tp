package simulation.environment;

public class Incendie {
    private Case position;

    // cout en eau pour l'eteindre
    private int coutEau;

    public Incendie(int coutEau, Case position) {
        this.position = position;
        this.coutEau = coutEau;
    }

    public Case getPosition() {
        return position;
    }

    public int getCoutEau() {
        return coutEau;
    }

    public void setPosition(Case position) {
        this.position = position;
    }
}
