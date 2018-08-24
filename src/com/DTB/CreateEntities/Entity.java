package com.DTB.CreateEntities;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Entity {

  private List<EntityPiece> entityPieces = new ArrayList<>();
  private Group GroupEntity;
  private Random rand = new Random();
  private EntityType entityType;
  private int entityMovementStyle;

  private double posX;
  private double posY;
  private int xSpeed;
  private int ySpeed;

  Entity(Group group, String type, double xStart, double yStart, int xSp, int ySp, int entityMoveStyle) {
    GroupEntity = group;
    posX = xStart * EntityPiece.PIECE_SIZE;
    posY = yStart * EntityPiece.PIECE_SIZE;
    xSpeed = xSp;
    ySpeed = ySp;
    entityMovementStyle = entityMoveStyle;
    if (type.equals("FRIEND")) {
      entityType = EntityType.FRIENDLY;
    } else {
      entityType = EntityType.ENNEMY;
    }
    switch (rand.nextInt(4) + 1) {
      case 1:
        two();
        break;
      case 2:
        four();
        break;
      case 3:
        six();
        break;
      default:
        one();
        break;
    }
    for (EntityPiece ep : entityPieces) {
      ep.setTranslateX(posX + ep.getxTrans());
      ep.setTranslateY(posY + ep.getyTrans());
      GroupEntity.getChildren().add(ep);
    }
  }

  private void one() {
    entityPieces.add(new EntityPiece(entityType, 0, 0));
  }

  private void two() {
    int ori = rand.nextInt(2);
    if (ori == 0) {
      for (int i = 0; i < 2; i++) {
        entityPieces.add(new EntityPiece(entityType, i * EntityPiece.PIECE_SIZE, 0));
      }
    } else {
      for (int i = 0; i < 2; i++) {
        entityPieces.add(new EntityPiece(entityType, 0, i * EntityPiece.PIECE_SIZE));
      }
    }
  }

  private void four() {
    for (int i = 0; i < 2; i++) {
      for (int l = 0; l < 2; l++) {
        entityPieces.add(new EntityPiece(entityType, i * EntityPiece.PIECE_SIZE, l * EntityPiece.PIECE_SIZE));
      }
    }
  }

  private void six() {
    int ori = rand.nextInt(2);
    if (ori == 0) {
      for (int i = 0; i < 3; i++) {
        for (int l = 0; l < 2; l++) {
          entityPieces.add(new EntityPiece(entityType, i * EntityPiece.PIECE_SIZE, l * EntityPiece.PIECE_SIZE));
        }
      }
    } else {
      for (int i = 0; i < 3; i++) {
        for (int l = 0; l < 2; l++) {
          entityPieces.add(new EntityPiece(entityType, l * EntityPiece.PIECE_SIZE, i * EntityPiece.PIECE_SIZE));
        }
      }
    }
  }

  void moveEntity(int time) {
    if (xSpeed == 0) {
    } else if (time % xSpeed == 0 && xSpeed > 0) {
      posX = posX + EntityPiece.PIECE_SIZE;
      for (EntityPiece ep : entityPieces) {
        ep.setTranslateX(posX + ep.getxTrans());
      }
    } else if (time % xSpeed == 0 && xSpeed < 0) {
      posX = posX - EntityPiece.PIECE_SIZE;
      for (EntityPiece ep : entityPieces) {
        ep.setTranslateX(posX + ep.getxTrans());
      }
    }

    if (ySpeed == 0) {
    } else if (time % ySpeed == 0 && ySpeed > 0) {
      posY = posY + EntityPiece.PIECE_SIZE;
      for (EntityPiece ep : entityPieces) {
        ep.setTranslateY(posY + ep.getyTrans());
      }
    } else if (time % ySpeed == 0 && ySpeed < 0) {
      posY = posY - EntityPiece.PIECE_SIZE;
      for (EntityPiece ep : entityPieces) {
        ep.setTranslateY(posY + ep.getyTrans());
      }
    }

    if (posY < 0) {
      for (EntityPiece ep : entityPieces) {
        ep.setVisible(false);
      }
    } else if (posY >= 0) {
      for (EntityPiece ep : entityPieces) {
        ep.setVisible(true);
      }
    }
  }

  public void removeEntity() {
    for (EntityPiece ep : entityPieces) {
      ep.setTranslateX(posX + ep.getxTrans());
      ep.setTranslateY(posY + ep.getyTrans());
      GroupEntity.getChildren().remove(ep);
    }
  }

  public List<EntityPiece> getEntityPieces() {
    return entityPieces;
  }

  public Group getGroupEntity() {
    return GroupEntity;
  }

  public int getEntityMovementStyle() {
    return entityMovementStyle;
  }

  public int getxSpeed() {
    return xSpeed;
  }

  public int getySpeed() {
    return ySpeed;
  }
}
