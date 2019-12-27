package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import java.util.stream.Stream;

/**
 * Represents the dice cup from Monopoly Game.
 * A Pure fabricator of dice objects.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class Cup {

    private static final int DICE_COUNT = 2;
    private Dice[] dices;

    /**
     * Initializes dices.
     */
    public Cup() {
        dices = new Dice[DICE_COUNT];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
    }

    /**
     * Rolls dices.
     */
    public void rollDices() {
        for (Dice dice : dices) dice.roll();
    }

    /**
     * Gets the total of facingValues of dices.
     * @return total of facingValues
     */
    public int getTotal() {
        return Stream.of(dices).mapToInt(Dice::getFacingValue).sum();
    }

    /**
     * Checks if dices are equal to each other.
     * @return <code>true</code> if dices are equal;
     *         <code>false</code> otherwise.
     */
    public boolean dicesEqual() {
        return Stream.of(dices).distinct().count() == 1L;
    }

    /**
     * Gets dices.
     * @return array of Dice
     */
    public Dice[] getDices(){
        return dices;
    }

}
