package pl.sdacademy;

/**
 * represents a specific point in a Labyrinth
 */
public class Point {
    private final char value;  //dodałem, ale właściwie nie jest nigdzie używane, może nie do końca ogarnąłem co można z tym zrobić
    private final int x; //horizontal
    private final int y; //vertical
    private final boolean isOpenNorth;
    private final boolean isOpenEast;
    private final boolean isOpenSouth;
    private final boolean isOpenWest;
    private final boolean isExit;

    /**
     * creates a new Point (usually should only be done, when initializing the Labyrinth), but some Labyrinths can change.
     * @param value  what sign is visible at the given point
     * @param x  horizontal position in the virtual 2D array of a labyrinth
     * @param y  vertical position in the virtual 2D array of a labyrinth
     * @param isOpenNorth  whether it is possible to move north(up) from this point to another point
     * @param isOpenEast  whether it is possible to move east(right) from this point to another point
     * @param isOpenSouth  whether it is possible to move south(down) from this point to another point
     * @param isOpenWest  whether it is possible to move west(left) from this point to another point
     * @param isExit  whether current point is the exit of a labyrinth
     */
    public Point(char value, int x, int y, boolean isOpenNorth, boolean isOpenEast, boolean isOpenSouth, boolean isOpenWest, boolean isExit) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.isOpenNorth = isOpenNorth;
        this.isOpenEast = isOpenEast;
        this.isOpenSouth = isOpenSouth;
        this.isOpenWest = isOpenWest;
        this.isExit = isExit;
    }

    /**
     * calling normal constructor, but with isExit always set as false - this would be how most of the points should be created
     * @param value  what sign is visible at the given point
     * @param x  horizontal position in the virtual 2D array of a labyrinth
     * @param y  vertical position in the virtual 2D array of a labyrinth
     * @param isOpenNorth  whether it is possible to move north(up) from this point to another point
     * @param isOpenEast  whether it is possible to move east(right) from this point to another point
     * @param isOpenSouth  whether it is possible to move south(down) from this point to another point
     * @param isOpenWest  whether it is possible to move west(left) from this point to another point
     */
    public Point(char value, int x, int y, boolean isOpenNorth, boolean isOpenEast, boolean isOpenSouth, boolean isOpenWest) {
        this(value, x, y, isOpenNorth, isOpenEast, isOpenSouth, isOpenWest, false);
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
