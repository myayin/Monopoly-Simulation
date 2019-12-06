package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public abstract class PropertySquare extends Square {
    private int buyingPrice;
    private int sellingPrice;
    protected SimulatedPlayer owner;

    public PropertySquare(int location, String name, int buyingPrice, int sellingPrice) {
        super(location, name);
        this.buyingPrice = 0;
        this.sellingPrice = 0;
        this.owner = null;
    }

    public abstract int getRent();

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setOwner(SimulatedPlayer owner) {
        this.owner = owner;
    }

    public SimulatedPlayer getOwner() {
        return owner;
    }

    @Override
    public void performLanding(SimulatedPlayer player) {
        if(owner == null){
            if(player.getBalance() >= this.getBuyingPrice()/2){
                player.buyProperty(this);
                this.setOwner(player);
            }
        }
        else{
            payRent(player);
        }
    }

    public void payRent(SimulatedPlayer player) {
        player.pay(this.getRent(), player);
    }

}
