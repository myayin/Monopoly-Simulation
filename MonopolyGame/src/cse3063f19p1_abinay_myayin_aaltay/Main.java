package tr.edu.marun.anilaltay;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;

import java.io.File;

public class Main {

    /**
     * Parses given arguments and starts Monopoly Simulation.
     * Expected argument format:"-key:value"
     */
    public static void main(String[] args) {
        try {
            MonopolyConfig monopolyConfig = MonopolyConfig.readFrom(getConfigPath("monopoly.json"));
            new MonopolyGame(monopolyConfig).start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Monopoly Simulation disturbed by an exception. Terminating...");
        }
    }

    private static String getConfigPath(String relativePath) {
        String configFolderPath = System.getProperty("user.dir") + File.separator + "config";
        return configFolderPath + File.separator + relativePath;
    }

}
