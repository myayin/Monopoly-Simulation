package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.Arguments;
import cse3063f19p1_abinay_myayin_aaltay.game.builder.BoardBuilder;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Piece;
import cse3063f19p1_abinay_myayin_aaltay.game.player.SimulatedPlayer;

import java.util.stream.Stream;

public class MonopolyGame {

    private SimulatedPlayer[] players;
    private int currentPlayerIndex;
    private Board gameBoard;

    // TODO: Extract those statistical values to a distinct class: GameStats || PlayerStats
    private int turnCounter;
    private int cycleCounter; // TODO: analyse

    public MonopolyGame(Arguments arguments) {
        int taxSquareCount = arguments.getInteger("taxSquareCount");
        int totalSquareCount = arguments.getInteger("totalSquareCount");
        String[] playerNames = arguments.getStrings("players");
        int startingBalance = arguments.getInteger("startingBalance");

        if (taxSquareCount <= 0)
            throw new IllegalArgumentException("taxSquareCount expected to be > 0");

        if (totalSquareCount <= 0)
            throw new IllegalArgumentException("totalSquareCount expected to be > 0");

        if (playerNames == null || playerNames.length < 2 || playerNames.length > 8)
            throw new IllegalArgumentException("players argument should be an array with at least 2, at most 8 players names.");

        if (startingBalance <= 0)
            throw new IllegalArgumentException("startingBalance expected to be > 0");

        this.players = new SimulatedPlayer[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            this.players[i] = new SimulatedPlayer(playerNames[i], startingBalance);
        }

        this.gameBoard = new BoardBuilder(this)
                .setTotalSquareSize(totalSquareCount)
                .setTaxSquareSize(taxSquareCount)
                .setDiceCount(2)
                .setSalary(200)
                .setTaxPayment(100)
                .build();
    }

    private void nextTurn() {
        System.out.println();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        turnCounter++;
    }

    private int countOfBankrupts() {
        return (int) Stream.of(players)
                .filter(SimulatedPlayer::isBankrupt).count();
    }

    private SimulatedPlayer getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void start() {
        while (true) {
            SimulatedPlayer currentPlayer = getCurrentPlayer();

            System.out.println("Turn #" + turnCounter);
            gameBoard.performTurn(currentPlayer);

            sleep(0.5f);

            if (players.length - countOfBankrupts() == 1) {
                onGameOver();
                break;
            }

            nextTurn();
        }
    }

    private void onGameOver() {
        // TODO
        System.out.println("Game over. Winner is: TODO");
    }

    public void sleep(float seconds) {
        try { Thread.sleep((long) (seconds * 1000L)); } catch (InterruptedException ignored) {}
    }

}
