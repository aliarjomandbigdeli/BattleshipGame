import java.util.Random;

/**
 * this class models Battleship game player
 *
 * @author Ali ArjomandBigdeli
 * @since 3.26.2018
 */
public abstract class Player {
    protected Board board;
    protected Ship[] ships;
    protected Player opponent;
    protected String id;
    public static final int NUM_OF_SHIPS = 5;


    /**
     * constructor
     */
    public Player() {
        board = new Board();
        ships = new Ship[NUM_OF_SHIPS];
        id = " ";
    }


    /**
     * getter
     *
     * @return the board of a player
     */
    protected Board getBoard() {
        return board;
    }

    /**
     * getter
     *
     * @return the ships of a player
     */
    private Ship[] getShips() {
        return ships;
    }

    /**
     * this use to set opponent for a player
     *
     * @param opponent opponent for a player
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * getter
     *
     * @return the opponent of a player
     */
    private Player getOpponent() {
        return opponent;
    }

    /**
     * getter
     *
     * @return the ID of a player
     */
    public String getId() {
        return id;
    }

    /**
     * setter
     *
     * @param id the ID of a player(it's like name)
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * this method use to build a player's ships in a correct way
     */
    public abstract void setupShips();


    /**
     * this methods use to update ship's symbols on the board
     */
    private void updateShipsInBoard() {
        for (Ship ship : ships) {
            ship.updateShipInBoard(board);
        }
    }

    /**
     * this method use to show player board
     */
    public void drawGrids() {
        updateShipsInBoard();
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
        for (int i = 0; i < board.getN(); i++) {
            System.out.print(i + " |");
            for (int j = 0; j < board.getN(); j++) {
                if (getBoard().getSymbols()[i][j] == '@') {
                    //System.out.print(" " + "\033[34m@\033[0m" + " |");
                    System.out.print("\u001B[44m" + "\u001B[30m" + " @ " + "\u001B[0m" + "|");
                } else if (getBoard().getSymbols()[i][j] == '#') {
                    //System.out.print(" " + "\033[31m#\033[0m" + " |");
                    System.out.print("\u001B[41m" + "\u001B[30m" + " # " + "\u001B[0m" + "|");
                } else
                    System.out.print(" " + getBoard().getSymbols()[i][j] + " |");
            }
            System.out.print("       " + i + " |");
            for (int j = 0; j < board.getN(); j++) {
                if (opponent.getBoard().getShootSymbols()[i][j] == '&') {
                    //System.out.print(" " + "\033[32m&\033[0m" + " |");
                    System.out.print("\u001B[42m" + "\u001B[30m" + " & " + "\u001B[0m" + "|");
                } else if (opponent.getBoard().getShootSymbols()[i][j] == 'X') {
                    //System.out.print(" " + "\033[33mX\033[0m" + " |");
                    System.out.print("\u001B[43m" + "\u001B[30m" + " X " + "\u001B[0m" + "|");
                } else
                    System.out.print(" " + opponent.getBoard().getShootSymbols()[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+" +
                    "       " + "--|---+---+---+---+---+---+---+---+---+---+");
        }

        System.out.println();
    }

    /**
     * this method use to math a point on the board to a ship part
     *
     * @param x the x position of that point
     * @param y the y position of that point
     * @return the part of ship that is in the position
     */
    protected ShipPart matchPoint(int x, int y) {
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            for (int j = 0; j < ships[i].getParts().length; j++) {
                if (ships[i].getParts()[j].getX() == x && ships[i].getParts()[j].getY() == y) {
                    return ships[i].getParts()[j];
                }
            }
        }
        System.err.println("No match part");
        throw new RuntimeException("No match part");
    }


    /**
     * this method use to shoot
     *
     * @param isExact specifies type of the shoot
     * @return whether the shoot was successful or not
     */
    public abstract boolean shoot(boolean isExact);


    /**
     * this method use for approximate shoot
     *
     * @param xy the position of the shoot
     */
    protected void randomize(int[] xy) {
        Random rand = new Random();
        int x = xy[0];
        int y = xy[1];
        if (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
            x = x - 1 + rand.nextInt(3);
            y = y - 1 + rand.nextInt(3);
        } else if (x == 0 && y == 0) {
            x += rand.nextInt(2);
            y += rand.nextInt(2);
        } else if (x == 9 && y == 9) {
            x = x - 1 + rand.nextInt(2);
            y = y - 1 + rand.nextInt(2);
        } else if (x == 9 && y == 0) {
            x = x - 1 + rand.nextInt(2);
            y = y + rand.nextInt(2);
        } else if (x == 0 && y == 9) {
            x = x + rand.nextInt(2);
            y = y - 1 + rand.nextInt(2);
        } else if (x == 0) {
            x += rand.nextInt(2);
            y = y - 1 + rand.nextInt(3);
        } else if (x == 9) {
            x = x - 1 + rand.nextInt(2);
            y = y - 1 + rand.nextInt(3);
        } else if (y == 0) {
            x = x - 1 + rand.nextInt(3);
            y = y + rand.nextInt(2);
        } else if (y == 9) {
            x = x - 1 + rand.nextInt(3);
            y = y - 1 + rand.nextInt(2);
        }
        xy[0] = x;
        xy[1] = y;
    }

    /**
     * the method specifies whether the player is a winner or not
     *
     * @return boolean that specifies whether the player is a winner or not
     */
    public boolean isWinner() {
        boolean result = true;
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            result = result && getOpponent().getShips()[i].isBurst();
        }
        return result;
    }

}
