
package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the color groups from Monopoly game.
 * LotGroups hold lot squares and grouping functionality inside.
 */
public class LotGroup {

    private List<LotSquare> lotList = new ArrayList<>();
    private String color;

    /**
     * Constructs Lot group
     * @param color color of the group to be constructed
     */
    public LotGroup(String color) {
        this.color = color;
    }

    /**
     * Adds Lot to the lot group.
     *
     * @param lotSquare lot square to be added inside of the group
     */
    public void appendLot(LotSquare lotSquare) {
        lotSquare.setLotGroup(this);
        lotList.add(lotSquare);
    }

    /**
     * Checks if player has all lot squares inside this group.
     * @param player
     * @return <code>true</code> if player has all lot squares of this lot group
     * <code>false</code> otherwise.
     */
    public boolean ownedBy(SimulatedPlayer player) {
        return lotList.stream().allMatch(lot -> lot.owner != null && lot.owner.equals(player));
    }

    /**
     * Gets the building count/level of this lot group
     * by getting the building level of lot square with the least valuable building(s) in lot group.
     *
     * @return lot group level
     */
    public int getLevel() {
        return lotList.stream().mapToInt(LotSquare::getBuildingLevel).min().orElse(0);
    }

    /**
     * Color of this group.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sells all of the lots in the group.
     */
    public void sellAll() {
        SimulatedPlayer owner = lotList.get(0).getOwner();
        if (!ownedBy(owner)) throw new IllegalStateException("Not all properties are owned by " + owner);
        for (LotSquare lotSquare : lotList) {
            while (lotSquare.getBuildingLevel() > 0)
                lotSquare.downgrade();
            owner.sellProperty(lotSquare);
        }
    }

    @Override
    public String toString() {
        return color;
    }

}
