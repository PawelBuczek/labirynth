package pl.sdacademy;

// Klasa reprezentuje konkretny punkt labirynty - dobrze byłoby, gdyby zawierała też pole - wartość tego punktu.
// Z klasy labirynt moglibyśmy wtedy usunąć pole labyrinthStringLines.
// Zapis np. wiersza canGoNorth = lines[i - 1].charAt(j) == ' '; byłby wtedy czytelniejszy.
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
        this(x, y, isOpenNorth, isOpenEast, isOpenSouth, isOpenWest, false);
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
