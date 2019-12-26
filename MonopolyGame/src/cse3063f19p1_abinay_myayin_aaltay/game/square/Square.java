package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents square from Monopoly Game.
 * Abstraction for all Squares.
 */
public abstract class Square {

    private String name;
    private int location;

    /**
     * Constructs Square.
     * @param location location square will be constructed
     * @param name name of the square
     */
    public Square(int location, String name) {
        this.location = location;
        this.name = name;
    }

    /**
     * Gets the location information of the square.
     * @return location of square
     */
    public int getLocation() {return location;}

    /**
     * Gets the name of the square.
     * @return name of square.
     */
    public String getName() {
        return name;
    }

    public abstract void performLanding(SimulatedPlayer player);

    @Override
    public String toString() {
        return name;
    }
}
