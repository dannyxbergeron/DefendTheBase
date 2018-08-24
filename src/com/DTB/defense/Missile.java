package com.DTB.defense;

import com.DTB.CreateEntities.Entity;
import com.DTB.CreateEntities.EntityPiece;
import com.DTB.CreateEntities.EntityType;
import com.DTB.CreateEntities.createAllEntities;
import com.DTB.GUI.createContent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Missile {

  private static Group group = createContent.getMissileGroup();
  private static ArrayList<Missile> missilesList = new ArrayList<>();
  private static Random rand = new Random();
  private static int kill = 0;
  private static int friendlyFire = 0;
  private ImageView missileImageView;
  private double locationY = 555;
  private Boolean canMove = false;

  public Missile(double locationX) {
    missileImageView = new javafx.scene.image.ImageView(
            new Image("/images/missile.png", 45, 45, true, true));
    missileImageView.setCache(true);
    group.getChildren().add(missileImageView);

    missileImageView.setTranslateX(locationX);
    missileImageView.setTranslateY(locationY);
    canMove = true;
  }

  public static void launchMissile() {
    if (missilesList.size() < createContent.getMissiles()) {
      double x = autoLaunch.startXpos() * EntityPiece.PIECE_SIZE
              + EntityPiece.PIECE_SIZE / 2 - 6;
      if (x != 0) {
        missilesList.add(new Missile(x));
        System.out.println("----------------------" + (missilesList.size() + 1));
      }
    }
  }

  public static ArrayList<Missile> getMissilesList() {
    return missilesList;
  }

  public void move() {
    if (canMove) {
      locationY -= 2;
      missileImageView.setTranslateY(locationY);
      if (locationY < 0) {
        group.getChildren().remove(missileImageView);
      }
      for (Entity entity : createAllEntities.getEntityList()) {
        for (EntityPiece piece : entity.getEntityPieces()) {
          if (missileImageView.getBoundsInParent().intersects(piece.getBoundsInParent())) {
            entity.getGroupEntity().getChildren().remove(piece);
            entity.getEntityPieces().remove(piece);
            group.getChildren().remove(missileImageView);
            if (piece.getEntityType() == EntityType.ENNEMY) {
              kill++;
              createContent.getScoreKill().setText(Integer.toString(kill));
            } else {
              friendlyFire++;
              createContent.getScoreFriends().setText(Integer.toString(friendlyFire));
            }
            canMove = false;
            System.out.println("missile: " + (int) (locationY / EntityPiece.PIECE_SIZE));
            return;
          }
        }
      }
    }
  }
}
