import java.util.Scanner;

public class Player {
    private Board board;
    private Ship[] ships;
    private Player opponent;
    public static final int NUM_OF_SHIPS = 5;


    public Player() {
        board = new Board();
        ships = new Ship[NUM_OF_SHIPS];
    }

    public void setupShips() {
        Scanner inputStream = new Scanner(System.in);
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            System.out.println("Please enter the size of the ship" + (i + 1) + ": ");
            int size = inputStream.nextInt();
            if (size >= 2 && size <= 5) {
                ships[i] = new Ship(size);
                ships[i].buildShip();
                ships[i].putShipInBoard(board);
            } else {
                System.err.println("incorrect size, please enter again");
                --i;
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
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
        for (int i = 0; i < board.getN(); i++) {
            System.out.print(i + " |");
            for (int j = 0; j < board.getN(); j++) {
                System.out.print(" " + getBoard().getSymbols()[i][j] + " |");
            }
            System.out.print("       " + i + " |");
            for (int j = 0; j < board.getN(); j++) {
                System.out.print(" " + opponent.getBoard().getShootSymbols()[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+" +
                    "       " + "--|---+---+---+---+---+---+---+---+---+---+");
        }

        System.out.println("\n");
    }

    public ShipPart matchPoint(int x, int y) {
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

    public void exactShoot(int x, int y) {
        if (opponent.getBoard().getIsFull()[x][y]) {
            opponent.getBoard().getShootSymbols()[x][y] = '&';
            opponent.matchPoint(x, y).setBroken(true);
        } else {
            opponent.getBoard().getShootSymbols()[x][y] = 'X';
        }
        opponent.getBoard().getIsShot()[x][y] = true;
    }

}
