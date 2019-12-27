package cse3063f19p1_abinay_myayin_aaltay.game.square;

/**
 * Lot Square represents Lot Square from Monopoly Game.
 * Players can buy Lots and when they have all the colored lot group
 * they can build houses on it up to 4 houses
 * after building 4 homes players can build hotels.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class LotSquare extends PropertySquare {

    private LotGroup lotGroup;
    private int baseRentPrice;
    private int buildingLevel;
    private int baseUpgradePrice;

    /**
     * Constructs Lot Square.
     * @param location location Lot Square to be set.
     * @param name
     * @param buyingPrice buying price of lot
     * @param baseUpgradePrice base upgrade price of lot
     * @param sellingPrice selling price of lot
     * @param baseRentPrice base rent price of lot
     */
    public LotSquare(int location, String name, int buyingPrice, int baseUpgradePrice, int sellingPrice, int baseRentPrice) {
        super(location, name, buyingPrice, sellingPrice);
        this.baseRentPrice = baseRentPrice;
        this.buildingLevel = 0;
        this.baseUpgradePrice = baseUpgradePrice;
    }

    /**
     * Gets the rent.
     * If owner of the square owns all the colored lot group, amount of rent is multiplied by 2 and
     * If there are houses or hotels build on the lot, base rent price will be multiplied by a building coefficient
     * @return rent
     */
    @Override
    public int getRent() {
        double coefficient = getRentCoefficient();
        return (int) (coefficient * (buildingLevel == 0 && lotGroup.ownedBy(owner) ? 2 : 1) * baseRentPrice);
    }

    /**
     * Gets buildingLevel representing the houses or hotel on the lot
     * @return
     */
    public int getBuildingLevel() {
        return this.buildingLevel;
    }

    /**
     * Sets the colored lot group of this lot
     * @param lotGroup
     */
    public void setLotGroup(LotGroup lotGroup) {
        this.lotGroup = lotGroup;
    }

    /**
     * Gets the price to build next house or upgrade hotel from houses
     * @return upgrade price
     */
    public int getUpgradePrice() {
        return (buildingLevel == 4 ? 5 : 1) * baseUpgradePrice;
    }

    /**
     * Checks if lot is upgradable or not.
     * @return <code>true</code> if lot is upgradable
     *         <code>false</code> otherwise.
     */
    public boolean canBeUpgraded() {
        if (buildingLevel >= 5) return false;
        if (!this.lotGroup.ownedBy(owner)) return false;
        return this.lotGroup.getLevel() == this.buildingLevel;
    }

    /**
     * Upgrades the lot with the next building.
     */
    public void upgrade() {
        if (!canBeUpgraded()) throw new IllegalStateException("This lot is not upgradable.");

        int buildingPrice = getUpgradePrice();
        if (owner.getBalance() < buildingPrice) throw new IllegalStateException("Owner can't afford this upgrade");

        owner.pay(buildingPrice);

        buildingLevel++;
    }

    /**
     * Downgrades the lot, removes a building from it and returns the value of the building to the owner.
     */
    public void downgrade(){
        if(buildingLevel == 0) throw new IllegalStateException("This lot is not downgradeable.");

        if(buildingLevel == 5){
            buildingLevel = 0;
            owner.setBalance(owner.getBalance() + 5 * baseUpgradePrice);
        }
        else{
            buildingLevel--;
            owner.setBalance(owner.getBalance() + baseUpgradePrice);
        }
    }

    /**
     * Returns the lot group this lot is in.
     * @return lot group
     */
    public LotGroup getLotGroup() {
        return lotGroup;
    }

    /**
     * Generates and returns rent coefficient according to the buildings on the lot.
     * @return rent coefficient
     */
    private double getRentCoefficient() {
        switch (buildingLevel) {
            case 0:
                return 1;
            case 1:
                return 2.5;
            case 2:
                return 6.9;
            case 3:
                return 19.4;
            case 4:
                return 24.3;
            case 5:
                return 30;

            default:
                throw new IllegalStateException("Building level cannot be " + buildingLevel);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(lotGroup.toString()).append("] ").append(getName());
        if(1 <= buildingLevel && buildingLevel <= 4)
            sb.append(' ').append(buildingLevel).append(" House");
        else if(buildingLevel == 5)
            sb.append(" Hotel");
        return sb.toString();
    }
}

