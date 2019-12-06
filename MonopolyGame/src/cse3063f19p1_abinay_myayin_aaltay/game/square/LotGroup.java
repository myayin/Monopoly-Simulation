package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LotGroup {

    List<LotSquare> lotList = new ArrayList<>();
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

}
