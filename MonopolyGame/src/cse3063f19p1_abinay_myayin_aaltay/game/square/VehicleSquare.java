package cse3063f19p1_abinay_myayin_aaltay.game.square;

/**
 * Represents the Vehicle Square from Monopoly Game.
 */
public class VehicleSquare extends PropertySquare {

    private int rent;

    /**
     * Constructs Vehicle Square
     * @param location location Vehicle Square to be constructed
     * @param name
     * @param buyingPrice buying price of vehicle
     * @param sellingPrice selling price of vehicle
     * @param rent amount of rent
     */
    public VehicleSquare(int location, String name, int buyingPrice, int sellingPrice, int rent) {
        super(location, name, buyingPrice, sellingPrice);
        this.rent = rent;
    }

    /**
     * Gets rent.
     * @return rent
     */
    @Override
    public int getRent() {
        return this.rent;
    }


}
