package cse3063f19p1_abinay_myayin_aaltay.game.entity;

import cse3063f19p1_abinay_myayin_aaltay.game.MonopolyGame;
import cse3063f19p1_abinay_myayin_aaltay.game.PrintHelper;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Jail;
import cse3063f19p1_abinay_myayin_aaltay.game.square.Square;

import java.util.List;
import java.util.stream.Stream;

public class Board {

    private MonopolyGame parentGame;
    private List<Square> squares;
    private Dice[] dices;

    public void setDices(Dice[] dices) {
        this.dices = dices;
    }

    public Dice[] getDices() {
        return dices;
    }

    private double sleepAfterPieceMove;

    public void attachParentGame(MonopolyGame game) {
        if (this.parentGame != null)
            throw new IllegalStateException("Parent game is already attached!");

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

    public void initSleepParameters(double afterPieceMove) {
        this.sleepAfterPieceMove = afterPieceMove;
    }

    /* ------------------------------ */

    public int performTurn(SimulatedPlayer player) {
        if (player.isBankrupt()) {
            // TODO: sell mechanism
            System.out.printf("%s is bankrupted. Turn is ended.\n", player);
            return 0;
        }
        rollDices();
        int cnt=0;
        do {
            if (player.isInJail()) { //TODO: zarı atmadan para verip çıkmak isterse çıkabilir
                if (dices[0].getFacingValue() == dices[1].getFacingValue()) {
                    player.setInJail(false);
                    player.setInWaiting(true);
                    //no need to perform turn
                }else{
                    cnt++;
                }
                if(cnt > 3){
                    player.setBalance(player.getBalance() - Jail.jailPayment);
                }
                return 0;
            }
            if(player.doublenessCounter == 3){
                player.setInJail(true);
                player.getPiece().setCurrentLocation(10); // 10 for the jail
                return 0;
            }
            if(dices[0].getFacingValue() == dices[1].getFacingValue()){
                player.doublenessCounter++;
            }else{
                player.doublenessCounter=0;  //arka arkaya oldup olmadığını kontrol etmek için
            }
            int diceTotal = Stream.of(dices).mapToInt(Dice::getFacingValue).sum();
            // Print rolled dices
            PrintHelper.printRoll(player, dices);

            Piece piece = player.getPiece();
            while (diceTotal > 0) {
                parentGame.sleep(sleepAfterPieceMove);
                System.out.printf("(%d) ", diceTotal);
                int prevLocation = piece.getCurrentLocation();
                int nextLocation = (prevLocation + 1) % squares.size();

                Square nextSquare = squares.get(nextLocation);

                if (diceTotal == 1) {
                    nextSquare.performLanding(player);
                    System.out.printf("%s landing on [%d] %s.\n",
                            player.getPlayerName(), nextLocation, nextSquare.getName());

                } else {
                    nextSquare.performPassing(player);
                    System.out.printf("%s passing by [%d] %s.",
                            player.getPlayerName(), nextLocation, nextSquare.getName());
                }

                piece.setCurrentLocation(nextLocation);


                diceTotal--;
            }
            if (player.getPiece().getCurrentLocation() == 30) { //gotojail mi diye kontrol ettim
                player.getPiece().setCurrentLocation(10);
                player.setInJail(true);
            }
            return -1; // TODO: return moved square count


        } while (dices[0].getFacingValue() == dices[1].getFacingValue() && player.doublenessCounter < 3);


    }

    public Dice[] rollDices() {
        for (Dice dice : dices) dice.roll();
        return dices;
    }

}
