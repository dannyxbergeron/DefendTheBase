package com.DTB.CreateEntities;

import com.DTB.GUI.createContent;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class createAllEntities {

  private static List<Entity> entityList = new ArrayList<>();
  private static Random random = new Random();
  private static Group entityType;
  private static String entityTypeString;
  private static int xStart, yStart, xSpeed, ySpeed, entityMovementStyle;

  public createAllEntities() {
    for (int i = 0; i < createContent.getSize() / 3; i++) {
      createSingleEntity();
    }
  }

  public static void createSingleEntity() {
    int randType = random.nextInt(3);
    if (randType == 0) {
      entityType = createContent.getFriends();
      entityTypeString = "FRIEND";
    } else {
      entityType = createContent.getEnnemies();
      entityTypeString = "ENNEMY";
    }
    int randLoc = random.nextInt(3);
    switch (randLoc) {
      case 0:
        vertical();
        break;
      case 1:
        horizontal();
        break;
      case 2:
        diagonal();
        break;
      default:
        break;
    }
    entityList.add(new Entity(entityType, entityTypeString, xStart, yStart, xSpeed, ySpeed, entityMovementStyle));
  }

  private static void vertical() {
    xStart = random.nextInt(createContent.getSize()) + 1;
    ySpeed = generateSpeed();
    yStart = -3 - random.nextInt(5);
    xSpeed = 0;
    entityMovementStyle = 0;
  }

  private static void horizontal() {
    yStart = random.nextInt(createContent.getSize()) + 1;
    if (random.nextBoolean()) {
      xSpeed = generateSpeed();
      xStart = -3 - random.nextInt(5);
      entityMovementStyle = 1;
    } else {
      xSpeed = generateSpeed() * -1;
      xStart = createContent.getSize() + (3 + random.nextInt(5));
      entityMovementStyle = 2;
    }
    ySpeed = 0;
  }

  private static void diagonal() {
    int randomDiago = random.nextInt(4);
    switch (randomDiago) {
      case 0:
        diagoCases(-1, 0, -1, 0, 1, 1, 3);
        break;
      case 1:
        diagoCases(1, createContent.getSize(), 1, createContent.getSize(), -1, -1, 4);
        break;
      case 2:
        diagoCases(-1, 0, 1, createContent.getSize(), 1, -1, 5);
        break;
      default:
        diagoCases(1, createContent.getSize(), -1, 0, -1, 1, 6);
        break;
    }
  }

  private static void diagoCases(int i, int i1, int i2, int i3, int i4, int i5, int style) {
    xStart = i * (random.nextInt(5)) + i1;
    yStart = i2 * (random.nextInt(5) + i3);
    xSpeed = generateSpeed() * i4;
    ySpeed = generateSpeed() * i5;
    entityMovementStyle = style;
  }

  public static void update(int time) {
    for (Entity e : entityList) {
      e.moveEntity(time);
    }
  }

  public static void remove() {
    for (int i = 0; i < 2; i++) {
      entityList.get(i).removeEntity();
      entityList.remove(i);
    }
  }

  public static int generateSpeed() {
    if (random.nextInt(3) == 0) {
      return 1;
    } else {
      return 2;
    }
  }

  public static List<Entity> getEntityList() {
    return entityList;
  }
}
