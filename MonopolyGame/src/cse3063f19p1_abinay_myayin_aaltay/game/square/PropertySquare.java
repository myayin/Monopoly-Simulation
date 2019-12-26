package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents the PropertySquare from Monopoly Game.
 * Players can buy property squares.
 * Visiting players pay rent to the owner of the Property.
 */
public abstract class PropertySquare extends Square {
    private int buyingPrice;
    private int sellingPrice;
    protected SimulatedPlayer owner;

    /**
     * Constructs PropertySquare
     * @param location location that property square will be set
     * @param name name of the square
     * @param buyingPrice buying price of the property
     * @param sellingPrice selling price of the property
     */
    public PropertySquare(int location, String name, int buyingPrice, int sellingPrice) {
        super(location, name);
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.owner = null;
    }

    public abstract int getRent();

    /**
     * Sets buying price of the property.
     * @param buyingPrice buying price of the property
     */
    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    /**
     * Sets selling price of the property.
     * @param sellingPrice selling price of the property
     */
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets buying price.
     * @return buying price
     */
    public int getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * Gets selling price.
     * @return selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets owner of property
     * @param owner
     */
    public void setOwner(SimulatedPlayer owner) {
        this.owner = owner;
    }

    /**
     * Gets owner of property.
     * @return owner of property
     */
    public SimulatedPlayer getOwner() {
        return owner;
    }

    /**
     * if property is not owned by anyone performs the buying routine
     * else landing players pays rent to the owner
     * @param player
     */
    @Override
    public void performLanding(SimulatedPlayer player) {
        if(owner == null){
            if(player.getBalance() >= this.getBuyingPrice()/2){
                player.buyProperty(this);
                this.setOwner(player);
                System.out.println(player.getPlayerName()+" bought "+this.getName()+" property for "+this.getBuyingPrice()+"$");
            }
        }
        else{
            payRent(player);
            System.out.println(player.getPlayerName()+" paid "+this.getRent()+"$ rent to "+getOwner().getPlayerName());
        }
    }

    /**
     * Pays rent to the owner of property.
     * @param player
     */
    public void payRent(SimulatedPlayer player) {
        player.pay(this.getRent(), owner);
    }

    public void onSold(){
        owner = null;
    }

}
