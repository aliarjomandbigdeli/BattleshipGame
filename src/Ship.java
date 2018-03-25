import java.util.Scanner;

public class Ship {
    private int size;
    private ShipPart[] parts;
    private boolean isBurst;


    public Ship(int size) {
        this.size = size;
        parts = new ShipPart[size];
        for (int i = 0; i < size; i++) {
            parts[i] = new ShipPart();
        }
        isBurst = false;
    }

    public ShipPart[] getParts() {
        return parts;
    }

    public void buildShip(Board board) {
        Scanner inputStream = new Scanner(System.in);

        System.out.print("Do you want to put it in a row?(y/n)");
        String ans = inputStream.next();
        if (ans.equals("y")) {
            System.out.println("Please enter the row:");
            int x = inputStream.nextInt();
            for (int i = 0; i < size; i++) {
                System.out.println("Enter the position of part " + (i + 1));
                System.out.print("Enter column: ");
                int y = inputStream.nextInt();
                for (int j = 0; j < i; j++) {
                    if ((parts[j].getX() == x && parts[j].getY() == y) || board.getIsFull()[x][y]) {
                        System.out.println("Enter the position of part " + (i + 1) + ", again");
                        System.out.print("Enter column: ");
                        y = inputStream.nextInt();
                        j = -1;
                    }
                }
                parts[i].setX(x);
                parts[i].setY(y);
            }
        } else {
            System.out.println("So, you want to put it in a column, enter the column:");
            System.out.println("Please enter the row:");
            int y = inputStream.nextInt();
            for (int i = 0; i < size; i++) {
                System.out.println("Enter the position of part " + (i + 1));
                System.out.print("Enter row: ");
                int x = inputStream.nextInt();
                for (int j = 0; j < i; j++) {
                    if ((parts[j].getX() == x && parts[j].getY() == y) || board.getIsFull()[x][y]) {
                        System.out.println("Enter the position of part " + (i + 1) + ", again");
                        System.out.print("Enter row: ");
                        x = inputStream.nextInt();
                        j = -1;
                    }
                }
                parts[i].setX(x);
                parts[i].setY(y);
            }
        }
    }

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

        boolean yDirection = true;
        int yPosition = parts[0].getY();
        int minY = parts[0].getY();
        int maxY = parts[0].getY();
        for (int i = 0; i < size; i++) {
            if (parts[i].getY() != yPosition) {
                yDirection = false;
            }
            minY = parts[i].getY() < minY ? parts[i].getY() : minY;
            maxY = parts[i].getY() > maxY ? parts[i].getY() : maxY;
        }
        if (xDirection) {
            return maxY - minY + 1 == size;
        } else {
            return maxX - minX + 1 == size;
        }
    }

    public void formantShip() {
        for (int i = 0; i < size; i++) {
            parts[i].setX(-1);
            parts[i].setY(-1);
        }
    }

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


    public boolean isBurst() {
        boolean result = true;
        for (int i = 0; i < size; i++) {
            result = result && parts[i].isBroken();
        }
        isBurst = result;
        return isBurst;
    }
}
