import java.util.Random;
import java.util.Scanner;

public class Player {
    private Board board;
    private Ship[] ships;
    private Player opponent;
    private String id;
    public static final int NUM_OF_SHIPS = 1;


    public Player() {
        board = new Board();
        ships = new Ship[NUM_OF_SHIPS];
        id = " ";
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

//    public void setupComputerShips() {
//        Random rand = new Random();
//        for (int i = 0; i < NUM_OF_SHIPS; i++) {
//            int size = 2 + rand.nextInt(4);
//            ships[i] = new Ship(size);
//            ships[i].buildShip();
//            ships[i].putShipInBoard(board);
//
//        }
//    }

    public void updateShipsInBoard() {
        for (Ship ship : ships) {
            ship.updateShipInBoard(board);
        }
    }

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
                if (getBoard().getSymbols()[i][j] == '@')
                    System.out.print(" " + "\033[34m@\033[0m" + " |");
                else if (getBoard().getSymbols()[i][j] == '#')
                    System.out.print(" " + "\033[31m#\033[0m" + " |");
                else
                    System.out.print(" " + getBoard().getSymbols()[i][j] + " |");
            }
            System.out.print("       " + i + " |");
            for (int j = 0; j < board.getN(); j++) {
                if (opponent.getBoard().getShootSymbols()[i][j] == '&')
                    System.out.print(" " + "\033[32m&\033[0m" + " |");
                else if (opponent.getBoard().getShootSymbols()[i][j] == 'X')
                    System.out.print(" " + "\033[33mX\033[0m" + " |");
                else
                    System.out.print(" " + opponent.getBoard().getShootSymbols()[i][j] + " |");
            }
            System.out.println();
            System.out.println("--|---+---+---+---+---+---+---+---+---+---+" +
                    "       " + "--|---+---+---+---+---+---+---+---+---+---+");
        }

        System.out.println();
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


    public boolean shoot(boolean isExact, boolean isComputer) {
        int x = 0;
        int y = 0;
        if (!isComputer) {
            Scanner inputStream = new Scanner(System.in);
            System.out.println("Enter the position of your shoot ");
            System.out.print("Enter row: ");
            x = inputStream.nextInt();
            System.out.print("Enter column: ");
            y = inputStream.nextInt();
            while ((x < 0 || x > 9 || y < 0 || y > 9) || opponent.getBoard().getIsShot()[x][y]) {
                System.out.println("Enter the position of your shoot again");
                System.out.print("Enter row: ");
                x = inputStream.nextInt();
                System.out.print("Enter column: ");
                y = inputStream.nextInt();
            }
        } else {
            Random rand = new Random();
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            while (opponent.getBoard().getIsShot()[x][y]) {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            }
        }


        if (!isExact) {
            Random rand = new Random();
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
            while (opponent.getBoard().getIsShot()[x][y]) {
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
            }
        }


        opponent.getBoard().getIsShot()[x][y] = true;
        if (opponent.getBoard().getIsFull()[x][y]) {
            opponent.getBoard().getShootSymbols()[x][y] = '&';
            opponent.matchPoint(x, y).setBroken(true);
            return true;
        } else {
            opponent.getBoard().getShootSymbols()[x][y] = 'X';
            return false;
        }
    }


    public boolean isWinner() {
        boolean result = true;
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            result = result && getOpponent().getShips()[i].isBurst();
        }
        return result;
    }

}
