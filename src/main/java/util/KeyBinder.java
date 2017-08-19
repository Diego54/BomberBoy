package util;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;
import scene.game.Player;
import scene.game.RichGameComponent;
import scene.game.TileMap;

import java.awt.*;

/**
 * Created by sergio on 28/07/17.
 */
public class KeyBinder{

    private CollisionDetector cd = CollisionDetector.INSTANCE;
    public static final KeyBinder INSTANCE = new KeyBinder();

    public void checkKeys(DeltaState state, Player player, TileMap grid) {
        int playerPosx = player.getTile()[0];
        int playerPosy = player.getTile()[1];

        if (state.isKeyBeingHold(Key.LEFT) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPosx - 1, playerPosy)).equals(cd.LEFT)) {
            player.setX(player.getX() - player.speed);
        }
        if (state.isKeyBeingHold(Key.UP) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPosx , playerPosy -1)).equals(cd.TOP)){
            player.setY(player.getY() - player.speed);
        }
        if (state.isKeyBeingHold(Key.RIGHT) && !cd.collidesRectAgainstRect(player, grid.getTile(playerPosx + 1, playerPosy)).equals(cd.RIGHT)){
            player.setX(player.getX() + player.speed);
        }
        if (state.isKeyBeingHold(Key.DOWN) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPosx, playerPosy + 1)).equals(cd.BOT)){
            player.setY(player.getY() + player.speed);
        }

        if (state.isKeyPressed(Key.A)) {
            //player.dropBomb(state);
        }
    }

}
