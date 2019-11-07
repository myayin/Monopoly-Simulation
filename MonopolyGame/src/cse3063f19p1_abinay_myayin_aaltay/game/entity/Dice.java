package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import java.util.Random;

public class Dice {

    private Random randomNumberGenerator;
    private int facingValue;
    private int previousFacingValue;

    public Dice() {
        this.randomNumberGenerator = new Random();
        this.facingValue = -1;
    }

    public int roll() {
        int roll = randomNumberGenerator.nextInt(6) + 1;
        previousFacingValue = facingValue;
        facingValue = roll;
        return roll;
    }

    public int getFacingValue() {
        return facingValue;
    }

    public int getPreviousFacingValue() {
        return previousFacingValue;
    }

}
