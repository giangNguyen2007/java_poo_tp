import simulation.robot.*;
import simulation.environment.Carte;
import simulation.environment.Case;
import simulation.DonneeSimulation;

public class TestDonneSimulation {

    public static void main(String[] args) {


        Carte carte = new Carte(20, 7, 10);

        Robot rb1 = new RobotRoue(0, carte.getCase(2, 2), 10);
        Robot rb2 = new RobotRoue(1, carte.getCase(3, 4), 10);

        Robot[] robots = new Robot[] {rb1, rb2};

        DonneeSimulation donneeSimulation = new DonneeSimulation(carte, robots);

        Case c = donneeSimulation.getRobotPosition(1);

        System.out.println("Hello " + c.getLigne() + " " + c.getColonne());


        c = donneeSimulation.getRobotPosition(1);

        System.out.println("Hello " + c.getLigne() + " " + c.getColonne());
        }
}

