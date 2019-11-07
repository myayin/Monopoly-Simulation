package cse3063f19p1_abinay_myayin_aaltay.game.config;

import com.google.gson.annotations.Expose;
import cse3063f19p1_abinay_myayin_aaltay.game.PrintHelper;

import java.io.IOException;

public class MonopolyConfig extends BaseConfig {

    public static MonopolyConfig readFrom(String path) {
        return (MonopolyConfig) new MonopolyConfig(path).readConfig();
    }

    @Expose
    private int taxSquareCount;

    @Expose
    private int startingBalance;

    @Expose
    private int goSalary, taxPayment;

    @Expose
    private String[] players;

    @Expose
    private double sleepAfterTurn;

    @Expose
    private double sleepAfterPieceMove;

    public MonopolyConfig(String path) {
        super(path);
    }

    @Override
    public BaseConfig readConfig() {
        PrintHelper.printSeperator();
        System.out.println("Reading config from " + this.path);

        return super.readConfig();
    }

    @Override
    public void writeConfig() throws IOException {
        System.out.println("Writing config to " + this.path);

        super.writeConfig();
    }

    @Override
    public void resetConfig() {
        System.out.println("Unexpected MonopolyConfig format. Resetting config ...");

        taxSquareCount = 5;
        startingBalance = 2000;
        goSalary = 200;
        taxPayment = 100;
        players = new String[]{"iGoodie", "Pomapii", "Neetaa", "Diavali"};
        sleepAfterTurn = 0.5;
        sleepAfterPieceMove = 0.25;
    }

    public int getTaxSquareCount() {
        return taxSquareCount;
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public int getGoSalary() {
        return goSalary;
    }

    public int getTaxPayment() {
        return taxPayment;
    }

    public String[] getPlayers() {
        return players;
    }

    public double getSleepAfterTurn() {
        return sleepAfterTurn;
    }

    public double getSleepAfterPieceMove() {
        return sleepAfterPieceMove;
    }

}
