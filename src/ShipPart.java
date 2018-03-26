
/**
 * this class models Battleship game ship's part
 *
 * @author Ali ArjomandBigdeli
 * @since 3.26.2018
 */
public class ShipPart {
    private int x;
    private int y;
    private boolean isBroken;

    /**
     * constructor
     */
    public ShipPart() {
        x = -1;
        y = -1;
        isBroken = false;
    }

    /**
     * getter
     * @return the x position of a part
     */
    public int getX() {
        return x;
    }

    /**
     * setter
     * @param x the x position of a part
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter
     * @return the y position of a part
     */
    public int getY() {
        return y;
    }

    /**
     * setter
     * @param y the y position of a part
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * this method returns the part's broken(burst) status
     * @return boolean that specifies the part's broken(burst) status
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * this method use to set the part's broken(burst) status
     * @param broken that specifies the part's broken(burst) status
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
