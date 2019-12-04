package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Dice;
import cse3063f19p1_abinay_myayin_aaltay.game.square.GoSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.LotSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;
import cse3063f19p1_abinay_myayin_aaltay.game.square.TaxSquare;

import java.util.LinkedList;
import java.util.List;

public class BoardBuilder {

    public static final int COUNT_TOTAL_SQUARE = 40;
    public static final int COUNT_TOTAL_PROPERTY_SQUARE = 22;
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

        // Attach Monopoly game instance
        board.attachParentGame(this.game);

        // Build squares
        List<Square> squares = new LinkedList<>();
        for (int i = 0; i < taxSquareSize; i++) {
            TaxSquare taxSquare = new TaxSquare(taxPayment);
            squares.add(taxSquare);
        }

       /* while (squares.size() < COUNT_TOTAL_SQUARE ) {
            Square square = new Square("Dummy Square") { // TODO: random square
                @Override
                public void performLanding(SimulatedPlayer player) {
                }
            };
            squares.add(square);
        }
        Collections.shuffle(squares);*/
        squares.add(0, new GoSquare(goSalary));
        squares.add(1,new LotSquare("Kasımpaşa", 0, 60, 2,4,10,30,90,160,250,50,50,30,33));
        squares.add(3, new LotSquare("Dolapdere",0,60,4,8,20,60,180,320,450,50, 50,30,33));
        squares.add(6, new LotSquare("Sultanahmet", 1,100,6,12,30,90,270,400,550,50,50,50,55));
        squares.add(8,new LotSquare("Karaköy",1,100,6,12,30,90,270,400,550,50,50,50,55));
        squares.add(9,new LotSquare("Sirkeci",1,120,8,16,40,100,300,450,600,50,50,60,66));
        squares.add(11,new LotSquare("Beyoğlu",2,140,10,20,50,150,450,625,750,100,100,70,77));
        squares.add(13,new LotSquare("Beşiktaş",2,140,10,20,50,150,450,625,750,100,100,70,70));
        squares.add(14,new LotSquare("Taksim",2,160,12,24,60,180,500,700,900,100,100,80,88));
        squares.add(16,new LotSquare("Harbiye",3,180,14,28,70,200,550,750,950,100,100,90,99));
        squares.add(18,new LotSquare("Şişli",3,180,14,28,70,200,550,750,950,100,100,90,99));
        squares.add(19,new LotSquare("Mecidiyeköy",3,200,16,32,80,220,600,800,1000,100,100,100,110));
        squares.add(21,new LotSquare("Bostancı", 4,220,18,36,90,250,700,875,1050,150,150,110,121));
        squares.add(23,new LotSquare("Erenköy",4,220,18,36,90,250,700,875,1050,150,150,110,121));
        squares.add(24,new LotSquare("Caddebostan",4,240,20,40,100,300,750,925,1100,150,150,120,132));
         board.initSquares(squares);

        // Build dices
        Dice[] dices = new Dice[COUNT_TOTAL_DICE];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
        board.initDices(dices);

        // Misc. attributes
        board.initSleepParameters(sleepAfterPieceMove);

        // Finally return the built board
        return board;
    }

}
