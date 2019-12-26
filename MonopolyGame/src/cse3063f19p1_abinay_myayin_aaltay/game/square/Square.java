package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public abstract class Square {

    private String name;
    private int location;

    public Square(int location, String name) {
        this.location = location;
        this.name = name;
    }

    public int getLocation() {return location;}

    public String getName() {
        return name;
    }

    public abstract void performLanding(SimulatedPlayer player);

    @Override
    public String toString() {
        return name;
    }
}
