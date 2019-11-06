package builder;

import entity.Board;
import entity.Dice;
import square.Square;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BoardBuilder {

    private int squareSize;
    private int taxSquareSize;
    private int diceCount;
    private int payment, salary;

    public BoardBuilder setSquareSize(int squareSize) {
        this.squareSize = squareSize;
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

    public BoardBuilder setPayment(int payment) {
        this.payment = payment;
        return this;
    }

    public Board build() {
        Board board = new Board();

        // Build squares
        List<Square>  squares = new LinkedList<>();
        // TODO generate squares
        board.initSquares(squares);

        Collections.shuffle(squares);

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
