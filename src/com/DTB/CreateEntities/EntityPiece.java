package com.DTB.CreateEntities;

import com.DTB.GUI.createContent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EntityPiece extends Rectangle {
  public static double PIECE_SIZE = createContent.getWIDTH() / ((double) createContent.getSize() + 1);
  private EntityType entityType;
  private double xTrans, yTrans;
  private int xHit, yHit;

  EntityPiece(EntityType ET, double x, double y) {
    setWidth(PIECE_SIZE);
    setHeight(PIECE_SIZE);
    entityType = ET;
    this.xTrans = x;
    this.yTrans = y;
    setTranslateX(xTrans);
    setTranslateY(yTrans);
    if (entityType == EntityType.FRIENDLY) {
      setFill(Color.BLUE);
      setOpacity(0.5);
    } else if (entityType == EntityType.ENNEMY) {
      setFill(Color.GREEN);
      setOpacity(0.5);
    }
  }

  double getxTrans() {
    return xTrans;
  }

  double getyTrans() {
    return yTrans;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public int getPieceX() {
    return (int) (getTranslateX() / PIECE_SIZE);
  }

  public int getPieceY() {
    return (int) (getTranslateY() / PIECE_SIZE);
  }

  public int getxHit() {
    return xHit;
  }

  public void setxHit(int xHit) {
    this.xHit = xHit;
  }

  public int getyHit() {
    return yHit;
  }

  public void setyHit(int yHit) {
    this.yHit = yHit;
  }
}
