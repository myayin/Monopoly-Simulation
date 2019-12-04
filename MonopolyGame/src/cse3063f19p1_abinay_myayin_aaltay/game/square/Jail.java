package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class Jail extends Square{
    //It is false, player is just visiting. True when player in jail
    boolean jail=false;
    public static int JailSquareNumber=10;

    public Jail(String name) {
        super(name);
    }

    @Override
    public void performLanding(SimulatedPlayer player) { //burada yalnızca jail squareden normal bir şekilde geçiyormuş gibi düşündüm
        player.setInWaiting(true);
    }
}
