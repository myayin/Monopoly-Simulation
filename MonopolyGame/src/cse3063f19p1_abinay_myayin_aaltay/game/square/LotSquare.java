package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

//TODO: rewrite the whole class.
public class LotSquare extends Square {
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


    public LotSquare(int location,String propertyName, int color, int buyingPrice, int incomeLand, int incomeSetLand, int income1,
                     int income2, int income3, int income4, int incomeHotel, int buildPriceHome, int buildPriceHotel, int mortgage, int cancelMortgage) {
        super(location,"PropertySquare");

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
        if (!isOwned) { // sahipsizse
            if (player.getBalance() / 2 > buyingPrice) {
                buyProperty(player);
            }
        } else if (owner == player) { //sahibi player ise
            if (hasUserAllSameColor(player)) {
                buildHome(player);
            }
            if (hasUserAllSameColor(player) && canBuildHotel(player))
                buildHotel(player);

        } else { //sahibi var ancak player değil
            PropertySquare.payRent(this.rentPrice, player);
        }
    }

    public boolean canBuildHotel(SimulatedPlayer player) {
        int count = 0;
        for (int i = 0; i < player.getLotSquare().size(); i++) {

            if (player.getLotSquare().get(i).color == color) {
                if (numOfHouse == 4) {
                    count++;
                }
                if (numOfHotel != 0) return true; //oteli varsa zaten 4er evi vardır
            }
        }

        if ((color == 0 || color == 7) && count == 2)
            return true;

        if ((color != 0 && color != 7) && count == 3)
            return true;

        return false;
    }


    public void buildHotel(SimulatedPlayer player) {
        numOfHouse = 0;
        if (player.getBalance() / 3 > buildPriceHotel) {
            numOfHotel = 1;
            player.setBalance(player.getBalance() - buildPriceHotel);
        }

    }

    public void buildHome(SimulatedPlayer player) {
        int numberOfHousesBuild = 4 - numOfHouse;
        int numberOfHousesCanBuild = (int) ((player.getBalance() / 3) / buildPriceHome);
        if (numberOfHousesCanBuild > numberOfHousesBuild) {
            numOfHouse = 4;
            player.setBalance(player.getBalance() - numberOfHousesBuild * buildPriceHome);
            this.rentPrice = income4;
        } else {
            numOfHouse += (int) numberOfHousesCanBuild;
            player.setBalance(player.getBalance() - numberOfHousesCanBuild * buildPriceHome);
            switch (numOfHouse) {
                case 0:
                    this.rentPrice = rentPrice;
                case 1:
                    this.rentPrice = income1;
                case 2:
                    this.rentPrice = income2;
                case 3:
                    this.rentPrice = income3;
                case 4:
                    this.rentPrice = income4;

            }

        }
    }

     public void buyProperty(SimulatedPlayer player) {

             int currentBalance = player.getBalance();
             player.setBalance(currentBalance - buyingPrice);
             owner = player;
             isOwned = true;


    }
    public void arrangeNumOfProp(SimulatedPlayer player){
        switch(color){
            case 0:
                player.getNumOfProperties()[0]++;
            case 1:
                player.getNumOfProperties()[1]++;
            case 2:
                player.getNumOfProperties()[2]++;
            case 3:
                player.getNumOfProperties()[3]++;
            case 4:
                player.getNumOfProperties()[4]++;
            case 5:
                player.getNumOfProperties()[5]++;
            case 6:
                player.getNumOfProperties()[6]++;
            case 7:
                player.getNumOfProperties()[7]++;
        }
    }

    public boolean hasUserAllSameColor(SimulatedPlayer player) {

        switch (color) {
            case 0:
                if (player.getNumOfProperties()[0] == 2)
                    return true;
            case 1:
                if (player.getNumOfProperties()[1] == 3)
                    return true;
            case 2:
                if (player.getNumOfProperties()[2] == 3)
                    return true;
            case 3:
                if (player.getNumOfProperties()[3] == 3)
                    return true;
            case 4:
                if (player.getNumOfProperties()[4] == 3)
                    return true;
            case 5:
                if (player.getNumOfProperties()[5] == 3)
                    return true;
            case 6:
                if (player.getNumOfProperties()[6]== 3)
                    return true;
            case 7:
                if (player.getNumOfProperties()[7] == 2)
                    return true;

        }
        return false;
    }

    public int getSellingPrice(){ //TODO
        return buyingPrice*2;
    }

    public SimulatedPlayer getOwner() {
        return owner;
    }

    public void setOwner(SimulatedPlayer owner) {
        this.owner = owner;
    }
}

