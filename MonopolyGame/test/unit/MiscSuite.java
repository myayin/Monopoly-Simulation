import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class MiscSuite {

    @Test
    @DisplayName("should load configs correctly.")
    public void configTest() throws Exception {
        URL configURL = getClass().getClassLoader().getResource("resources/config/monopoly.properties");
        Assertions.assertNotNull(configURL);
        URI configURI = configURL.toURI();

        File file = new File(configURI);

        MonopolyConfig monopolyConfig = new MonopolyConfig(file);

        Assertions.assertEquals(5, monopolyConfig.getTaxSquareCount());
        Assertions.assertEquals(2000, monopolyConfig.getStartingBalance());
        Assertions.assertEquals(200, monopolyConfig.getGoSalary());
        Assertions.assertEquals(100, monopolyConfig.getTaxPayment());
        Assertions.assertEquals(0.5, monopolyConfig.getSleepAfterTurn());
        Assertions.assertEquals(0.25, monopolyConfig.getSleepAfterPieceMove());
        Assertions.assertArrayEquals(
                new String[]{"Anil", "Ayten", "Merve", "Murat"},
                monopolyConfig.getPlayers());
    }

}
