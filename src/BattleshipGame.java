
public class BattleshipGame {
    private Player playerA;
    private Player playerB;
    private final int N = 10;


    public BattleshipGame() {
        playerA = new Player();
        playerB = new Player();
    }

    public void drawGrids() {
        System.out.println("###########################################" +
                "       " + "###########################################");
        System.out.println("############### Your grid #################" +
                "       " + "############# Opponent grid ###############");
        System.out.println("###########################################" +
                "       " + "###########################################");
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |" +
                "       " + "  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("--|---+---+---+---+---+---+---+---+---+---+" +
                "       " + "--|---+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < N; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < N; j++) {
                System.out.print(" " + playerA.getBoard().getSymbols()[i][j] + " |");
            }
            System.out.print("       " + i + " |");
            for (int j = 0; j < N; j++) {
                System.out.print(" " + playerB.getBoard().getSymbols()[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+" +
                    "       " + "--|---+---+---+---+---+---+---+---+---+---+");
        }

        System.out.println("\n");
    }


    public Player getPlayerA() {
        return playerA;
    }
}
