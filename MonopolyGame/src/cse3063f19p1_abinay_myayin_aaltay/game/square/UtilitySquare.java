package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.Cup;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public class UtilitySquare extends PropertySquare {

    public UtilitySquare(int location, String name, int buyingPrice, int sellingPrice) {
        super(location, name, buyingPrice, sellingPrice);
    }

    @Override
    public int getRent() {
        Cup dummyCup = new Cup();
        dummyCup.rollDices();
        return (owner.hasAllUtilities() ? 40_000 : 10_000) * dummyCup.getTotal();
    }


}
