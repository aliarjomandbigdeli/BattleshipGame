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

    public void buildShip() {
        Scanner inputStream = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            System.out.println("Enter the position of part " + (i + 1));
            System.out.print("Enter row: ");
            int x = inputStream.nextInt();
            System.out.print("Enter column: ");
            int y = inputStream.nextInt();
//            while (parts[i].getX() == x && parts[i].getY() == y) {
//                System.out.println("Enter the position of part " + (i + 1) + ", again");
//                System.out.print("Enter row: ");
//                x = inputStream.nextInt();
//                System.out.print("Enter column: ");
//                y = inputStream.nextInt();
//            }
            parts[i].setX(x);
            parts[i].setY(y);
        }


        //work on this part to be more accurate
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

//    public void buildComputerShip() {
//        Scanner inputStream = new Scanner(System.in);
//
//        for (int i = 0; i < size; i++) {
//            System.out.println("Enter the position of part " + (i + 1));
//            System.out.print("Enter row: ");
//            int x = inputStream.nextInt();
//            System.out.print("Enter column: ");
//            int y = inputStream.nextInt();
////            while (parts[i].getX() == x && parts[i].getY() == y) {
////                System.out.println("Enter the position of part " + (i + 1) + ", again");
////                System.out.print("Enter row: ");
////                x = inputStream.nextInt();
////                System.out.print("Enter column: ");
////                y = inputStream.nextInt();
////            }
//            parts[i].setX(x);
//            parts[i].setY(y);
//        }
//
//
//        //work on this part to be more accurate
//        boolean xDirection = true;
//        boolean yDirection = true;
//        int xPosition = parts[0].getX();
//        for (int i = 0; i < size; i++) {
//            if (parts[i].getX() != xPosition) {
//                xDirection = false;
//                break;
//            }
//        }
//
//        int yPosition = parts[0].getY();
//        for (int i = 0; i < size; i++) {
//            if (parts[i].getY() != yPosition) {
//                yDirection = false;
//                break;
//            }
//        }
//
//        if (!xDirection && !yDirection) {
//            System.err.println("Incorrect direction");
//        }
//    }

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
