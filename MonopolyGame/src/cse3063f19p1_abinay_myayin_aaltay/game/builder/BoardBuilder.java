package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.SimulatedPlayer;
import cse3063f19p1_abinay_myayin_aaltay.game.square.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardBuilder {

    public static final int COUNT_TOTAL_SQUARE = 40;
    public static final int COUNT_TOTAL_DICE = 2;

    private MonopolyGame game;
    private int taxSquareSize;
    private int taxPayment, goSalary;
    private double sleepAfterPieceMove;

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

    public void setSleepAfterPieceMove(double sleepAfterPieceMove) {
        this.sleepAfterPieceMove = sleepAfterPieceMove;
    }

    public BoardBuilder withConfig(MonopolyConfig config) {
        setTaxSquareSize(config.getTaxSquareCount());
        setGoSalary(config.getGoSalary());
        setTaxPayment(config.getTaxPayment());
        setSleepAfterPieceMove(config.getSleepAfterPieceMove());

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
       /* while (squares.size() < COUNT_TOTAL_SQUARE ) {
            Square square = new Square("Dummy Square") { // TODO: random square
                @Override
                public void performLanding(SimulatedPlayer player) {
                }
            };
            squares.add(square);
        }
        Collections.shuffle(squares);*/
        squares.set(0, new GoSquare(goSalary));
        squares.set(1, new LotSquare(1, lotNames.remove(rnd.nextInt(lotNames.size())), 0, 60, 2, 4, 10, 30, 90, 160, 250, 50, 50, 30, 33));
        squares.set(3, new LotSquare(3, lotNames.remove(rnd.nextInt(lotNames.size())), 0, 60, 4, 8, 20, 60, 180, 320, 450, 50, 50, 30, 33));
        squares.set(6, new LotSquare(6, lotNames.remove(rnd.nextInt(lotNames.size())), 1, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50, 50, 55));
        squares.set(8, new LotSquare(8, lotNames.remove(rnd.nextInt(lotNames.size())), 1, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50, 50, 55));
        squares.set(9, new LotSquare(9, lotNames.remove(rnd.nextInt(lotNames.size())), 1, 120, 8, 16, 40, 100, 300, 450, 600, 50, 50, 60, 66));
        squares.set(10, new JailSquare(10, 50));
        squares.set(11, new LotSquare(11, lotNames.remove(rnd.nextInt(lotNames.size())), 2, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100, 70, 77));
        squares.set(13, new LotSquare(13, lotNames.remove(rnd.nextInt(lotNames.size())), 2, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100, 70, 70));
        squares.set(14, new LotSquare(14, lotNames.remove(rnd.nextInt(lotNames.size())), 2, 160, 12, 24, 60, 180, 500, 700, 900, 100, 100, 80, 88));
        squares.set(16, new LotSquare(16, lotNames.remove(rnd.nextInt(lotNames.size())), 3, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100, 90, 99));
        squares.set(18, new LotSquare(18, lotNames.remove(rnd.nextInt(lotNames.size())), 3, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100, 90, 99));
        squares.set(19, new LotSquare(19, lotNames.remove(rnd.nextInt(lotNames.size())), 3, 200, 16, 32, 80, 220, 600, 800, 1000, 100, 100, 100, 110));
        squares.set(21, new LotSquare(21, lotNames.remove(rnd.nextInt(lotNames.size())), 4, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150, 110, 121));
        squares.set(23, new LotSquare(23, lotNames.remove(rnd.nextInt(lotNames.size())), 4, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150, 110, 121));
        squares.set(24, new LotSquare(24, lotNames.remove(rnd.nextInt(lotNames.size())), 4, 240, 20, 40, 100, 300, 750, 925, 1100, 150, 150, 120, 132));
        squares.set(26, new LotSquare(26, lotNames.remove(rnd.nextInt(lotNames.size())), 5, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150, 130, 143));
        squares.set(27, new LotSquare(27, lotNames.remove(rnd.nextInt(lotNames.size())), 5, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150, 130, 143));
        squares.set(29, new LotSquare(29, lotNames.remove(rnd.nextInt(lotNames.size())), 5, 280, 24, 48, 120, 360, 850, 1025, 1200, 150, 150, 140, 154));
        squares.set(30, new GoToJailSquare(30));
        squares.set(31, new LotSquare(31, lotNames.remove(rnd.nextInt(lotNames.size())), 6, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200, 150, 165));
        squares.set(32, new LotSquare(32, lotNames.remove(rnd.nextInt(lotNames.size())), 6, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200, 150, 165));
        squares.set(34, new LotSquare(34, lotNames.remove(rnd.nextInt(lotNames.size())), 6, 320, 28, 56, 150, 450, 1000, 1200, 1400, 200, 200, 160, 176));
        squares.set(38, new LotSquare(38, lotNames.remove(rnd.nextInt(lotNames.size())), 7, 350, 35, 70, 175, 500, 1100, 1300, 1500, 200, 200, 175, 193));
        squares.set(39, new LotSquare(39, lotNames.remove(rnd.nextInt(lotNames.size())), 7, 400, 50, 100, 200, 600, 1400, 1700, 2000, 200, 200, 200, 220));

        List<Integer> emptyLocations = new ArrayList<>();
        for (int i = 0; i < squares.size(); i++) {
           if(squares.get(i) == null) emptyLocations.add(i);
        }

        if(taxSquareSize > emptyLocations.size())
            throw new IllegalArgumentException("Tax square size is given more than empty locations.");

        for (int i = 0; i < taxSquareSize; i++) {
            int location = emptyLocations.remove(rnd.nextInt(emptyLocations.size()));
            TaxSquare taxSquare = new TaxSquare(location,taxPayment);
            squares.set(location,taxSquare);
        }
        //TODO: ensure there is no empty location beforehand
        for (Integer emptyLocation : emptyLocations) {
            squares.set(emptyLocation,new Square(emptyLocation,"Dummy Square") {
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

}
