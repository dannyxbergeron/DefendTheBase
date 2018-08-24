package com.DTB.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class determineParameters {

  private static int size;
  private static int missiles;

  determineParameters(String title, String message) {
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle(title);
    stage.setMinWidth(350);

    Label label = new Label(message);
    Label sizeLabel = new Label("Size of the radar: ");
    Label missileLabel = new Label("Number of missiles");
    TextField sizeText = new TextField();
    TextField missilesText = new TextField();
    Button okButton = new Button("Ok");
    okButton.setMinWidth(100);

    VBox vbCol1 = new VBox(sizeLabel, sizeText);
    vbCol1.setAlignment(Pos.CENTER);
    VBox vbCol2 = new VBox(missileLabel, missilesText);
    vbCol2.setAlignment(Pos.CENTER);
    HBox hb = new HBox(vbCol1, vbCol2);
    VBox vb = new VBox(hb, okButton);

    vb.setAlignment(Pos.CENTER);
    vb.setSpacing(10);
    vb.setPadding(new Insets(10, 40, 15, 40));
    hb.setSpacing(15);

    okButton.setOnAction(event -> {
      createContent.setSize(Integer.valueOf(sizeText.getText()));
      createContent.setMissiles(Integer.valueOf(missilesText.getText()));
      stage.close();
    });

    Scene scene = new Scene(vb);
    stage.setScene(scene);
    stage.showAndWait();
  }
}

