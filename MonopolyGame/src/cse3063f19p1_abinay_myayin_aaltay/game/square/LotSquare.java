package cse3063f19p1_abinay_myayin_aaltay.game.square;

public class LotSquare extends PropertySquare {

    private LotGroup lotGroup;
    private int baseRentPrice;
    private int buildingLevel;
    private int baseUpgradePrice;

    public LotSquare(int location, String name, int buyingPrice, int baseUpgradePrice, int sellingPrice, int baseRentPrice) {
        super(location, name, buyingPrice, sellingPrice);
        this.baseRentPrice = baseRentPrice;
        this.buildingLevel = 0;
        this.baseUpgradePrice = baseUpgradePrice;
    }

    @Override
    public int getRent() {
        double coefficient = getRentCoefficient();
        return (int) (coefficient * (buildingLevel == 0 && lotGroup.ownedBy(owner) ? 2 : 1) * baseRentPrice);
    }

    public int getBuildingLevel() {
        return this.buildingLevel;
    }

    public void setLotGroup(LotGroup lotGroup) {
        this.lotGroup = lotGroup;
    }

    public int getUpgradePrice() {
        return (buildingLevel == 4 ? 5 : 1) * baseUpgradePrice;
    }

    public boolean canBeUpgraded() {
        if (buildingLevel >= 5) return false;
        if (!this.lotGroup.ownedBy(owner)) return false;
        return this.lotGroup.getLevel() == this.buildingLevel;
    }

    public void upgrade() {
        if (!canBeUpgraded()) throw new IllegalStateException("This lot is not upgradable.");

        int buildingPrice = getUpgradePrice();
        if (owner.getBalance() < buildingPrice) throw new IllegalStateException("Owner can't afford this upgrade");

        owner.pay(buildingPrice);

        buildingLevel++;
    }
    
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

    public LotGroup getLotGroup() {
        return lotGroup;
    }

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
        sb.append("[").append(lotGroup.getColor()).append("] ").append(getName());
        if(1 <= buildingLevel && buildingLevel <= 4)
            sb.append(' ').append(buildingLevel).append(" House");
        else if(buildingLevel == 5)
            sb.append(" Hotel");
        return sb.toString();
    }
}

