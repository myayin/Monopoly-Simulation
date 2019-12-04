package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

public  class LotSquare extends Square {
    //TODO

    private String propertyName;
    private boolean isOwned = false;
    private SimulatedPlayer owner;
    private int color;
    private int numOfHouse = 0;
    private int numOfHotel = 0;
    private int buyingPrice;
    private int incomeLand;
    private  int incomeSetLand;
    private int income1;
    private int income2;
    private int income3;
    private int income4;
    private int incomeHotel;
    private int buildPriceHome;
    private int buildPriceHotel;
    private  int mortgage;
    private int cancelMortgage;
    private int rentPrice;


    public LotSquare(String propertyName, int color, int buyingPrice, int incomeLand, int incomeSetLand, int income1,
                     int income2, int income3, int income4, int incomeHotel, int buildPriceHome, int buildPriceHotel, int mortgage, int cancelMortgage) {
        super("PropertySquare");

        this.propertyName = propertyName;
        this.color=color;
        this.rentPrice=incomeLand;
        this.buyingPrice=buyingPrice;
        this.incomeLand = incomeLand;
        this.incomeSetLand=incomeSetLand;
        this.income1 = income1;
        this.income2 = income2;
        this.income3 = income3;
        this.income4 = income4;
        this.incomeHotel = incomeHotel;
        this.buildPriceHome = buildPriceHome;
        this.buildPriceHotel = buildPriceHotel;
        this.mortgage = mortgage;
        this.cancelMortgage=cancelMortgage;

    }


    @Override
    public void performLanding(SimulatedPlayer player) {
        if (!isOwned) {
            if (player.getBalance() / 2 > buyingPrice) {
            buyProperty(player); }
        }
         else
            if (owner == player) {
            if(hasUserAllSameColor(player))
            if (numOfHouse < 5) {
                buildHome(player);

            }


        }
    }

    public void buildHome(SimulatedPlayer player) {
        int numberOfHouseCanBuild = 4 - numOfHouse;
        int numberOfHousesBuild = (int) ((player.getBalance() / 3) / buildPriceHome);
        if (numberOfHouseCanBuild > numberOfHousesBuild) {
            numOfHouse = 4;
            player.setBalance(player.getBalance() - numberOfHousesBuild * buildPriceHome);
        } else {

        }
    }

     public void buyProperty(SimulatedPlayer player) {

             int currentBalance = player.getBalance();
             player.setBalance(currentBalance - buyingPrice);
             owner = player;
             isOwned = true;


     }
    public boolean hasUserAllSameColor (SimulatedPlayer player) {
        switch (color) {
            case 0:
                if (player.getLotSquare().get(0).color == 2)
                    return true;
            case 1:
                if (player.getLotSquare().get(1).color == 3)
                    return true;
            case 2:
                if (player.getLotSquare().get(2).color == 3)
                    return true;
            case 3:
                if (player.getLotSquare().get(3).color == 3)
                    return true;
            case 4:
                if (player.getLotSquare().get(4).color == 3)
                    return true;
            case 5:
                if (player.getLotSquare().get(5).color == 3)
                    return true;
            case 6:
                if (player.getLotSquare().get(6).color == 3)
                    return true;
            case 7:
                if (player.getLotSquare().get(7).color == 3)
                    return true;

        }
        return false;
    }

}

