package cse3063f19p1_abinay_myayin_aaltay.game;

import cse3063f19p1_abinay_myayin_aaltay.game.builder.BoardBuilder;
import cse3063f19p1_abinay_myayin_aaltay.game.config.MonopolyConfig;
import cse3063f19p1_abinay_myayin_aaltay.game.entity.Board;
import cse3063f19p1_abinay_myayin_aaltay.game.player.SimulatedPlayer;

import java.util.stream.Stream;

public class MonopolyGame {

    private SimulatedPlayer[] players;
    private int currentPlayerIndex;
    private Board gameBoard;

    private boolean running;

    // TODO: Extract possible speed values into a distinct class: Speed POJO
    private double sleepAfterTurn;

    // TODO: Extract those statistical values into a distinct class: GameStats || PlayerStats
    private int turnCounter;
    private int cycleCounter; // TODO: analyse

    public MonopolyGame(MonopolyConfig config) {
        String[] playersName = config.getPlayers();

        this.players = new SimulatedPlayer[playersName.length];
        for (int i = 0; i < playersName.length; i++) {
            this.players[i] = new SimulatedPlayer(
                    playersName[i],
                    config.getStartingBalance());
        }

        this.sleepAfterTurn = config.getSleepAfterTurn();

        this.gameBoard = new BoardBuilder(this).withConfig(config).build();
    }

    public boolean isRunning() {
        return running;
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
        running = true;

        while (running) gameLoop();
    }

    private void gameLoop() {
        SimulatedPlayer currentPlayer = getCurrentPlayer();

        System.out.println("Turn #" + turnCounter);
        gameBoard.performTurn(currentPlayer);

        sleep(sleepAfterTurn);

        if (players.length - countOfBankrupts() == 1) {
            onGameOver();
            stop();
            return;
        }

        nextTurn();
    }

    private void onGameOver() {
        // TODO
        System.out.println("Game over. Winner is: TODO");
    }

    /* ----------------------------- */

    public void sleep(double seconds) {
        try { Thread.sleep((long) (seconds * 1000L)); } catch (InterruptedException ignored) {}
    }

    public void stop() {
        running = false;
    }

}
