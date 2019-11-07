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

    // TODO: Extract those statistical values to a distinct class: GameStats || PlayerStats
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

        this.gameBoard = new BoardBuilder(this).withConfig(config).build();
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
