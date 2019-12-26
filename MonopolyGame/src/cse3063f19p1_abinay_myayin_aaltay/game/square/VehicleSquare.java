package cse3063f19p1_abinay_myayin_aaltay.game.square;

public class VehicleSquare extends PropertySquare {

    private int rent;
    public VehicleSquare(int location, String name, int buyingPrice, int sellingPrice, int rent) {
        super(location, name, buyingPrice, sellingPrice);
        this.rent = rent;
    }

    @Override
    public int getRent() {
        return this.rent;
    }


}
