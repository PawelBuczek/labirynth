package pl.sdacademy;

public class Point {
    private final int x; //horizontal
    private final int y; //vertical
    private final boolean isOpenNorth;
    private final boolean isOpenEast;
    private final boolean isOpenSouth;
    private final boolean isOpenWest;
    private final boolean isExit;

    public Point(int x, int y, boolean isOpenNorth, boolean isOpenEast, boolean isOpenSouth, boolean isOpenWest, boolean isExit) {
        this.x = x;
        this.y = y;
        this.isOpenNorth = isOpenNorth;
        this.isOpenEast = isOpenEast;
        this.isOpenSouth = isOpenSouth;
        this.isOpenWest = isOpenWest;
        this.isExit = isExit;
    }

    public Point(int x, int y, boolean isOpenNorth, boolean isOpenEast, boolean isOpenSouth, boolean isOpenWest) {
        this.x = x;
        this.y = y;
        this.isOpenNorth = isOpenNorth;
        this.isOpenEast = isOpenEast;
        this.isOpenSouth = isOpenSouth;
        this.isOpenWest = isOpenWest;
        this.isExit = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOpenNorth() {
        return isOpenNorth;
    }

    public boolean isOpenEast() {
        return isOpenEast;
    }

    public boolean isOpenSouth() {
        return isOpenSouth;
    }

    public boolean isOpenWest() {
        return isOpenWest;
    }

    public boolean isExit() {
        return isExit;
    }
}
