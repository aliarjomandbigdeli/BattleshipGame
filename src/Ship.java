import java.util.Random;
import java.util.Scanner;

/**
 * this class models Battleship game ship
 *
 * @author Ali ArjomandBigdeli
 * @since 3.26.2018
 */
public class Ship {
    private int size;
    private ShipPart[] parts;
    private boolean isBurst;

    /**
     * constructor
     *
     * @param size specifies size of the ship
     */
    public Ship(int size) {
        this.size = size;
        parts = new ShipPart[size];
        for (int i = 0; i < size; i++) {
            parts[i] = new ShipPart();
        }
        isBurst = false;
    }

    /**
     * getter
     *
     * @return parts of a ship
     */
    public ShipPart[] getParts() {
        return parts;
    }

    /**
     * this method use to build a ship by user input
     *
     * @param board board that ships put on
     */
    public void buildShip(Board board) {
        Scanner inputStream = new Scanner(System.in);

        System.out.print("Do you want to put it in a row?(y/n)");
        String ans = inputStream.next();
        if (ans.equals("y")) {
            System.out.println("Please enter the row:");
            int x = inputStream.nextInt();
            while (x < 0 || x > 9) {
                System.err.println("Please enter the row, again:");
                x = inputStream.nextInt();
            }
            for (int i = 0; i < size; i++) {
                System.out.println("Enter the position of part " + (i + 1));
                System.out.print("Enter column: ");
                int y = inputStream.nextInt();
                while (y < 0 || y > 9) {
                    System.err.println("Enter the position of part " + (i + 1) + ", again");
                    System.out.print("Enter column: ");
                    y = inputStream.nextInt();
                }
                for (int j = 0; j <= i; j++) {
                    if ((parts[j].getX() == x && parts[j].getY() == y) || board.getIsFull()[x][y]) {
                        System.out.println("Enter the position of part " + (i + 1) + ", again");
                        System.out.print("Enter column: ");
                        y = inputStream.nextInt();
                        while (y < 0 || y > 9) {
                            System.err.println("Enter the position of part " + (i + 1) + ", again");
                            System.out.print("Enter column: ");
                            y = inputStream.nextInt();
                        }
                        j = -1;
                    }
                }
                parts[i].setX(x);
                parts[i].setY(y);
            }
        } else {
            System.out.println("So, you want to put it in a column, enter the column:");
            System.out.println("Please enter the column:");
            int y = inputStream.nextInt();
            while (y < 0 || y > 9) {
                System.err.println("Please enter the column, again:");
                y = inputStream.nextInt();
            }
            for (int i = 0; i < size; i++) {
                System.out.println("Enter the position of part " + (i + 1));
                System.out.print("Enter row: ");
                int x = inputStream.nextInt();
                while (x < 0 || x > 9) {
                    System.err.println("Enter the position of part " + (i + 1) + ", again");
                    System.out.print("Enter row: ");
                    x = inputStream.nextInt();
                }
                for (int j = 0; j <= i; j++) {
                    if ((parts[j].getX() == x && parts[j].getY() == y) || board.getIsFull()[x][y]) {
                        System.out.println("Enter the position of part " + (i + 1) + ", again");
                        System.out.print("Enter row: ");
                        x = inputStream.nextInt();
                        while (x < 0 || x > 9) {
                            System.err.println("Enter the position of part " + (i + 1) + ", again");
                            System.out.print("Enter row: ");
                            x = inputStream.nextInt();
                        }
                        j = -1;
                    }
                }
                parts[i].setX(x);
                parts[i].setY(y);
            }
        }
    }

    /**
     * this method use to build a ship by computer
     *
     * @param board board that ships put on
     */
    public void buildComputerShip(Board board) {
        Random rand = new Random();
        int sameRow = rand.nextInt(2);
        if (sameRow == 0) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10 - size + 1);
            for (int i = 0; i < size; i++) {
                if ((parts[i].getX() == x && parts[i].getY() == y) || board.getIsFull()[x][y]) {
                    x = rand.nextInt(10);
                    y = rand.nextInt(10 - size + 1);
                    for (int j = 0; j <= i; j++) {
                        parts[i].setX(-1);
                        parts[i].setY(-1);
                    }
                    i = -1;
                    continue;
                }
                parts[i].setX(x);
                parts[i].setY(y++);

            }
        } else {
            int y = rand.nextInt(10);
            int x = rand.nextInt(10 - size + 1);
            for (int i = 0; i < size; i++) {
                if ((parts[i].getX() == x && parts[i].getY() == y) || board.getIsFull()[x][y]) {
                    y = rand.nextInt(10);
                    x = rand.nextInt(10 - size + 1);
                    for (int j = 0; j <= i; j++) {
                        parts[i].setX(-1);
                        parts[i].setY(-1);
                    }
                    i = -1;
                    continue;
                }
                parts[i].setX(x++);
                parts[i].setY(y);
            }
        }
    }

    /**
     * check whether parts of ship are connected or not
     *
     * @return boolean that specifies whether parts of ship are connected or not
     */
    public boolean partsAreConnected() {
        boolean xDirection = true;
        int xPosition = parts[0].getX();
        int minX = parts[0].getX();
        int maxX = parts[0].getX();
        for (int i = 0; i < size; i++) {
            if (parts[i].getX() != xPosition) {
                xDirection = false;
            }
            minX = parts[i].getX() < minX ? parts[i].getX() : minX;
            maxX = parts[i].getX() > maxX ? parts[i].getX() : maxX;
        }

        int minY = parts[0].getY();
        int maxY = parts[0].getY();
        for (int i = 0; i < size; i++) {
            minY = parts[i].getY() < minY ? parts[i].getY() : minY;
            maxY = parts[i].getY() > maxY ? parts[i].getY() : maxY;
        }
        if (xDirection) {
            return maxY - minY + 1 == size;
        } else {
            return maxX - minX + 1 == size;
        }
    }

    /**
     * use to format a ship's parts to initial state
     */
    public void formantShip() {
        for (int i = 0; i < size; i++) {
            parts[i].setX(-1);
            parts[i].setY(-1);
        }
    }

    /**
     * this method puts ships on the board
     *
     * @param board board that ships put on
     */
    public void putShipInBoard(Board board) {
        for (int i = 0; i < size; i++) {
            int x = parts[i].getX();
            int y = parts[i].getY();
            if (!board.getIsFull()[x][y]) {
                if (!parts[i].isBroken()) {
                    board.getSymbols()[x][y] = '@';
                } else {
                    board.getSymbols()[x][y] = '#';
                }
                board.getIsFull()[x][y] = true;
            } else {
                System.err.println("the position is full");
            }
        }
    }

    /**
     * this methods use to update ship's symbols on the board
     */
    public void updateShipInBoard(Board board) {
        for (int i = 0; i < size; i++) {
            int x = parts[i].getX();
            int y = parts[i].getY();
            if (!parts[i].isBroken()) {
                board.getSymbols()[x][y] = '@';
            } else {
                board.getSymbols()[x][y] = '#';
            }
        }
    }

    /**
     * check whether the ship is burst or not
     *
     * @return boolean that specifies whether the ship is burst or not
     */
    public boolean isBurst() {
        boolean result = true;
        for (int i = 0; i < size; i++) {
            result = result && parts[i].isBroken();
        }
        isBurst = result;
        return isBurst;
    }
}
