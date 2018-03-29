import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer() {
        super();
    }

    /**
     * this method use to build a player's ships in a correct way
     */
    public void setupShips() {
        Scanner inputStream = new Scanner(System.in);
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            System.out.println("Please enter the size of the ship" + (i + 1) + ": ");
            int size = inputStream.nextInt();
            if (size >= 2 && size <= 5) {
                ships[i] = new Ship(size);
                ships[i].buildShip(board);
                while (!ships[i].partsAreConnected()) {
                    System.err.println("Ship wasn't connected, please enter again");
                    System.out.println("ship" + (i + 1) + ",again : ");
                    ships[i].formantShip();
                    ships[i].buildShip(board);
                }
                ships[i].putShipInBoard(board);
            } else {
                System.err.println("incorrect size, please enter again");
                --i;
            }
        }
    }


    /**
     * this method use to shoot
     * @param isExact specifies type of the shoot
     * @return whether the shoot was successful or not
     */
    public boolean shoot(boolean isExact) {
        int x = 0;
        int y = 0;

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

        int[] xy = new int[2];
        if (!isExact) {
            do {
                xy[0] = x;
                xy[1] = y;
                randomize(xy);
            } while (opponent.getBoard().getIsShot()[xy[0]][xy[1]]);
            x = xy[0];
            y = xy[1];
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
}
