package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents the GoToJail Square from Monopoly Game.
 * This square sends players to the jail when they land on it.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class GoToJailSquare extends Square {

    /**
     * Constructs the GoToJail Square.
     * @param location location GoToJailSquare is to be constructed
     */
    public GoToJailSquare(int location) {
        super(location, "GoToJailSquare");
    }

    /**
     * Sends landing players to the jail.
     * @param player
     */
    @Override
    public void performLanding(SimulatedPlayer player) {
        System.out.println(player.getPlayerName() + " is jailed!");
        player.getPiece().getBoard().getJailSquare().jailPlayer(player);
    }
}
