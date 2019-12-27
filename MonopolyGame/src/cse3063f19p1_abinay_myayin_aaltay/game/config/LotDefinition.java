package cse3063f19p1_abinay_myayin_aaltay.game.config;

import com.google.gson.annotations.Expose;

/**
 * Exposable JSON config definition of
 * {@link cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare LotSquare}.
 */
public class LotDefinition {

    @Expose
    public int location;

    @Expose
    public int buyingPrice;

    @Expose
    public int baseUpgradePrice;

    @Expose
    public int sellingPrice;

    @Expose
    public int baseRentPrice;

    public LotDefinition(int location, int buyingPrice,
                         int baseUpgradePrice, int sellingPrice, int baseRentPrice) {
        this.location = location;
        this.buyingPrice = buyingPrice;
        this.baseUpgradePrice = baseUpgradePrice;
        this.sellingPrice = sellingPrice;
        this.baseRentPrice = baseRentPrice;
    }

}