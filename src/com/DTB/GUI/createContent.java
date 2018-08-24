package com.DTB.GUI;

import com.DTB.defense.Missile;
import com.DTB.CreateEntities.EntityPiece;
import com.DTB.CreateEntities.createAllEntities;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class createContent {

  private static double WIDTH = 600;
  private static double HEIGHT = 600;
  private static int SIZE = 14;
  private static int MISSILES = 50;

  private static Pane pane;
  private static Group friends;
  private static Group ennemies;
  private static Group missileGroup;
  private static GridPane gp;
  private static Label ScoreKill;
  private static Label ScoreFriends;
  private Timeline timeline;
  private Boolean timelineBool = true;
  private int time = 0;
  private int fastTime = 0;

  public createContent(Stage stage) {
    BorderPane root = new BorderPane();

    pane = new Pane();
    pane.setMaxSize(WIDTH, HEIGHT);
    pane.setStyle("-fx-background-color: lightgrey;");

    gp = new GridPane();

    friends = new Group();
    ennemies = new Group();
    missileGroup = new Group();
    pane.getChildren().addAll(ennemies, friends, missileGroup);

    createScoreBoard(root);
    createSides(pane);
    createTable(pane);
    new createAllEntities();

    timeline = new Timeline(
            new KeyFrame(Duration.millis(5), e -> {

              if (fastTime % 300 == 0) {
                createAllEntities.createSingleEntity();
              }
              if (fastTime % 600 == 0 && time > SIZE + SIZE) {
                createAllEntities.remove();
              }
              if (fastTime % 100 == 0) {
                createAllEntities.update(time);
                time++;
              }

              if (fastTime % 300 == 0) {
                Missile.launchMissile();
              }
              for (Missile m : Missile.getMissilesList()) {
                m.move();
              }
              fastTime++;
            })
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    pane.setOnMouseClicked(event -> {
      if (timelineBool) {
        timeline.stop();
        timelineBool = false;
      } else {
        timeline.play();
        timelineBool = true;
      }
    });

    root.setTop(gp);
    root.setCenter(pane);

    Scene scene = new Scene(root);
    stage.setTitle("Defend the Base challenge");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.sizeToScene();
    stage.show();
  }

  public static int getSize() {
    return SIZE;
  }

  public static void setSize(int size) {
    createContent.SIZE = size;
  }

  public static int getMissiles() {
    return MISSILES;
  }

  public static void setMissiles(int missiles) {
    createContent.MISSILES = missiles;
  }

  public static double getWIDTH() {
    return WIDTH;
  }

  public static double getHEIGHT() {
    return HEIGHT;
  }

  public static Group getFriends() {
    return friends;
  }

  public static Group getEnnemies() {
    return ennemies;
  }

  public static Group getMissileGroup() {
    return missileGroup;
  }

  public static Pane getPane() {
    return pane;
  }

  public static Label getScoreKill() {
    return ScoreKill;
  }

  public static Label getScoreFriends() {
    return ScoreFriends;
  }

  private void createTable(Pane pane) {
    double colWidth = WIDTH / (SIZE + 1);
    double colHeight = WIDTH / (SIZE + 1);
    for (int i = 0; i <= SIZE + 1; i++) {
      Line lineW = new Line(i * colWidth, 0, i * colWidth, HEIGHT);
      Line lineH = new Line(0, i * colHeight, WIDTH, i * colHeight);
      Label labelW = new Label(Integer.toString(i + 1));
      labelW.layoutXProperty().bind(pane.widthProperty().divide(SIZE + 1).multiply(i + 2).
              subtract(labelW.widthProperty().divide(2)).subtract(pane.widthProperty().
              divide((SIZE + 1) * 2)));
      labelW.layoutYProperty().bind(pane.heightProperty().divide((SIZE + 1) * 2).
              subtract(labelW.heightProperty().divide(2)));
      Label labelH = new Label(Integer.toString(i + 1));
      labelH.layoutXProperty().bind(pane.widthProperty().divide((SIZE + 1) * 2).
              subtract(labelH.widthProperty().divide(2)));
      labelH.layoutYProperty().bind(pane.heightProperty().divide(SIZE + 1).multiply(i + 2).
              subtract(labelH.heightProperty().divide(2)).subtract(pane.heightProperty().
              divide((SIZE + 1) * 2)));
      labelH.setStyle("-fx-font-size:18;" + "-fx-text-fill:white;" + "-fx-font-weight:bold;");
      labelW.setStyle("-fx-font-size:18;" + "-fx-text-fill:white;" + "-fx-font-weight:bold;");
      pane.getChildren().addAll(lineW, lineH, labelW, labelH);
    }
  }

  private void createSides(Pane pane) {
    Rectangle up = new Rectangle(0, 0, WIDTH, EntityPiece.PIECE_SIZE);
    Rectangle down = new Rectangle(0, 0, EntityPiece.PIECE_SIZE, HEIGHT);
    pane.getChildren().addAll(up, down);
  }

  private void createScoreBoard(BorderPane root) {
    Label TextEnnemies = new Label("Killed ennemy units");
    Label TextFriends = new Label("Friendly fire");
    ScoreKill = new Label("0");
    ScoreFriends = new Label("0");
    Region region1 = new Region();
    Region region2 = new Region();
    gp.setAlignment(Pos.CENTER);

    setAllItemsScoreBoard(TextEnnemies, TextFriends, ScoreKill, ScoreFriends);

    gp.add(TextEnnemies, 0, 0);
    gp.add(region1, 1, 0);
    gp.add(TextFriends, 2, 0);
    gp.add(ScoreKill, 0, 1);
    gp.add(region2, 1, 1);
    gp.add(ScoreFriends, 2, 1);

    region1.setPrefWidth(150);
  }

  private void setAllItemsScoreBoard(Label... labels) {
    for (int i = 0; i < labels.length; i++) {
      labels[i].setStyle("-fx-font-size: 30px;");
      labels[i].setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
      labels[i].setAlignment(Pos.CENTER);
    }
  }
}
