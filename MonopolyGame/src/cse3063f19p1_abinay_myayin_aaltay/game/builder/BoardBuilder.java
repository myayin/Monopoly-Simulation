package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;
import cse3063f19p1_abinay_myayin_aaltay.game.square.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardBuilder {

    public static final int COUNT_TOTAL_SQUARE = 40;
    public static final int COUNT_TOTAL_DICE = 2;

    private MonopolyGame game;
    private int taxSquareSize;
    private int taxPayment, goSalary;
    private int utilitySquareSize;
    private int vehicleSquareSize;

    public BoardBuilder(MonopolyGame game) {
        this.game = game;
    }

    public BoardBuilder setTaxSquareSize(int taxSquareSize) {
        this.taxSquareSize = taxSquareSize;
        return this;
    }

    public BoardBuilder setGoSalary(int goSalary) {
        this.goSalary = goSalary;
        return this;
    }

    public BoardBuilder setTaxPayment(int taxPayment) {
        this.taxPayment = taxPayment;
        return this;
    }

    public BoardBuilder setUtilitySquareSize(int utilitySquareSize) {
        this.utilitySquareSize = utilitySquareSize;
        return this;
    }

    public BoardBuilder setVehicleSquareSize(int vehicleSquareSize){
        this.vehicleSquareSize = vehicleSquareSize;
        return this;
    }


    public BoardBuilder withConfig(MonopolyConfig config) {
        setTaxSquareSize(config.getTaxSquareCount());
        setGoSalary(config.getGoSalary());
        setTaxPayment(config.getTaxPayment());
        setUtilitySquareSize(config.getUtilitySquareCount());
        setVehicleSquareSize(config.getVehicleSquareCount());

        return this;
    }

    public Board build() {
        Board board = new Board();
        Random rnd = new Random();

        // Build squares
        List<Square> squares = new ArrayList<>(40);
        for (int i = 0; i < 40; i++) {
            squares.add(null);
        }

        //TODO: move lot list to config
        List<String> lotNames = new ArrayList<>(List.of(
                "Kasımpaşa",
                "Dolapdere",
                "Sultanahmet",
                "Karaköy",
                "Sirkeci",
                "Beyoğlu",
                "Beşiktaş",
                "Taksim",
                "Harbiye",
                "Şişli",
                "Mecidiyeköy",
                "Bostancı",
                "Erenköy",
                "Caddebostan",
                "Nişantaşı",
                "Teşvikiye",
                "Maçka",
                "Levent",
                "Etiler",
                "Bebek",
                "Tarabya",
                "Yeniköy"
        ));
        List<String> utilityNames = new ArrayList<>(List.of(
                "Sular Dairesi",
                "Elektrik İdaresi",
                "Vergi Dairesi",
                "Doğalgaz Dağıtım Firması",
                "İnternet Servis Sağlayıcısı"
        ));

        List<String> vehicleNames = new ArrayList<>(List.of(
                "Haydarpaşa Treni",
                "Kadıköy Vapuru",
                "Kabataş Vapuru",
                "Sirkeci Treni",
                "Marmaray",
                "Metrobüs"
        ));

       /* while (squares.size() < COUNT_TOTAL_SQUARE ) {
            Square square = new Square("Dummy Square") { // TODO: random square
                @Override
                public void performLanding(SimulatedPlayer player) {
                }
            };
            squares.add(square);
        }
        Collections.shuffle(squares);*/

        addGroupedLots(
                squares, new LotGroup(Color.RED),
                new LotSquare(1, lotNames.remove(rnd.nextInt(lotNames.size())), 6000, 6000,600),
                new LotSquare(3, lotNames.remove(rnd.nextInt(lotNames.size())), 6000, 6000,600)
        );

        addGroupedLots(
                squares, new LotGroup(Color.BLUE),
                new LotSquare(6, lotNames.remove(rnd.nextInt(lotNames.size())), 10_000, 10_000,1000),
                new LotSquare(8, lotNames.remove(rnd.nextInt(lotNames.size())), 10_000, 10_000,1000),
                new LotSquare(9, lotNames.remove(rnd.nextInt(lotNames.size())), 12_000, 12_000,1200)
        );

        addGroupedLots(
                squares, new LotGroup(Color.MAGENTA),
                new LotSquare(11, lotNames.remove(rnd.nextInt(lotNames.size())), 14_000, 14_000,1400),
                new LotSquare(13, lotNames.remove(rnd.nextInt(lotNames.size())), 14_000, 14_000,1400),
                new LotSquare(14, lotNames.remove(rnd.nextInt(lotNames.size())), 16_000, 16_000,1600)
        );

        addGroupedLots(
                squares, new LotGroup(Color.ORANGE),
                new LotSquare(16, lotNames.remove(rnd.nextInt(lotNames.size())), 18_000, 18_000,1800),
                new LotSquare(18, lotNames.remove(rnd.nextInt(lotNames.size())), 18_000, 18_000,1800),
                new LotSquare(19, lotNames.remove(rnd.nextInt(lotNames.size())), 20_000, 20_000,2000)
        );

        addGroupedLots(
                squares, new LotGroup(Color.CYAN),
                new LotSquare(21, lotNames.remove(rnd.nextInt(lotNames.size())), 22_000, 22_000,2200),
                new LotSquare(23, lotNames.remove(rnd.nextInt(lotNames.size())), 22_000, 22_000,2200),
                new LotSquare(24, lotNames.remove(rnd.nextInt(lotNames.size())), 24_000, 24_000,2400)
        );

        addGroupedLots(
                squares, new LotGroup(Color.YELLOW),
                new LotSquare(26, lotNames.remove(rnd.nextInt(lotNames.size())), 26_000, 26_000,2600),
                new LotSquare(27, lotNames.remove(rnd.nextInt(lotNames.size())), 26_000, 26_000,2600),
                new LotSquare(29, lotNames.remove(rnd.nextInt(lotNames.size())), 28_000, 28_000,2600)
        );

        addGroupedLots(
                squares, new LotGroup(Color.GREEN),
                new LotSquare(31, lotNames.remove(rnd.nextInt(lotNames.size())), 30_000, 30_000,3000),
                new LotSquare(32, lotNames.remove(rnd.nextInt(lotNames.size())), 30_000, 30_000,3000),
                new LotSquare(34, lotNames.remove(rnd.nextInt(lotNames.size())), 32_000, 32_000,3000)
        );

        addGroupedLots(
                squares, new LotGroup(Color.BLUE),
                new LotSquare(38, lotNames.remove(rnd.nextInt(lotNames.size())), 35_000, 35_000,3500),
                new LotSquare(39, lotNames.remove(rnd.nextInt(lotNames.size())), 40_000, 40_000,4000)
        );


        squares.set(0, new GoSquare(goSalary));
        squares.set(10, new JailSquare(10, 50));
        squares.set(30, new GoToJailSquare(30));

        List<Integer> emptyLocations = new ArrayList<>();
        for (int i = 0; i < squares.size(); i++) {
            if (squares.get(i) == null) emptyLocations.add(i);
        }

        if (taxSquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Tax square size is given more than empty locations.");

        for (int i = 0; i < taxSquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));
            TaxSquare taxSquare = new TaxSquare(location, taxPayment);
            squares.set(location, taxSquare);
        }

        if (utilitySquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Utility square size is given more than empty locations.");

        for (int i = 0; i < utilitySquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));
            System.out.println(lotNames.size());
            UtilitySquare utilitySquare = new UtilitySquare(location,
                    utilityNames.remove(rnd.nextInt(utilityNames.size())),
                    10_000,8_000); //TODO: refactor constant assignment

            squares.set(location, utilitySquare);
        }

        if (vehicleSquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Vehicle square size is given more than empty locations.");

        for (int i = 0; i < vehicleSquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));

            VehicleSquare vehicleSquare = new VehicleSquare(location,
                    vehicleNames.remove(rnd.nextInt(vehicleNames.size())),
                    10_000,8_000,1000); //TODO: refactor constant assignment

            squares.set(location, vehicleSquare);
        }

        //TODO: ensure there is no empty location beforehand
        for (Integer emptyLocation : emptyLocations) {
            squares.set(emptyLocation, new Square(emptyLocation, "Dummy Square") {
                @Override
                public void performLanding(SimulatedPlayer player) {
                    System.out.println("Dummy square :)");
                }
            });
        }

        board.initSquares(squares);


        // Finally return the built board
        return board;
    }

    private void addGroupedLots(List<Square> squares, LotGroup lotGroup, LotSquare... squaresToPlace) {
        for (LotSquare square : squaresToPlace) {
            lotGroup.appendLot(square);
            squares.set(square.getLocation(), square);
        }
    }

}
