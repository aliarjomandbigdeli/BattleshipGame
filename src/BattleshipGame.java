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
        System.out.print("First player: Please enter your name: ");
        String playerAId = inputStream.next();
        playerA.setId(playerAId);
        playerA.setupShips();

        System.out.print("Second player: Please enter your name: ");
        String playerBId = inputStream.next();
        playerB.setId(playerBId);
        playerB.setupShips();


        while (true) {
            playerA.drawGrids();
            if(ifTurnProcessEndGame(playerA))
                break;

            playerB.drawGrids();
            if(ifTurnProcessEndGame(playerB))
                break;


        }
    }


    public boolean ifEndGame(){
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

    public boolean ifTurnProcessEndGame(Player player){
        while (true) {
            if(ifEndGame()){
                return true;
            }
            System.out.println("Player " + player.getId() + " turn");
            if (!player.exactShoot()) {
                return false;
            }
            player.drawGrids();
        }
    }

}
