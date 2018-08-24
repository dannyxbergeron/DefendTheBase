package com.DTB.defense;

import com.DTB.CreateEntities.Entity;
import com.DTB.CreateEntities.EntityPiece;
import com.DTB.CreateEntities.createAllEntities;
import com.DTB.GUI.createContent;

import java.util.Random;

class autoLaunch {

  private static int startX = 0;
  private static double missileSpeed = 1 / (((EntityPiece.PIECE_SIZE / 2) * 5) / 1000);

  private static int[] secureCol;
  private static Random rand = new Random();

  static int startXpos() {
    for (Entity entity : createAllEntities.getEntityList()) {
      for (EntityPiece piece : entity.getEntityPieces()) {
        if (piece.getPieceX() > 0 && piece.getPieceY() > 0
                && piece.getPieceX() <= createContent.getSize() && piece.getPieceY() <= createContent.getSize()) {
          switch (entity.getEntityMovementStyle()) {
            case 0:
              vert(piece, entity);
            case 1:
              hori(1, piece, entity);
            case 2:
              hori(-1, piece, entity);
            case 3:
              diag(1, 1, piece, entity);
            case 4:
              diag(-1, -1, piece, entity);
            case 5:
              diag(1, -1, piece, entity);
            case 6:
              diag(-1, 1, piece, entity);
            default:
              continue;
          }
        }
      }
    }
    computeStuff cs = new computeStuff();
    secureCol = cs.computedStuffList();
    if (secureCol.length - 1 > 0) {
      return secureCol[rand.nextInt(secureCol.length - 1) + 1];
    } else {
      return 0;
    }
  }

  private static void vert(EntityPiece piece, Entity entity) {
    piece.setxHit(piece.getPieceX());
    if (entity.getySpeed() == 1) {
      piece.setyHit((int) ((createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed + 2) * 2 + piece.getPieceY()));
    } else if (entity.getySpeed() == 2) {
      piece.setyHit((int) ((createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed + 1) * 1 + piece.getPieceY()));
    }
  }

  private static void hori(int xMoveSide, EntityPiece piece, Entity entity) {
    piece.setyHit(piece.getPieceY());
    double distance = createContent.getSize() - piece.getPieceY();
    double timeToGetThere = distance / missileSpeed;
    if (entity.getxSpeed() == 1 || entity.getxSpeed() == -1) {
      if (xMoveSide == 1) {
        piece.setxHit(piece.getPieceX() + (int) (timeToGetThere / 0.5 + 0.5));
      } else if (xMoveSide == -1) {
        piece.setxHit(piece.getPieceX() + 1 - (int) (timeToGetThere / 0.5));
      }
    } else if (entity.getxSpeed() == 2 || entity.getxSpeed() == -2) {
      if (xMoveSide == 1) {
        piece.setxHit(piece.getPieceX() + (int) (timeToGetThere / 1 + 0.5));
      } else if (xMoveSide == -1) {
        piece.setxHit(piece.getPieceX() + 1 - (int) (timeToGetThere / 1));
      }
    }
  }

  private static void diag(int xMoveSide, int yMoveSide, EntityPiece piece, Entity entity) {
    if (entity.getySpeed() == 1) {
      piece.setyHit((int) ((createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed + 2) * 2 + piece.getPieceY()));
    } else if (entity.getySpeed() == 2) {
      piece.setyHit((int) ((createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed + 1) * 1 + piece.getPieceY()));
    } else if (entity.getySpeed() == -1) {
      piece.setyHit((int) (piece.getPieceY() - (createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed - 2) * 2));
    } else if (entity.getySpeed() == -2) {
      piece.setyHit((int) (piece.getPieceY() - (createContent.getSize() - (piece.getPieceY()))
              / (missileSpeed - 1) * 1));
    }

    double distance = createContent.getSize() - piece.getPieceY();
    double timeToGetThere = distance / missileSpeed;
    if (entity.getxSpeed() == 1 || entity.getxSpeed() == -1) {
      if (xMoveSide == 1) {
        piece.setxHit(piece.getPieceX() + (int) (timeToGetThere / 0.3 + 0.5));
      } else if (xMoveSide == -1) {
        piece.setxHit(piece.getPieceX() + 1 - (int) (timeToGetThere / 0.3));
      }
    } else if (entity.getxSpeed() == 2 || entity.getxSpeed() == -2) {
      if (xMoveSide == 1) {
        piece.setxHit(piece.getPieceX() + (int) (timeToGetThere / .7 + 0.5));
      } else if (xMoveSide == -1) {
        piece.setxHit(piece.getPieceX() + 1 - (int) (timeToGetThere / .7));
      }
    }
  }
}
