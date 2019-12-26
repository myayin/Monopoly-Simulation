package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import java.util.Random;

/**
 * Represents a standard dice.
 * @author anilaltay
 */
public class Dice {

    private Random randomNumberGenerator;
    private int facingValue;
    private int previousFacingValue;

    /**
     * Constructs a Dice entity.
     * Constructs and sets random number generator.
     * Initializes facingValue.
     */
    public Dice() {
        this.randomNumberGenerator = new Random();
        this.facingValue = -1;
    }

    /**
     * Rolls the dice.
     * @return facingValue of this dice
     */
    public int roll() {
        int roll = randomNumberGenerator.nextInt(6) + 1;
        previousFacingValue = facingValue;
        facingValue = roll;
        return roll;
    }

    /**
     * Gets the facing value of this dice.
     * @return facing value of this dice
     */
    public int getFacingValue() {
        return facingValue;
    }

    /**
     * Gets the previous facing value of this dice.
     * @return previous facing value of this dice
     */
    public int getPreviousFacingValue() {
        return previousFacingValue;
    }

}
