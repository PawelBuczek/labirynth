package pl.sdacademy;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class App extends Application {
    VBox firstMainVBox;
    Button buttonStart;
    VBox gameVBox;
    Scene gameScene;
    Label topInfo;
    Label labyrinthMap;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        firstMainVBox = new VBox();
        buttonStart = new Button("Start Game");
        buttonStart.setOnAction(event -> {
            createGameScene();
            stage.setScene(gameScene);
                });
        firstMainVBox.getChildren().add(buttonStart);
        firstMainVBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(firstMainVBox, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void createGameScene() {
        gameVBox = new VBox();
        topInfo = new Label("Labyrinth 1/10  |  Steps in current Labyrinth: 0");
        topInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        gameVBox.getChildren().add(topInfo);
        labyrinthMap = new Label(
                "+-+-+-+-+\n" +
                "!#  |   |\n" +
                "+-+ + + +\n" +
                "|   | | |\n" +
                "+ +-+ + +\n" +
                "|     | |\n" +
                "+-+-+ + +\n" +
                "|     |  \n" +
                "+-+-+-+-+");

        labyrinthMap.setFont(new Font("Courier New", 24));
        labyrinthMap.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        gameVBox.getChildren().add(labyrinthMap);
        gameVBox.getChildren().add(new Label("Controls: Arrow keys or WASD keys to move\n    R to Restart current Labyrinth\n    Space to move once towards exit\n    (hold Space to continue moving)"));
        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.setSpacing(12.0);
        gameScene = new Scene(gameVBox, 400, 600);
    }

    private void showNextLabyrinth() {

    }
}
