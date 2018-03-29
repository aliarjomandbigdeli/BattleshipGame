import java.util.Random;

public class AIPlayer extends Player {

    public AIPlayer() {
        super();
        setId("Computer");
    }

    /**
     * this method use to build a computer player's ships in a correct way
     */
    public void setupShips() {
        Random rand = new Random();
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            int size = 2 + rand.nextInt(4);
            ships[i] = new Ship(size);
            ships[i].buildComputerShip(board);
            ships[i].putShipInBoard(board);

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

        int[] xy = new int[2];
        Random rand = new Random();
        do {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            xy[0] = x;
            xy[1] = y;
            chooser(xy);
        } while (opponent.getBoard().getIsShot()[xy[0]][xy[1]]);
        x = xy[0];
        y = xy[1];


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


    /**
     * the help computer player to be smart
     *
     * @param xy the position of shoot
     */
    private void chooser(int[] xy) {
        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getN(); j++) {
                if (opponent.getBoard().getShootSymbols()[i][j] == '&') {
                    if (i >= 1 && i <= 8 && j >= 1 && j <= 8) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        } else if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        }
                    } else if (i == 0 && j == 0) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        }
                    } else if (i == 9 && j == 9) {
                        if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        }
                    } else if (i == 9 && j == 0) {
                        if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        }
                    } else if (i == 0 && j == 9) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        }
                    } else if (i == 0) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        }
                    } else if (i == 9) {
                        if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        } else if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        }
                    } else if (j == 0) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j + 1]) {
                            xy[0] = i;
                            xy[1] = j + 1;
                        }
                    } else if (j == 9) {
                        if (!opponent.getBoard().getIsShot()[i + 1][j]) {
                            xy[0] = i + 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i - 1][j]) {
                            xy[0] = i - 1;
                            xy[1] = j;
                        } else if (!opponent.getBoard().getIsShot()[i][j - 1]) {
                            xy[0] = i;
                            xy[1] = j - 1;
                        }
                    }
                }
            }
        }
    }
}
