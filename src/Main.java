import simulation.Simulateur;
import simulation.robot.RobotAction;
import simulation.robot.RobotRoue;
import simulation.robot.Robot;
import simulation.environment.Carte;
import evenement.MoveRobotEvenement;
import gui.GUISimulator;
import simulation.environment.Direction;
import simulation.DonneeSimulation;

import java.awt.Color;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);


        Carte carte = new Carte(20, 7, 10);

        Robot rb1 = new RobotRoue(0, carte.getCase(2, 2), 5);
        Robot rb2 = new RobotRoue(1, carte.getCase(3, 4), 4);

        Robot[] robots = new Robot[] {rb1, rb2};

        DonneeSimulation donneeSimulation = new DonneeSimulation(carte, robots);

        LinkedList<RobotAction> r0 = new LinkedList<RobotAction>();
        r0.add(RobotAction.MOVE_SUD);
        r0.add(RobotAction.MOVE_EST);
        r0.add(RobotAction.MOVE_EST);

        LinkedList<RobotAction> r1 = new LinkedList<RobotAction>();
        r1.add(RobotAction.MOVE_NORD);
        r1.add(RobotAction.MOVE_EST);
        r1.add(RobotAction.MOVE_EST);

        Simulateur sim = new Simulateur(gui, Color.decode("#f2ff28"), donneeSimulation);
        sim.addTrajetRobots(0, r0);
        sim.addTrajetRobots(1, r1);
//
    }
}


