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

    public void buildShip() {
        Scanner inputStream = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            System.out.println("Enter the position of part " + (i + 1));
            System.out.print("Enter X position: ");
            parts[i].setX(inputStream.nextInt());
            System.out.print("Enter Y position: ");
            parts[i].setY(inputStream.nextInt());
        }


        boolean xDirection = true;
        boolean yDirection = true;
        int xPosition = parts[0].getX();
        for (int i = 0; i < size; i++) {
            if (parts[i].getX() != xPosition) {
                xDirection = false;
                break;
            }
        }

        int yPosition = parts[0].getY();
        for (int i = 0; i < size; i++) {
            if (parts[i].getY() != yPosition) {
                yDirection = false;
                break;
            }
        }

        if (!xDirection && !yDirection) {
            System.err.println("Incorrect direction");
        }
    }

    public void putShipInBoard(Board board) {
        for (int i = 0; i < size; i++) {
            int x = parts[i].getX();
            int y = parts[i].getY();
            if (!board.getIsFull()[x][y]) {
                if (!parts[i].isBroken())
                    board.getSymbols()[x][y] = '@';
                else
                    board.getSymbols()[x][y] = '#';
            } else {
                System.err.println("the position is full");
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
