package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import java.util.stream.Stream;

public class Cup {

    private static final int DICE_COUNT = 2;
    private Dice[] dices;

    public Cup() {
        dices = new Dice[DICE_COUNT];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
    }

    public void rollDices() {
        for (Dice dice : dices) dice.roll();
    }

    public int getTotal() {
        return Stream.of(dices).mapToInt(Dice::getFacingValue).sum();
    }

    public boolean dicesEqual() {
        return Stream.of(dices).distinct().count() == 1L;
    }

    public Dice[] getDices(){
        return dices;
    }

}
