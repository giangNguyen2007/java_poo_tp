public abstract class Robot {
    private int id;
    private int qteEau;
    private Case position;
    private int vitesse;
    public Robot(int id, Case position, int vitesse) {
        this.position = position;
        this.vitesse = vitesse;
        this.id = id;
    }

    public Case getPosition() {
        return this.position;
    }

    // return true if case is not already occupied
    public boolean setPosition(Case position) {
        if (position.getRobot() != null) {
            return position.setRobot(this);
        } else {
            return false;
        }
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", position=" + position +
                ", vitesse=" + vitesse +
                '}';
    }

    public int getQteEau() {
        return qteEau;
    }

    public void setQteEau(int qteEau) {
        this.qteEau = qteEau;
    }

    public abstract void remplitEau();
}
