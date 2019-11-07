package cse3063f19p1_abinay_myayin_aaltay;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;

public class Main {

    /**
     * Parses given arguments and starts Monopoly Simulation.
     * Expected argument format:"-key:value"
     */
    public static void main(String[] args) {
        try {
            Arguments arguments = new Arguments(args);
            new MonopolyGame(arguments).start();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Monopoly Simulation disturbed by an expection. Terminating...");
        }
    }

}
