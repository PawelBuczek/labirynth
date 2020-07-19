package pl.sdacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class App extends Application {
    private final HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    VBox firstMainVBox;
    Button button;
    VBox gameVBox;
    Scene gameScene;
    Label topInfo;
    Label bottomInfo;
    Label labyrinthMap;
    Labyrinth labyrinth;
    int labyrinthNr = 0;
    final int labyrinthCount = 3;
    boolean keyPressed;

    @Override
    public void start(Stage stage) {
        firstMainVBox = new VBox();
        button = new Button("Start Game");
        button.setOnAction(event -> createGameScene(stage));
        firstMainVBox.getChildren().add(button);
        firstMainVBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(firstMainVBox, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void createGameScene(Stage stage) {
        labyrinth = new Labyrinth(takeLabyrinthStringFromFile(labyrinthNr++));
        gameVBox = new VBox();
        topInfo = new Label("Labyrinth " + labyrinthNr + "/3");
        topInfo.setTextFill(Color.BLUE);
        topInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        gameVBox.getChildren().add(topInfo);
        labyrinthMap = new Label(labyrinth.getLabyrinthString());
        labyrinthMap.setFont(new Font("Courier New", 24));
        labyrinthMap.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        gameVBox.getChildren().add(labyrinthMap);
        bottomInfo = new Label("Controls: Arrow keys or WASD keys to move");
        bottomInfo.setTextFill(Color.DARKGRAY);
        gameVBox.getChildren().add(bottomInfo);
        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.setSpacing(12.0);
        gameScene = new Scene(gameVBox, 400, 800);
        stage.setScene(gameScene);
        gameScene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });
        gameScene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                keyPressed = false;
                if (removeActiveKey("DOWN") || removeActiveKey("S")) {
                    labyrinth.tryMoving(0);
                    keyPressed = true;
                }
                if (removeActiveKey("LEFT") || removeActiveKey("A")) {
                    labyrinth.tryMoving(1);
                    keyPressed = true;
                }
                if (removeActiveKey("UP") || removeActiveKey("W")) {
                    labyrinth.tryMoving(2);
                    keyPressed = true;
                }
                if (removeActiveKey("RIGHT") || removeActiveKey("D")) {
                    labyrinth.tryMoving(3);
                    keyPressed = true;
                }
                if (keyPressed) {
                    if (labyrinth.getCurrentPoint().isExit()) {
                        if (labyrinthNr < labyrinthCount) {
                            labyrinth = new Labyrinth(takeLabyrinthStringFromFile(labyrinthNr));
                            topInfo = new Label("Labyrinth " + labyrinthNr + "/3");
                            topInfo.setTextFill(Color.BLUE);
                            topInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                        }
                        labyrinthNr++;
                    }
                    labyrinthMap = new Label(labyrinth.getLabyrinthString());
                    labyrinthMap.setFont(new Font("Courier New", 24));
                    labyrinthMap.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                    gameVBox.getChildren().setAll(topInfo, labyrinthMap, bottomInfo);
                    if (labyrinthNr > labyrinthCount) {
                        topInfo = new Label("Congratulations! You have completed the game");
                        topInfo.setTextFill(Color.GREEN);
                        button = new Button("Exit Game");
                        button.setOnAction(event -> System.exit(0));
                        gameVBox.getChildren().setAll(topInfo, button);
                    }
                }
            }
        }.start();

        stage.show();
    }

    private String takeLabyrinthStringFromFile(int labyrinthNr) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/Labyrinths_Strings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches("_Labyrinth" + labyrinthNr)) {
                    while ((line = br.readLine()) != null) {
                        if (!line.matches("_Labyrinth\\d+")) {
                            sb.append(line).append("\n");
                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return sb.toString();
    }

    private boolean removeActiveKey(String codeString) {
        Boolean isActive = currentlyActiveKeys.get(codeString);

        if (isActive != null && isActive) {
            currentlyActiveKeys.put(codeString, false);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}