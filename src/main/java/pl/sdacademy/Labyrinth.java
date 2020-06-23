package pl.sdacademy;

public class Labyrinth {
    // Lepiej, gdybyśmy po prostu mieli dwuwymiarową tablicę, wtedy dostęp do punktu o zadanych współrzędnych
    // byłby błyskawiczny (nie musielibyśmy przebiegać w pętli przez wszystkie punkty).
    private final Point[] points;
    private Point currentPoint;
    private final String[] labyrinthStringLines;
    private int currentDirection;
    private String solution = "";

    public Labyrinth(String labyrinthString) {
        String[] lines = labyrinthString.split("\\r?\\n");
        this.labyrinthStringLines = lines;
        points = new Point[(lines.length / 2) * (lines[0].length() / 2)];
        boolean canGoSouth;
        boolean canGoEast;
        boolean canGoNorth;
        boolean canGoWest;
        int counter = 0;
        for (int i = 1; i < lines.length; i += 2) {
            for (int j = 1; j < lines[i].length(); j += 2) {
                canGoNorth = lines[i - 1].charAt(j) == ' ';
                canGoEast = lines[i].charAt(j + 1) == ' ';
                canGoSouth = lines[i + 1].charAt(j) == ' ';
                if (i == 1 && j == 1) {
                    canGoWest = false;  //there is an exit there, but that's too easy!
                } else {
                    canGoWest = lines[i].charAt(j - 1) == ' ';
                }

                if (counter == points.length - 1) {
                    points[counter] = new Point(j / 2, i / 2, canGoNorth, canGoEast, canGoSouth, canGoWest, true);
                } else {
                    points[counter] = new Point(j / 2, i / 2, canGoNorth, canGoEast, canGoSouth, canGoWest);
                }
                counter++;
            }
        }
        currentPoint = getPoint(0, 0);
    }


    private Point getPoint(int x, int y) {
        for (Point point : points) {
            if (point.getX() == x && point.getY() == y) {
                return point;
            }
        }
        System.out.println("Point with given parameters was not found.");
        return null;
    }

    // metody moveXYZ można by zapisać lepiej na różne sposoby, ale o nich będziemy dopiero sobie dopowiadać
    // (typy wyliczeniowe, interfejsy funcjonalne itp.)
    // W tym wypadku można by się pokusić o myk następujący:
    // za każdym razem kolejność będzie następująca:
    // WSEN i tak w koło. Czyli moglibyśmy powiedzieć, że wykonujemy ciąg kroków spośrod:
    // WSENWSE
    // z tymże od zadanego punktu i wykonujemy maksymalnie 4 kroki.
    // Można by stworzyć metodę, która by zliczała wykonane kroki, do tego byłby switch, którego case'y
    // reprezentowałyby właśnie ciąg: WSENWSE. Te wypisane przypadki miałyby instrukcję break wykonywaną,
    // jeśli wykonaliśmy 4 kroki.
    // W tym momencie można by uwspólnić kod tych 4 metod moveXYZ - stworzyć jedną metodę, która przyjmowałaby
    // jako parametr informacje od którego punktu z ciągu WSENWSE startujemy.
    private void moveRight() {
        switch (currentDirection) {
            case 0:  //south
                if (moveWest()) {
                    break;
                }
                if (moveSouth()) {
                    break;
                }
                if (moveEast()) {
                    break;
                }
                moveNorth();
                break;
            case 1:  //west
                if (moveNorth()) {
                    break;
                }
                if (moveWest()) {
                    break;
                }
                if (moveSouth()) {
                    break;
                }
                moveEast();
                break;
            case 2:  //north
                if (moveEast()) {
                    break;
                }
                if (moveNorth()) {
                    break;
                }
                if (moveWest()) {
                    break;
                }
                moveSouth();
                break;
            default: //east
                if (moveSouth()) {
                    break;
                }
                if (moveEast()) {
                    break;
                }
                if (moveNorth()) {
                    break;
                }
                moveWest();
                break;
        }

    }

    public boolean moveNorth() {
        if (currentPoint.isOpenNorth()) {
            currentPoint = getPoint(currentPoint.getX(), currentPoint.getY() - 1);
            currentDirection = 2;
            solution += "N";
            return true;
        } else {
            return false;
        }
    }

    public boolean moveEast() {
        if (currentPoint.isOpenEast()) {
            currentPoint = getPoint(currentPoint.getX() + 1, currentPoint.getY());
            currentDirection = 3;
            solution += "E";
            return true;
        } else {
            return false;
        }
    }

    public boolean moveSouth() {
        if (currentPoint.isOpenSouth()) {
            currentPoint = getPoint(currentPoint.getX(), currentPoint.getY() + 1);
            currentDirection = 0;
            solution += "S";
            return true;
        } else {
            return false;
        }
    }

    public boolean moveWest() {
        if (currentPoint.isOpenWest()) {
            currentPoint = getPoint(currentPoint.getX() - 1, currentPoint.getY());
            currentDirection = 1;
            solution += "W";
            return true;
        } else {
            return false;
        }
    }

    public String resolve(boolean printEachMove) {
        String solutionToPrint;
        while (!currentPoint.isExit()) {
            if (printEachMove || (currentPoint.getX() ==0 && currentPoint.getY()==0)) {
                System.out.println();
                markCurrentPosition();
                print();
                removeCurrentPosition();
            }
            moveRight();
        }
        System.out.println();
        markCurrentPosition();
        print();
        solutionToPrint = solution;
        solution = "";
        return solutionToPrint;
    }

    public void print() {
        for (String s : labyrinthStringLines) {
            System.out.println(s);
        }
    }

    private void markCurrentPosition() {
        StringBuilder labString = new StringBuilder(labyrinthStringLines[(currentPoint.getY() * 2) + 1]);
        labString.setCharAt((currentPoint.getX() * 2) + 1, '#');
        labyrinthStringLines[(currentPoint.getY() * 2) + 1] = labString.toString();
    }

    private void removeCurrentPosition() {
        StringBuilder labString = new StringBuilder(labyrinthStringLines[(currentPoint.getY() * 2) + 1]);
        labString.setCharAt((currentPoint.getX() * 2) + 1, ' ');
        labyrinthStringLines[(currentPoint.getY() * 2) + 1] = labString.toString();
    }

}
