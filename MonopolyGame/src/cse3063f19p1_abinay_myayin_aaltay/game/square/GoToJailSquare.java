package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class GoToJailSquare extends Square {

    public GoToJailSquare(int location) {
        super(location, "GoToJailSquare");
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        System.out.println(player.getPlayerName() + " is jailed!");
        player.getPiece().getBoard().getJailSquare().jailPlayer(player);
    }
}
