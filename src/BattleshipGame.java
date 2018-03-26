import java.util.Scanner;

/**
 * this class models Battleship game
 *
 * @author Ali ArjomandBigdeli
 * @since 3.26.2018
 */
public class BattleshipGame {
    private Player playerA;
    private Player playerB;

    /**
     * constructor
     */
    public BattleshipGame() {
        playerA = new Player();
        playerB = new Player();
        playerA.setOpponent(playerB);
        playerB.setOpponent(playerA);

    }

    /**
     * this method is for running the game
     */
    public void startGame() {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Single player?(y/n)");
        String ans = inputStream.next();
        boolean isComputer = false;
        if (ans.equals("y")) {
            isComputer = true;
        }
        System.out.println("Do you want exact shoot?(y/n)");
        ans = inputStream.next();
        boolean isExact = false;
        if (ans.equals("y")) {
            isExact = true;
        }
        System.out.print("First player: Please enter your name: ");
        String playerAId = inputStream.next();
        playerA.setId(playerAId);
        playerA.setupShips();

        if (!isComputer) {
            System.out.print("Second player: Please enter your name: ");
            String playerBId = inputStream.next();
            playerB.setId(playerBId);
            playerB.setupShips();
        } else {
            playerB.setId("Computer");
            playerB.setupComputerShips();
        }


        while (true) {
            playerA.drawGrids();
            if (ifTurnProcessEndGame(playerA, isExact, false))
                break;

            if (!isComputer)
                playerB.drawGrids();
            if (ifTurnProcessEndGame(playerB, isExact, isComputer))
                break;

        }
    }


    /**
     * check whether  the game ends or doesn't(someone wins or not)
     * @return whether  the game ends or doesn't
     */
    private boolean ifEndGame() {
        if (playerA.isWinner()) {
            System.out.println(playerA.getId() + " wins *_*");
            return true;
        }
        if (playerB.isWinner()) {
            System.out.println(playerB.getId() + " wins *_*");
            return true;
        }
        return false;
    }

    /**
     * this method runs the turn process of the game
     * @param player the turn is for this player
     * @param isExact determine the type of shoot
     * @param isComputer determine the type of player
     * @return whether the turn process can end the game or not
     */
    private boolean ifTurnProcessEndGame(Player player, boolean isExact, boolean isComputer) {
        while (true) {
            if (ifEndGame()) {
                return true;
            }
            if (!isComputer)
                System.out.println(player.getId() + "'s turn");
            if (!player.shoot(isExact, isComputer)) {
                return false;
            }
            if (!isComputer)
                player.drawGrids();
        }
    }

}
