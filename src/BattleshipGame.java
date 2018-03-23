
public class BattleshipGame {
    private Player playerA;
    private Player playerB;


    public BattleshipGame() {
        playerA = new Player();
        playerB = new Player();
        playerA.setOpponent(playerB);
        playerB.setOpponent(playerA);

    }

    public boolean isWinner(Player player) {
        boolean result = true;
        for (int i = 0; i < player.NUM_OF_SHIPS; i++) {
            result = result && player.getShips()[i].isBurst();
        }
        return result;
    }

    public Player getPlayerA() {
        return playerA;
    }
}
