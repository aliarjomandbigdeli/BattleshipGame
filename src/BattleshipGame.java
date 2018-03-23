public class BattleshipGame {
    private char[][] gridA;
    private char[][] gridB;
    private final int N = 10;

    public BattleshipGame() {
        gridA = new char[N][N];
        gridB = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridA[i][j] = ' ';
                gridB[i][j] = ' ';
            }
        }
    }

    public void drawGrids() {
        System.out.println("###########################################");
        System.out.println("############### Your grid #################");
        System.out.println("###########################################");
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("--|---+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < N; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < N; j++) {
                System.out.print(" " + gridA[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+");
        }

        System.out.println("\n");

        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("--|---+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < N; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < N; j++) {
                System.out.print(" " + gridB[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+");
        }
        System.out.println("Opponent grid");
    }
}
