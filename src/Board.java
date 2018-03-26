
/**
 * this class models Battleship game board
 *
 * @author Ali ArjomandBigdeli
 * @since 3.26.2018
 */
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

    /**
     * getter
     * @return getSymbols that is for your ships symbols
     */
    public char[][] getSymbols() {
        return symbols;
    }

    /**
     * getter
     * @return getShootSymbols that is for your shoot on the target
     */
    public char[][] getShootSymbols() {
        return shootSymbols;
    }

    /**
     * getter
     * @return getIsFull that is for checking the board is full or not
     */
    public boolean[][] getIsFull() {
        return isFull;
    }

    /**
     * getter
     * @return getIsShoot that is for checking the board is shot or not
     */
    public boolean[][] getIsShot() {
        return isShot;
    }

    /**
     * getter
     * @return N that is specifies one dimension of the board
     */
    public int getN() {
        return N;
    }
}
