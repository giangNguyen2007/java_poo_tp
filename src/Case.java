public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    private Incendie incendie;

    // occupying robot, null by default
    // objectif: avoid two robots occupying same case
    private Robot robot;

    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
        this.robot = null;
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

    public Robot getRobot() {
        return robot;
    }

    // return false if the case is already occupied
    public boolean setRobot(Robot robot) {
        if (this.robot != null) {
            this.robot = robot;
            return true;
        } else {
            return false;
        }
    }

    public void liberate(){
        this.robot = null;
    }

    public void setIncendie(Incendie incendie) {
        this.incendie = incendie;
    }

    public void eteindreIncendie(I) {
        this.incendie = incendie;
    }
}
