import robot.RobotRoue;
import robot.Robot;
import environment.Carte;
import environment.Case;
import evenement.Evenement;
import evenement.MoveRobotEvenement;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;
import environment.Direction;
import interaction.DonneeSimulation;

import java.util.ArrayList;

import java.awt.Color;


public class TestSimulateur {
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);


        Carte carte = new Carte(20, 7, 10);

        Robot rb1 = new RobotRoue(0, carte.getCase(2, 2), 10);
        Robot rb2 = new RobotRoue(1, carte.getCase(3, 4), 10);

        Robot[] robots = new Robot[] {rb1, rb2};

        DonneeSimulation donneeSimulation = new DonneeSimulation(carte, robots);

        MoveRobotEvenement e01 = new MoveRobotEvenement(0, donneeSimulation, 0, Direction.EST);
        MoveRobotEvenement e02 = new MoveRobotEvenement(0, donneeSimulation, 1, Direction.EST);

        MoveRobotEvenement e11 = new MoveRobotEvenement(1, donneeSimulation, 0, Direction.SUD);
        MoveRobotEvenement e12 = new MoveRobotEvenement(1, donneeSimulation, 1, Direction.SUD);

        Simulateur sim = new Simulateur(gui, Color.decode("#f2ff28"), donneeSimulation);
        sim.ajoutEvenement(e01);
        sim.ajoutEvenement(e02);
        sim.ajoutEvenement(e11);
        sim.ajoutEvenement(e12);
    }
}

class Simulateur implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;

    /** La couleur de dessin de l'invader */
    private Color invaderColor;

    /** Abcisse courante de l'invader (bord gauche) */
    private int x;

    private DonneeSimulation donneeSimulation;
    private Carte carte;

    /** Ordonnée courante de l'invader (bord supérieur) */
    private int y;
    private long dateSimulation;
    private ArrayList<ArrayList<Evenement>> evenements;

    public Simulateur(GUISimulator gui, Color invaderColor, DonneeSimulation donneeSimulation) {
        this.gui = gui;
        this.x = 20;
        this.y = 20;
        gui.setSimulable(this);				// association a la gui!
        this.invaderColor = invaderColor;
        this.dateSimulation = -1;
        this.evenements = new ArrayList<>();
        this.donneeSimulation = donneeSimulation;
        this.carte = donneeSimulation.getCarte();

        draw();
    }

    public void ajoutEvenement(Evenement e) {
        if (e.getDate() < this.evenements.size()) {
            this.evenements.get((int)e.getDate()).add(e);
        }
        //
        else if (e.getDate() == this.evenements.size()){
            ArrayList<Evenement> newEvenements = new ArrayList<>();
            newEvenements.add(e);
            this.evenements.add(newEvenements);
        }
    }

    private void incrementDate(){
        dateSimulation++;
        if (dateSimulation < evenements.size()){
            for (int i = 0; i < evenements.get((int)dateSimulation).size(); i++){
                // execute all events at that date
                evenements.get((int)dateSimulation).get(i).execute();
            }
        }
    }

    @Override
    public void next() {
        incrementDate();
        draw();
    }

    @Override
    public void restart() {
        draw();
    }

    private void drawRobot(int roboId){
        Case posRobot = donneeSimulation.getRobotPosition(roboId);
        gui.addGraphicalElement(new Rectangle(x + posRobot.getLigne() *carte.getTailleCase(),
                y + posRobot.getColonne() * carte.getTailleCase(),
                invaderColor, null, 3));
    }

    private void draw() {
        gui.reset();	// clear the window

        drawRobot(1);
        drawRobot(0);

        gui.addGraphicalElement(new Text(x + 40, y + 120, invaderColor, "INVADER"));
        gui.addGraphicalElement(new Text(x + 40, y + 140, invaderColor, "Simulation date =" + this.dateSimulation));

        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColones(); j++) {
                gui.addGraphicalElement(new Rectangle(x + j*carte.getTailleCase(),
                                                        y + i * carte.getTailleCase(),
                                                        invaderColor, null, carte.getTailleCase()));
            }
        }
    }
}
