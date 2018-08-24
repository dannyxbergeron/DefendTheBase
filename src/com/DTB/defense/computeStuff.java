package com.DTB.defense;

import com.DTB.CreateEntities.Entity;
import com.DTB.CreateEntities.EntityPiece;
import com.DTB.CreateEntities.EntityType;
import com.DTB.CreateEntities.createAllEntities;
import com.DTB.GUI.createContent;

import java.util.ArrayList;
import java.util.List;

public class computeStuff {

  private int[] secureCol;
  private Boolean[] colBool = new Boolean[createContent.getSize()];
  private int[] colYHit = new int[createContent.getSize()];

  public int[] computedStuffList() {

    for (int i = 0; i < createContent.getSize(); i++) {
      colBool[i] = false;
      colYHit[i] = 0;
    }

    for (Entity entity : createAllEntities.getEntityList()) {
      for (EntityPiece piece : entity.getEntityPieces()) {
        if (piece.getPieceX() > 0 && piece.getPieceY() > 0
                && piece.getPieceX() <= createContent.getSize() && piece.getPieceY() <= createContent.getSize()) {

          if (piece.getxHit() > 0 && piece.getxHit() < createContent.getSize()
                  && piece.getyHit() >= colYHit[piece.getxHit() - 1]) {
            if (piece.getEntityType() == EntityType.FRIENDLY) {
              colBool[piece.getxHit() - 1] = false;
            } else if (piece.getEntityType() == EntityType.ENNEMY && piece.getyHit() > colYHit[piece.getxHit() - 1]) {
              colBool[piece.getxHit() - 1] = true;
            }
            colYHit[piece.getxHit() - 1] = piece.getyHit();
          }

        }
      }
    }
    List<Integer> temp = new ArrayList<>();
    for (int l = 0; l < colBool.length; l++) {
      if (colBool[l] == true) {
        temp.add(l);
      }
    }
    secureCol = new int[temp.size()];
    for (int k = 0; k < temp.size(); k++) {
      secureCol[k] = temp.get(k) + 1;
    }
    return secureCol;
  }
}
