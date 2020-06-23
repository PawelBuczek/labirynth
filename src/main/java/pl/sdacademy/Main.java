package pl.sdacademy;

public class Main {
    public static void main(String[] args) {
        String labyrinthString1 = "+-+-+-+-+-+-+-+-+-+-+\n" +
                "  |                 |\n" +
                "+ + +-+-+ +-+-+-+-+ +\n" +
                "|   |   | |   |   | |\n" +
                "+-+-+ + + + + + + + +\n" +
                "|   | |   | | | | | |\n" +
                "+-+ + +-+-+ + +-+ +-+\n" +
                "|   | |     | |     |\n" +
                "+ +-+ +-+-+-+ + +-+ +\n" +
                "|     |       |   | |\n" +
                "+ +-+-+ +-+ + +-+-+ +\n" +
                "| |   |   | |       |\n" +
                "+ + + +-+ +-+-+-+-+-+\n" +
                "|   | |   |         |\n" +
                "+-+-+ + +-+ +-+-+-+ +\n" +
                "|     | |   |   | | |\n" +
                "+ +-+-+ + +-+ + + + +\n" +
                "| |       | | | |   |\n" +
                "+ + +-+ + + + + +-+ +\n" +
                "|   |   | |   |      \n" +
                "+-+-+-+-+-+-+-+-+-+-+";
        Labyrinth labyrinth1 = new Labyrinth(labyrinthString1);
        System.out.println(labyrinth1.resolve(false));

        System.out.println(System.lineSeparator() + " --- | Entering Labyrinth nr 2 | --- " + System.lineSeparator());
        String labyrinthString2 = "+-+-+-+-+\n" +
                "    |   |\n" +
                "+-+ + + +\n" +
                "|   | | |\n" +
                "+ +-+ + +\n" +
                "|     | |\n" +
                "+-+-+ + +\n" +
                "|     |  \n" +
                "+-+-+-+-+";
        Labyrinth labyrinth2 = new Labyrinth(labyrinthString2);
        System.out.println(labyrinth2.resolve(true));
    }
}
