package com.DTB.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    new determineParameters("Set Parameters", "Choose your parameters: ");
    new createContent(stage);

  }
}
