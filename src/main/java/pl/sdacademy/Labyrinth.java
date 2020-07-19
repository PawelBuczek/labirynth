package pl.sdacademy;

/**
 * represents a 2D text labyrinth with entrance in the upper-left corner and exit in the bottom-right corner
 */
public class Labyrinth {
    private final Point[][] points;
    private Point currentPoint;
    private final String[] labyrinthStringLines;

    public String getLabyrinthString() {
        StringBuilder sb = new StringBuilder();
        for (String labyrinthStringLine : labyrinthStringLines) {
            sb.append(labyrinthStringLine).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * creates a new Labyrinth from given String in a specific format - taken from: http://www.delorie.com/game-room/mazes/genmaze.cgi (Text type, Width and Height = 2).
     *
     * @param labyrinthString text labyrinth representation
     */
    public Labyrinth(String labyrinthString) {
        String[] lines = labyrinthString.split("\\r?\\n");
        this.labyrinthStringLines = lines;
        points = new Point[(lines.length / 2)][(lines[0].length() / 2)];
        for (int i = 1; i < lines.length; i += 2) {
            for (int j = 1; j < lines[i].length(); j += 2) {
                if (i == lines.length - 2 & j == lines[0].length() - 2) {
                    points[i / 2][j / 2] = new Point(lines[i].charAt(j), j / 2, i / 2, lines[i - 1].charAt(j) == ' ', lines[i].charAt(j + 1) == ' ', lines[i + 1].charAt(j) == ' ', lines[i].charAt(j - 1) == ' ', true);
                } else {
                    points[i / 2][j / 2] = new Point(lines[i].charAt(j), j / 2, i / 2, lines[i - 1].charAt(j) == ' ', lines[i].charAt(j + 1) == ' ', lines[i + 1].charAt(j) == ' ', lines[i].charAt(j - 1) == ' ');
                }
            }
        }
        currentPoint = points[0][0];
        markCurrentPosition();
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

//    /**
//     * prints current state of the labyrinth to the console
//     * (could be done using points[][] - then labyrinthStringLines would be redundant. but too much work for today - I will try to upgrade it later on)
//     */
//    public void print() {
//        for (String s : labyrinthStringLines) {
//            System.out.println(s);
//        }
//    }

    /**
     * marks current position in the labyrinthStringLines
     */
    private void markCurrentPosition() {
        StringBuilder labString = new StringBuilder(labyrinthStringLines[(currentPoint.getY() * 2) + 1]);
        labString.setCharAt((currentPoint.getX() * 2) + 1, '#');
        labyrinthStringLines[(currentPoint.getY() * 2) + 1] = labString.toString();
    }

    /**
     * removes current position in the labyrinthStringLines
     */
    private void removeCurrentPosition() {
        StringBuilder labString = new StringBuilder(labyrinthStringLines[(currentPoint.getY() * 2) + 1]);
        labString.setCharAt((currentPoint.getX() * 2) + 1, ' ');
        labyrinthStringLines[(currentPoint.getY() * 2) + 1] = labString.toString();
    }


    /**
     * tries moving by 1 space in the given direction
     */
    void tryMoving(int direction) {
        removeCurrentPosition();
        switch (direction) {
            case 0:  //south
                if (currentPoint.isOpenSouth()) {
                    currentPoint = points[currentPoint.getY() + 1][currentPoint.getX()];
                }
                break;
            case 1:  //west
                if (currentPoint.isOpenWest()) {
                    currentPoint = points[currentPoint.getY()][currentPoint.getX() - 1];
                }
                break;
            case 2:  //north
                if (currentPoint.isOpenNorth()) {
                    currentPoint = points[currentPoint.getY() - 1][currentPoint.getX()];
                }
                break;
            default:  //east
                if (currentPoint.isOpenEast()) {
                    currentPoint = points[currentPoint.getY()][currentPoint.getX() + 1];
                }
                break;
        }
        markCurrentPosition();
    }

}
