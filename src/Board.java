public class Board {
    private boolean[][] isFull;
    private boolean[][] isShot;
    private char[][] symbols;
    private final int N = 10;

    public Board() {
        isFull = new boolean[N][N];
        isShot = new boolean[N][N];
        symbols = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                symbols[i][j] = ' ';
                isShot[i][j] = false;
                isFull[i][j] = false;
            }
        }
    }

    public char[][] getSymbols() {
        return symbols;
    }

    public boolean[][] getIsFull() {
        return isFull;
    }
}
