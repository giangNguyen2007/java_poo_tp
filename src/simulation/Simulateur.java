package simulation;

import simulation.environment.Carte;
import simulation.environment.Case;
import evenement.EvenementGestionnaire;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;
import simulation.robot.RobotAction;

import java.awt.*;
import java.util.LinkedList;

public class Simulateur implements Simulable {
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
    private EvenementGestionnaire evenementGestionnaire;

    public Simulateur(GUISimulator gui, Color invaderColor, DonneeSimulation donneeSimulation) {
        this.gui = gui;
        this.x = 20;
        this.y = 20;
        gui.setSimulable(this);				// association a la gui!
        this.invaderColor = invaderColor;
        this.dateSimulation = -1;
        this.donneeSimulation = donneeSimulation;
        this.carte = donneeSimulation.getCarte();
        this.evenementGestionnaire = new EvenementGestionnaire(this);

        draw();
    }

    public DonneeSimulation getDonneeSimulation(){
        return this.donneeSimulation;
    }

    public void addTrajetRobots(int robotId, LinkedList<RobotAction> trajetRobot){
        this.evenementGestionnaire.addTrajetRobots(robotId, trajetRobot);
    }



    @Override
    public void next() {
        dateSimulation++;
        if (dateSimulation == 0){
            this.evenementGestionnaire.initFilEvent();
        }
        System.out.println("next event date = " + evenementGestionnaire.nextEventDate());
        if (evenementGestionnaire.nextEventDate() == dateSimulation){
            evenementGestionnaire.execute(dateSimulation);
        }
        draw();
    }

    @Override
    public void restart() {
        draw();
    }

    private void drawRobot(int roboId){
        Case posRobot = donneeSimulation.getRobotPosition(roboId);
        gui.addGraphicalElement(new gui.Rectangle(x + posRobot.getLigne() *carte.getTailleCase(),
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
