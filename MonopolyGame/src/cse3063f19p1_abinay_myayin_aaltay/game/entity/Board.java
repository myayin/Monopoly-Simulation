package cse3063f19p1_abinay_myayin_aaltay.game.entity;


import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.player.SimulatedPlayer;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;

import java.util.List;
import java.util.stream.Stream;

public class Board {

    private MonopolyGame parentGame;
    private List<Square> squares;
    private Dice[] dices;

    public void attachParentGame(MonopolyGame game) {
        if (this.parentGame != null)
            throw new IllegalStateException("Parent cse3063f19p1_abinay_myayin_aaltay.game is already attached!");

        this.parentGame = game;
    }

    public void initSquares(List<Square> squares) {
        if (this.squares != null)
            throw new IllegalStateException("Squares are already initialized!");

        this.squares = squares;
    }

    public void initDices(Dice[] dices) {
        if (this.dices != null)
            throw new IllegalStateException("Dices are already initialized!");

        this.dices = dices;
    }

    /* ------------------------------ */

    public int performTurn(SimulatedPlayer player) {
        if (player.isBankrupt()) {
            // TODO: sell mechanism
            System.out.printf("%s is bankrupted. Turn is ended.\n", player);
            return 0;
        }

        for (Dice dice : dices) dice.roll();

        boolean allSame = false; // TODO
        int diceTotal = Stream.of(dices).mapToInt(Dice::getValue).sum();

        // Print rolled dices
        System.out.print(player.toString() + " rolled [");
        String delimiter = "";
        for (Dice dice : dices) {
            System.out.print(delimiter);
            System.out.print(dice.getValue());
            delimiter = " ";
        }
        System.out.println("] into total " + diceTotal);

        Piece piece = player.getPiece();
        while (diceTotal > 0) {
            parentGame.sleep(0.25f);
            System.out.printf("(%d) ", diceTotal);
            int prevLocation = piece.getCurrentLocation();
            int nextLocation = (prevLocation + 1) % squares.size();

            Square nextSquare = squares.get(nextLocation);

            if (diceTotal == 1) {
                nextSquare.performLanding(player);
                System.out.printf("%s passing by [%d] %s.\n",
                        player.getPlayerName(), nextLocation, nextSquare.getName());

            } else {
                nextSquare.performPassing(player);
                System.out.printf("%s landing on [%d] %s.\n",
                        player.getPlayerName(), nextLocation, nextSquare.getName());
            }

            piece.setCurrentLocation(nextLocation);

            diceTotal--;
        }

        return -1; // TODO: return moved square count
    }

}
