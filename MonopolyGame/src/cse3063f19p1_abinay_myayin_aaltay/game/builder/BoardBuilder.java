package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Dice;
import cse3063f19p1_abinay_myayin_aaltay.game.player.SimulatedPlayer;
import cse3063f19p1_abinay_myayin_aaltay.game.square.GoSquare;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;
import cse3063f19p1_abinay_myayin_aaltay.game.square.TaxSquare;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

        // Attach Monopoly game instance
        board.attachParentGame(this.game);

        // Build squares
        List<Square> squares = new LinkedList<>();
        for (int i = 0; i < taxSquareSize; i++) {
            TaxSquare taxSquare = new TaxSquare(taxPayment);
            squares.add(taxSquare);
        }
        while (squares.size() < COUNT_TOTAL_SQUARE - 1) {
            Square square = new Square("Dummy Square") { // TODO: random square
                @Override
                public void performLanding(SimulatedPlayer player) {}
            };
            squares.add(square);
        }
        Collections.shuffle(squares);
        squares.add(0, new GoSquare(goSalary));
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
