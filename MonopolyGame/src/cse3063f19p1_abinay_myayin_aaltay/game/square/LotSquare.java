package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.awt.*;

//TODO: rewrite the whole class.
public class LotSquare extends PropertySquare {
    //TODO

    private String propertyName;
    private LotGroup lotGroup;
    private int rentPrice;

    public LotSquare(int location, String name, int buyingPrice, int sellingPrice, int rentPrice) {
        super(location, name, buyingPrice, sellingPrice);
        this.rentPrice = rentPrice;
    }

    //TODO: refactor get rent
    @Override
    public int getRent() {
        return lotGroup.ownedBy(owner) ? rentPrice * 2 : rentPrice;
    }

    public void setLotGroup(LotGroup lotGroup) {
        this.lotGroup = lotGroup;
    }

}

