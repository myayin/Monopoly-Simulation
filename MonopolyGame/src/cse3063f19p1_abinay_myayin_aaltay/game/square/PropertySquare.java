package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class PropertySquare extends Square {
    private int price;

    public PropertySquare(int location, String name) {
        super(location, name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {

    }

    public static void payRent(int rentPrice, SimulatedPlayer player) {
        player.setBalance(player.getBalance() - rentPrice);
    }

}
