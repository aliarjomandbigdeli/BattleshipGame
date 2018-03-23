public class ShipPart {
    private int x;
    private int y;
    private boolean isBroken;

    public ShipPart(int x, int y) {
        this.x = x;
        this.y = y;
        isBroken = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
