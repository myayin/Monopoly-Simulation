package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.Dice;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.util.stream.Stream;

/**
 * PrintHelper class handles basic printing patterns and routines.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class PrintHelper {

    /**
     * Prints opening message of the simulation.
     */
    public static void printSimulationOpener() {
        printSeperator();
        System.out.println("MONOPOLY SIMULATOR INITIATED");
        printSeperator();
    }

    /**
     * Prints numbers between i and j, with the ',' delimeter wrapped by square brackets.
     * [i..j]
     * @param i starting number
     * @param j finishing number
     */
    public static void printOpenSpots(int i, int j) {
        System.out.print("Rolling for places: [");
        String delimiter = "";
        for (int k = i; k <= j; k++) {
            System.out.print(delimiter);
            System.out.print(k + 1 + "#");
            delimiter = ", ";
        }
        System.out.println("]");
    }

    /**
     * Prints dashed line seperator.
     */
    public static void printSeperator() {
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

    /**
     * Prints the result of thrown dices on board.
     * @param playerName
     * @param dices
     */
    public static void printRoll(String playerName, Dice[] dices) {
        int diceTotal = Stream.of(dices).mapToInt(Dice::getFacingValue).sum();

        System.out.print(playerName + " rolled [");
        String delimiter = "";
        for (Dice dice : dices) {
            System.out.print(delimiter);
            System.out.print(dice.getFacingValue());
            delimiter = " ";
        }
        System.out.println("] into total " + diceTotal);
    }

    /**
     * Prints the result of thrown dices on board.
     * @param player
     * @param dices
     */
    public static void printRoll(SimulatedPlayer player, Dice[] dices) {
        int diceTotal = Stream.of(dices).mapToInt(Dice::getFacingValue).sum();

        System.out.print(player + " rolled [");
        String delimiter = "";
        for (Dice dice : dices) {
            System.out.print(delimiter);
            System.out.print(dice.getFacingValue());
            delimiter = " ";
        }
        System.out.println("] into total " + diceTotal);
    }

}
