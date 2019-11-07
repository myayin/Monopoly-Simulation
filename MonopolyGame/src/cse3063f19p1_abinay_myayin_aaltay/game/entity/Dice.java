package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import java.util.Random;

public class Dice {

    private Random rng;
    private int value;
    private int previousValue;

    public Dice() {
        this.rng = new Random();
        this.value = -1;
    }

    public int roll() {
        int roll = rng.nextInt(6) + 1;
        previousValue = value;
        value = roll;
        return roll;
    }

    public int getValue() {
        return value;
    }

    public int getPreviousValue() {
        return previousValue;
    }

}
