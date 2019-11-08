package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    Dice dice = new Dice();
    @Test
    void roll() {
        assertTrue(dice.roll() <= 6 && dice.roll() >= 1);
    }


    @Test
    void getFacingValue() {
    }

    @Test
    void getPreviousFacingValue() {
    }
}