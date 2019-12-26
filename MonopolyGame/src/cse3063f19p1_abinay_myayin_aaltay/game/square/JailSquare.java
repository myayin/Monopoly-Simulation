package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents the Jail Square from Monopoly Game.
 * Holds players inside and jailed players cant play their turns, until they pay the penalty.
 */
public class JailSquare extends Square {
    //It is false, player is just visiting. True when player in jail

    public static final int LOCATION = 10;

    private int jailPenalty;
    private Map<SimulatedPlayer, Integer> jailedPlayers;


    /**
     * Constructs JailSquare.
     * @param location location JailSquare is to be set
     * @param jailPayment penalty that Players should pay to get out of jail
     */
    public JailSquare(int location, int jailPayment) {
        super(location, "Jail Square");
        this.jailPenalty = jailPayment;
        this.jailedPlayers = new HashMap<>();
    }

    /**
     * Performs visitor routine for landing players.
     * @param player
     */
    @Override
    public void performLanding(SimulatedPlayer player) {
        System.out.println(player.getPlayerName() + " is visiting jail.");
    }

    /**
     * Gets jail penalty.
     * @return jail penalty
     */
    public int getJailPenalty() {
        return jailPenalty;
    }

    /**
     * Jails the passed player.
     * @param player player to be jailed
     */
    public void jailPlayer(SimulatedPlayer player) {
        player.getPiece().setCurrentLocation(getLocation());
        jailedPlayers.put(player, 0);
    }

    /**
     * Releases/pardons the jailed player.
     * @param player
     */
    public void pardonPlayer(SimulatedPlayer player) {
        jailedPlayers.remove(player);
    }

    /**
     * Checks if player is jailed.
     * @param player player to be checked
     * @return <code>true</code> if player is jailed
     *         <code>false</code> otherwise
     */
    public boolean isJailed(SimulatedPlayer player) {
        return jailedPlayers.keySet().contains(player);
    }

    /**
     * Gets the count of rounds that player has spend in jail.
     * @param player
     * @return count of rounds spend in jail.
     */
    public int getJailedDuration(SimulatedPlayer player) {
        return jailedPlayers.get(player);
    }

    /**
     * Increases the count of rounds that player has spend in jail by 1.
     * @param player
     */
    public void increaseJailedDuration(SimulatedPlayer player) {
        int currentDuration = jailedPlayers.get(player);
        jailedPlayers.put(player, currentDuration + 1);
    }

}
