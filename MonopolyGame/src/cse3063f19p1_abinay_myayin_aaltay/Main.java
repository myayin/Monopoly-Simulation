package cse3063f19p1_abinay_myayin_aaltay;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;

import java.io.File;

/**
 * Main class.
 * Monopoly Game Simulation. Simulates players and lets them play a simpler and modified version of monopoly game.
 * Players make decisions according to the rules defined throughout the implementation and random number generation.
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class Main {

    /**
     * Holds config parameters.
     */
    public static MonopolyConfig monopolyConfig;

    public static void main(String[] args) {
        try {
            monopolyConfig = MonopolyConfig.readFrom(getConfigPath("monopoly.json"));
            new MonopolyGame(monopolyConfig).start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Monopoly Simulation disturbed by an exception. Terminating...");
        }
    }

    /**
     * Gets config path of the config file by using relativePath passed.
     * @param relativePath source directory relative config path
     * @return the path of config file
     */
    private static String getConfigPath(String relativePath) {
        String configFolderPath = System.getProperty("user.dir") +File.separator +"MonopolyGame"+ File.separator + "config";
        return configFolderPath + File.separator + relativePath;
    }

}
