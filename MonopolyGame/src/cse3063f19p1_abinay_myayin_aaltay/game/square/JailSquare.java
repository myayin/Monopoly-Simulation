package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JailSquare extends Square {
    //It is false, player is just visiting. True when player in jail

    public static final int LOCATION = 10;

    private int jailPenalty;
    private Map<SimulatedPlayer, Integer> jailedPlayers;


    public JailSquare(int location, int jailPayment) {
        super(location, "Jail Square");
        this.jailPenalty = jailPayment;
        this.jailedPlayers = new HashMap<>();
    }

    // do nothing since you are a visitor.
    @Override
    public void performLanding(SimulatedPlayer player) {
        System.out.println(player.getPlayerName() + " is visiting jail.");
    }

    public int getJailPenalty() {
        return jailPenalty;
    }

    public void jailPlayer(SimulatedPlayer player) {
        player.getPiece().setCurrentLocation(getLocation());
        jailedPlayers.put(player, 0);
    }

    public void pardonPlayer(SimulatedPlayer player) {
        jailedPlayers.remove(player);
    }

    public boolean isJailed(SimulatedPlayer player) {
        return jailedPlayers.keySet().contains(player);
    }

    public int getJailedDuration(SimulatedPlayer player) {
        return jailedPlayers.get(player);
    }

    public void increaseJailedDuration(SimulatedPlayer player) {
        int currentDuration = jailedPlayers.get(player);
        jailedPlayers.put(player, currentDuration + 1);
    }

}
