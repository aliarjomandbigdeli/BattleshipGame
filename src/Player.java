import java.util.Scanner;

public class Player {
    private Board board;
    private Ship[] ships;

    public Player() {
        board = new Board();
        ships = new Ship[5];
    }

    public void setupShips() {
        Scanner inputStream = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
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
}
