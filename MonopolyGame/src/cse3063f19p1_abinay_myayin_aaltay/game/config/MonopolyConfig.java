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
    private int utilitySquareCount;

    @Expose
    private int vehicleSquareCount;

    @Expose
    private String[] lotNames;

    @Expose
    private String[] utilityNames;

    @Expose
    private String[] vehicleNames;

    @Expose
    private LotGroupDefinition[] lotGroups;

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
        startingBalance = 200_000;
        goSalary = 200;
        taxPayment = 100;
        players = new String[]{"Anıl", "Ayten", "Merve", "Murat"};
        sleepAfterTurn = 0.5;
        utilitySquareCount = 2;
        vehicleSquareCount = 3;

        lotNames = new String[]{"Kasımpaşa", "Dolapdere", "Sultanahmet", "Karaköy",
                "Sirkeci", "Beyoğlu", "Beşiktaş", "Taksim",
                "Harbiye", "Sisli", "Mecidiyeköy", "Bostancı",
                "Erenköy", "Caddebostan", "Nişantaşı", "Teşvikiye",
                "Maçka", "Levent", "Etiler", "Bebek",
                "Tarabya", "Yeniköy"};
        utilityNames = new String[]{"Sular Dairesi",
                "Elektrik İdaresi",
                "Vergi Dairesi",
                "Doğalgaz Dağıtım Firması",
                "İnternet Servis Sağlayıcısı"};
        vehicleNames = new String[]{"Haydarpaşa Treni",
                "Kadıköy Vapuru", "Kabataş Vapuru",
                "Sirkeci Treni", "Marmaray", "Metrobüs"};

        lotGroups = new LotGroupDefinition[]{
                new LotGroupDefinition("RED",
                        new LotDefinition(1, 6000, 5000, 6000, 6000),
                        new LotDefinition(3, 6000, 5000, 6000, 6000)
                ),
                new LotGroupDefinition("BLUE",
                        new LotDefinition(6, 10_000, 5000, 10_000, 1000),
                        new LotDefinition(8, 10_000, 5000, 10_000, 1000),
                        new LotDefinition(9, 12_000, 5000, 12_000, 1200)
                ),
                new LotGroupDefinition("MAGENTA",
                        new LotDefinition(11, 14_000, 10_000, 14_000, 1400),
                        new LotDefinition(13, 14_000, 10_000, 14_000, 1400),
                        new LotDefinition(14, 16_000, 10_000, 16_000, 1600)
                ),
                new LotGroupDefinition("ORANGE",
                        new LotDefinition(16, 18_000, 10_000, 18_000, 1800),
                        new LotDefinition(18, 18_000, 10_000, 18_000, 1800),
                        new LotDefinition(19, 20_000, 10_000, 20_000, 2000)
                ),
                new LotGroupDefinition("CYAN",
                        new LotDefinition(21, 22_000, 15_000, 22_000, 2200),
                        new LotDefinition(23, 22_000, 15_000, 22_000, 2200),
                        new LotDefinition(24, 24_000, 15_000, 24_000, 2400)
                ),
                new LotGroupDefinition("YELLOW",
                        new LotDefinition(26, 26_000, 15_000, 26_000, 2600),
                        new LotDefinition(27, 26_000, 15_000, 26_000, 2600),
                        new LotDefinition(29, 28_000, 15_000, 28_000, 2600)
                ),
                new LotGroupDefinition("GREEN",
                        new LotDefinition(31, 30_000, 20_000, 30_000, 3000),
                        new LotDefinition(32, 30_000, 20_000, 30_000, 3000),
                        new LotDefinition(34, 32_000, 20_000, 32_000, 3000)
                ),
                new LotGroupDefinition("GRAY",
                        new LotDefinition(38, 35_000, 20_000, 35_000, 3500),
                        new LotDefinition(39, 40_000, 20_000, 40_000, 4000)
                )
        };
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

    public int getUtilitySquareCount() {
        return utilitySquareCount;
    }

    public int getVehicleSquareCount() {
        return vehicleSquareCount;
    }

    public String[] getLotNames() {
        return lotNames;
    }

    public String[] getUtilityNames() {
        return utilityNames;
    }

    public String[] getVehicleNames() {
        return vehicleNames;
    }

    public LotGroupDefinition[] getLotGroups() {
        return lotGroups;
    }

}