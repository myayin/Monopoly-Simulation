package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.config.LotDefinition;
import cse3063f19p1_abinay_myayin_aaltay.game.config.LotGroupDefinition;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;
import cse3063f19p1_abinay_myayin_aaltay.game.square.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * BoardBuilder is used to build instances of
 * Board object from values configured by the setters.
 *
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public class BoardBuilder {


    public static final int COUNT_TOTAL_SQUARE = 40;
    public static final int COUNT_TOTAL_DICE = 2;

    private int taxSquareSize;
    private int taxPayment, goSalary;
    private int utilitySquareSize;
    private int vehicleSquareSize;
    private String[] lotNames;
    private String[] utilityNames;
    private String[] vehicleNames;
    private LotGroupDefinition[] lotGroups;

    /**
     * Creates an instance of BoardBuilder.
     */
    public BoardBuilder() {
    }

    /**
     * Sets the taxSquareSize, count of Tax Squares on the board.
     *
     * @param taxSquareSize count of Tax Squares on the board
     * @return this builder.
     */
    public BoardBuilder setTaxSquareSize(int taxSquareSize) {
        this.taxSquareSize = taxSquareSize;
        return this;
    }

    /**
     * Sets the goSalary, amount of money paid when player passes or lands on go square.
     *
     * @param goSalary amount of money paid to the player as Salary
     * @return this builder.
     */
    public BoardBuilder setGoSalary(int goSalary) {
        this.goSalary = goSalary;
        return this;
    }

    /**
     * Sets the taxPayment.
     *
     * @param taxPayment amount of tax money paid by the player
     * @return this builder.
     */
    public BoardBuilder setTaxPayment(int taxPayment) {
        this.taxPayment = taxPayment;
        return this;
    }

    /**
     * Sets the taxSquareSize.
     *
     * @param utilitySquareSize count of Utility Squares on the board
     * @return this builder.
     */
    public BoardBuilder setUtilitySquareSize(int utilitySquareSize) {
        this.utilitySquareSize = utilitySquareSize;
        return this;
    }

    /**
     * Sets the vehicleSquareSize.
     *
     * @param vehicleSquareSize count of Vehicle Squares on the board
     * @return this builder.
     */
    public BoardBuilder setVehicleSquareSize(int vehicleSquareSize) {
        this.vehicleSquareSize = vehicleSquareSize;
        return this;
    }

    public void setLotNames(String[] lotNames) {
        this.lotNames = lotNames;
    }

    public void setUtilityNames(String[] utilityNames) {
        this.utilityNames = utilityNames;
    }

    public void setVehicleNames(String[] vehicleNames) {
        this.vehicleNames = vehicleNames;
    }

    public void setLotGroups(LotGroupDefinition[] lotGroups) {
        this.lotGroups = lotGroups;
    }

    /**
     * Calls setters with the parameters given in the passed config.
     *
     * @param config MonopolyConfig object that is going to be used to fill parameters of setters
     * @return this builder
     */
    public BoardBuilder withConfig(MonopolyConfig config) {
        setTaxSquareSize(config.getTaxSquareCount());
        setGoSalary(config.getGoSalary());
        setTaxPayment(config.getTaxPayment());
        setUtilitySquareSize(config.getUtilitySquareCount());
        setVehicleSquareSize(config.getVehicleSquareCount());
        setLotNames(config.getLotNames());
        setUtilityNames(config.getUtilityNames());
        setVehicleNames(config.getVehicleNames());
        setLotGroups(config.getLotGroups());

        return this;
    }

    /**
     * Returns an instance of Board created from the fields set on this builder.
     *
     * @return A Board.
     */
    public Board build() {
        Board board = new Board();
        Random rnd = new Random();

        //Builds initial square slots.
        List<Square> squares = new ArrayList<>(40);
        for (int i = 0; i < 40; i++) {
            squares.add(null);
        }

        List<String> lotNames = new ArrayList<>(List.of(this.lotNames));
        List<String> utilityNames = new ArrayList<>(List.of(this.utilityNames));
        List<String> vehicleNames = new ArrayList<>(List.of(this.vehicleNames));

        // Add lot groups and their respected lot squares
        for (LotGroupDefinition lotGroupDefinition : lotGroups) {
            LotGroup lotGroup = new LotGroup(lotGroupDefinition.color);
            for (LotDefinition lotDefinition : lotGroupDefinition.lots) {
                LotSquare lot = new LotSquare(
                        lotDefinition.location,
                        lotNames.remove(rnd.nextInt(lotNames.size())),
                        lotDefinition.buyingPrice,
                        lotDefinition.baseUpgradePrice,
                        lotDefinition.sellingPrice,
                        lotDefinition.baseRentPrice
                );
                lotGroup.appendLot(lot);
                squares.add(lot);
            }
        }

        //Sets GoSquare to location 0, JailSquare to location 10 and GoToJailSquare to location 30.
        squares.set(0, new GoSquare(goSalary));
        squares.set(10, new JailSquare(10, 50));
        squares.set(30, new GoToJailSquare(30));

        //Gets the empty locations left.
        List<Integer> emptyLocations = new ArrayList<>();
        for (int i = 0; i < squares.size(); i++) {
            if (squares.get(i) == null) emptyLocations.add(i);
        }

        //If there are more Tax Squares to be placed than empty squares throws error.
        if (taxSquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Tax square size is given more than empty locations.");

        //Places Tax Squares on the board.
        for (int i = 0; i < taxSquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));
            TaxSquare taxSquare = new TaxSquare(location, taxPayment);
            squares.set(location, taxSquare);
        }

        //If there are more Utility Squares to be placed than empty squares throws error.
        if (utilitySquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Utility square size is given more than empty locations.");

        //Places Utility Squares on the board.
        for (int i = 0; i < utilitySquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));
            UtilitySquare utilitySquare = new UtilitySquare(location,
                    utilityNames.remove(rnd.nextInt(utilityNames.size())),
                    10_000, 8_000); //TODO: refactor constant assignment

            squares.set(location, utilitySquare);
        }

        //If there are more Vehicle Squares to be placed than empty squares throws error.
        if (vehicleSquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Vehicle square size is given more than empty locations.");

        //Places Vehicle Squares on the board.
        for (int i = 0; i < vehicleSquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));

            VehicleSquare vehicleSquare = new VehicleSquare(location,
                    vehicleNames.remove(rnd.nextInt(vehicleNames.size())),
                    10_000, 8_000, 1000); //TODO: refactor constant assignment

            squares.set(location, vehicleSquare);
        }

        //TODO: ensure there is no empty location beforehand
        for (Integer emptyLocation : emptyLocations) {
            squares.set(emptyLocation, new Square(emptyLocation, "Regular Square") {
                @Override
                public void performLanding(SimulatedPlayer player) {
                    System.out.println("Regular Square");
                }
            });
        }

        board.initSquares(squares);


        // Finally return the built board
        return board;
    }

}