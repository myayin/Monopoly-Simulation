package cse3063f19p1_abinay_myayin_aaltay.game.square;

import cse3063f19p1_abinay_myayin_aaltay.game.entity.Cup;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;

/**
 * Represents Utility Square from Monopoly Game.
 * Landing players rolls two dices and pays rent based on total facing value of these dices
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class UtilitySquare extends PropertySquare {

    /**
     * Constructs Utility Square.
     *
     * @param location     location utility square to be constructed
     * @param name
     * @param buyingPrice  buying price of this square
     * @param sellingPrice selling price of this square
     */
    public UtilitySquare(int location, String name, int buyingPrice, int sellingPrice) {
        super(location, name, buyingPrice, sellingPrice);
    }

    /**
     * Gets the rent
     * If owner of the utility square owns all of the utility squares on board value of facing dices
     * are rent is multiplied by 40_000
     * else it is multiplied by 10_000
     * @return amount of rent
     */
    @Override
    public int getRent() {
        Cup dummyCup = new Cup();
        dummyCup.rollDices();
        return (owner.hasAllUtilities() ? 40_000 : 10_000) * dummyCup.getTotal();
    }


}
