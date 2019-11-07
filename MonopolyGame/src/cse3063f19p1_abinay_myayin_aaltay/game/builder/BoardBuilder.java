package cse3063f19p1_abinay_myayin_aaltay.game.builder;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
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

    private MonopolyGame game;
    private int totalSquareSize;
    private int taxSquareSize;
    private int diceCount;
    private int taxPayment, salary;

    public BoardBuilder(MonopolyGame game) {
        this.game = game;
    }

    public BoardBuilder setTotalSquareSize(int totalSquareSize) {
        this.totalSquareSize = totalSquareSize;
        return this;
    }

    public BoardBuilder setTaxSquareSize(int taxSquareSize) {
        this.taxSquareSize = taxSquareSize;
        return this;
    }

    public BoardBuilder setDiceCount(int diceCount) {
        this.diceCount = diceCount;
        return this;
    }

    public BoardBuilder setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public BoardBuilder setTaxPayment(int taxPayment) {
        this.taxPayment = taxPayment;
        return this;
    }

    public Board build() {
        Board board = new Board();

        // Attach Monopoly cse3063f19p1_abinay_myayin_aaltay.game instance
        board.attachParentGame(this.game);

        // Build squares
        List<Square> squares = new LinkedList<>();
        for (int i = 0; i < taxSquareSize; i++) {
            TaxSquare taxSquare = new TaxSquare(taxPayment);
            squares.add(taxSquare);
        }
        while (squares.size() < totalSquareSize - 1) {
            Square square = new Square("Dummy Square") { // TODO: random square
                @Override
                public void performLanding(SimulatedPlayer player) {}
            };
            squares.add(square);
        }
        Collections.shuffle(squares);
        squares.add(0, new GoSquare(salary));
        board.initSquares(squares);

        // Debug
//        for (int i = 0; i < squares.size(); i++) {
//            System.out.printf("Square #%d: %s\n", i + 1, squares.get(i).getName());
//        }

        // Build dices
        Dice[] dices = new Dice[diceCount];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
        board.initDices(dices);

        // Finally return the built board
        return board;
    }

}
