package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LotGroup {

    private List<LotSquare> lotList = new ArrayList<>();
    private Color color;

    public LotGroup(Color color) {
        this.color = color;
    }

    public void appendLot(LotSquare lotSquare) {
        lotSquare.setLotGroup(this);
        lotList.add(lotSquare);
    }

    public boolean ownedBy(SimulatedPlayer player) {
        return lotList.stream().allMatch(lot -> lot.owner != null && lot.owner.equals(player));
    }

    public int getLevel() {
        return lotList.stream().mapToInt(LotSquare::getBuildingLevel).min().orElse(0);
    }

    public Color getColor() {
        return color;
    }

    public void sellAll() {
        SimulatedPlayer owner = lotList.get(0).getOwner();
        if (!ownedBy(owner)) throw new IllegalStateException("Not all properties are owned by " + owner);
        for (LotSquare lotSquare : lotList) {
            while (lotSquare.getBuildingLevel() > 0)
                lotSquare.downgrade();
            owner.sellProperty(lotSquare);
        }
    }

}
