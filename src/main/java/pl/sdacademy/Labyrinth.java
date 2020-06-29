package pl.sdacademy;

/**
 * represents a 2D text labyrinth with entrance in the upper-left corner and exit in the bottom-right corner
 */
public class Labyrinth implements Runnable {
    private Thread t;  //not sure, it seems I don't need this, hmmm
    private final Point[][] points;
    private Point currentPoint;
    private final String[] labyrinthStringLines;
    private int currentDirection = 3;
    private String solution = "";

    /**
     * creates a new Labyrinth from given String in a specific format - taken from: http://www.delorie.com/game-room/mazes/genmaze.cgi (Text type, Width + Height = 2).
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
    }

    public void run() {

    }


    /**
     * tries to get out of the labyrinth, returning solution to the user  (current algorithm - keep right hand pressed against the wall)
     *
     * @param printSlowly instructs method if after each move a current state should be printed with current position, or only entry and exit states
     * @return text containing moves done in order to get out of the maze (e.g. NSSE would mean that to get out you need to move North, then South, then South, then East)
     */
    public String resolve(boolean printSlowly) throws InterruptedException {
        String solutionToPrint;
        while (!currentPoint.isExit()) {
            if (printSlowly || (currentPoint.getX() == 0 && currentPoint.getY() == 0)) {
                //noinspection BusyWait
                Thread.sleep(500);
                System.out.println();
                markCurrentPosition();
                print();
                removeCurrentPosition();
            }
            move();
        }
        System.out.println();
        markCurrentPosition();
        print();
        solutionToPrint = solution;
        solution = "";
        return solutionToPrint;
    }

    /**
     * prints current state of the labyrinth to the console
     * (could be done using points[][] - then labyrinthStringLines would be redundant. but too much work for today - I will try to upgrade it later on)
     */
    public void print() {
        for (String s : labyrinthStringLines) {
            System.out.println(s);
        }
    }

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
     * executes next move according to the current algorithm
     */
    private void move() {
        for (int i = 0; i < 4; i++) {
            if (tryMovingForward()) {
                break;
            } else {
                turnLeft();
            }
        }
        turnRight();
    }

    /**
     * changes currentDirection to be facing right of what it currently faces (e.g. if currentDirection is equal South (0), then it changes it to West (1))
     */
    private void turnRight() {
        if (currentDirection == 3) {
            currentDirection = 0;
        } else {
            currentDirection++;
        }
    }

    /**
     * changes currentDirection to be facing left of what it currently faces (e.g. if currentDirection is equal North (2), then it changes it to West (1))
     */
    private void turnLeft() {
        if (currentDirection == 0) {
            currentDirection = 3;
        } else {
            currentDirection--;
        }
    }

    /**
     * tries moving by 1 space in the current Direction
     *
     * @return true - successfully moved forward in the current direction. current position was updated  ;  false - move couldn't be performed. current position stays the same
     */
    private boolean tryMovingForward() {
        switch (currentDirection) {
            case 0:  //south
                if (currentPoint.isOpenSouth()) {
                    currentPoint = points[currentPoint.getY() + 1][currentPoint.getX()];
                    solution += "S";
                    return true;
                } else {
                    return false;
                }
            case 1:  //west
                if (currentPoint.isOpenWest()) {
                    currentPoint = points[currentPoint.getY()][currentPoint.getX() - 1];
                    solution += "W";
                    return true;
                } else {
                    return false;
                }
            case 2:  //north
                if (currentPoint.isOpenNorth()) {
                    currentPoint = points[currentPoint.getY() - 1][currentPoint.getX()];
                    solution += "N";
                    return true;
                } else {
                    return false;
                }
            default:  //east
                if (currentPoint.isOpenEast()) {
                    currentPoint = points[currentPoint.getY()][currentPoint.getX() + 1];
                    solution += "E";
                    return true;
                } else {
                    return false;
                }
        }
    }

}
