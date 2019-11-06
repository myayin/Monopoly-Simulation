package entity;

import java.util.Random;

public class Dice {

    private Random rng;
    private int prevValue;

    public Dice() {
        this.rng = new Random();
        this.prevValue = -1;
    }

    public int roll() {
        int roll = rng.nextInt(6) + 1;
        prevValue = roll;
        return roll;
    }

}
