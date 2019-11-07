package cse3063f19p1_abinay_myayin_aaltay;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;


import java.io.File;
import java.nio.file.Paths;

public class Main {

    /**
     * Parses given arguments and starts Monopoly Simulation.
     * Expected argument format:"-key:value"
     */
    public static void main(String[] args) {
        try {
            MonopolyConfig monopolyConfig = new MonopolyConfig(getConfigFile("monopoly.properties"));
            new MonopolyGame(monopolyConfig).start();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Monopoly Simulation disturbed by an expection. Terminating...");
        }
    }

    private static File getConfigFile(String relativePath) {
        String configFolderPath = System.getProperty("user.dir")+ File.separator + "MonopolyGame" + File.separator + "config";
        return new File(configFolderPath + File.separator + relativePath);
    }

}
