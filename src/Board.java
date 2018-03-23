public class Board {
    private boolean[][] isFull;
    private char[][] symbols;
    private boolean[][] isShot;
    private char[][] shootSymbols;
    public static final int N = 10;

    public Board() {
        isFull = new boolean[N][N];
        isShot = new boolean[N][N];
        symbols = new char[N][N];
        shootSymbols = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                symbols[i][j] = ' ';
                shootSymbols[i][j] = ' ';
                isShot[i][j] = false;
                isFull[i][j] = false;
            }
        }
    }

    public char[][] getSymbols() {
        return symbols;
    }

    public char[][] getShootSymbols() {
        return shootSymbols;
    }

    public boolean[][] getIsFull() {
        return isFull;
    }

    public int getN() {
        return N;
    }
}
