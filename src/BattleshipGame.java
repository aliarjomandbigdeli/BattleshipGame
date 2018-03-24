import java.util.Scanner;

public class BattleshipGame {
    private Player playerA;
    private Player playerB;


    public BattleshipGame() {
        playerA = new Player();
        playerB = new Player();
        playerA.setOpponent(playerB);
        playerB.setOpponent(playerA);

    }


    public Player getPlayerA() {
        return playerA;
    }

    public void startGame() {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Single player?(y/n)");
        String ans = inputStream.next();
        boolean isSingle = false;
        if (ans.equals("y")) {
            isSingle = true;
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

        if(!isSingle){
            System.out.print("Second player: Please enter your name: ");
            String playerBId = inputStream.next();
            playerB.setId(playerBId);
            playerB.setupShips();
        }else {
            playerB.setId("Computer");
            playerB.setupShips();
        }



        while (true) {
            playerA.drawGrids();
            if (ifTurnProcessEndGame(playerA,isExact))
                break;

            playerB.drawGrids();
            if (ifTurnProcessEndGame(playerB,isExact))
                break;


        }
    }


    public boolean ifEndGame() {
        if (playerA.isWinner()) {
            System.out.println("Player " + playerA.getId() + " wins *_*");
            return true;
        }
        if (playerB.isWinner()) {
            System.out.println("Player " + playerB.getId() + " wins *_*");
            return true;
        }
        return false;
    }

    public boolean ifTurnProcessEndGame(Player player, boolean isExact) {
        while (true) {
            if (ifEndGame()) {
                return true;
            }
            System.out.println("Player " + player.getId() + " turn");
            if (!player.shoot(isExact)) {
                return false;
            }
            player.drawGrids();
        }
    }

}
