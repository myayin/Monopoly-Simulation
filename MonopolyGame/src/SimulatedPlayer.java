public class SimulatedPlayer {

    private int balance;
    private boolean bankrupt;
    /* private Piece piece;*/
    private String playerName;

    public SimulatedPlayer(int balance, String playerName) {
        this.balance = balance;
        this.playerName = playerName;

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


}
