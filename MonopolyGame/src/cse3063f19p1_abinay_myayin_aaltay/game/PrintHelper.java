package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.Dice;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.util.stream.Stream;

public class PrintHelper {

    public static void printSimulationOpener() {
        printSeperator();
        System.out.println("MONOPOLY SIMULATOR INITIATED");
        printSeperator();
    }

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

    public static void printSeperator() {
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

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
