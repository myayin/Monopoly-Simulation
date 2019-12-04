package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class GoToJail extends Square{

    public GoToJail(String name) {
        super(name);
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        player.setInJail(true);

    }
}