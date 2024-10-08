public class Carte {
    private int tailleCase;
    private int nbLignes;
    private int nbColones;
    private Case[][] cases;

    public Carte(int tailleCase, int nbLignes, int nbColones) {
        this.tailleCase = tailleCase;
        this.nbLignes = nbLignes;
        this.nbColones = nbColones;

        // initiate the matrice of cases (reference only)
        this.cases = new Case[nbLignes][nbColones];

        // assign reference to case objects
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColones; j++) {
                cases[i][j] = new Case(i, j, NatureTerrain.TERRAIN_LIBRE);
            }
        }
    }

    public Case getCase(int x, int y) {
        return cases[x][y];
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColones() {
        return nbColones;
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public Case findVoisin(Case c, Direction direction){
        Case res = null;
        switch (direction){
            case Direction.OUEST:
                if (c.getColonne() >= 1){
                    res = cases[c.getLigne()][c.getColonne() - 1];
                }
                break;
            case Direction.EST:
                if (c.getColonne() <= this.nbColones - 1){
                    res = cases[c.getLigne()][c.getColonne() + 1];
                }
                break;
            case Direction.NORD:
                if (c.getLigne() >= 1){
                    res = cases[c.getLigne() - 1][c.getColonne()];
                }
                break;
            case Direction.SUD:
                if (c.getColonne() <= this.nbLignes - 1){
                    res = cases[c.getLigne() + 1][c.getColonne()];
                }
                break;
        }
        return  res;
    }

}
