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

    private Key[] controls;

    private CollisionDetector cd = CollisionDetector.INSTANCE;
    public static final KeyBinder INSTANCE = new KeyBinder();

    public KeyBinder(){}

    public KeyBinder(Key[] controls){
        this.controls = controls;
    }

    public void checkKeys(DeltaState state, Player player, TileMap grid) {
        Vector2D playerPos = player.getTile();

        if (state.isKeyBeingHold(controls[0]) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPos.getX() - 1, playerPos.getY())).equals(cd.LEFT)) {
            player.setX(player.getX() - player.getSpeed());
        }
        if (state.isKeyBeingHold(controls[1]) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPos.getX() , playerPos.getY() -1)).equals(cd.TOP)){
            player.setY(player.getY() - player.getSpeed());
        }
        if (state.isKeyBeingHold(controls[2]) && !cd.collidesRectAgainstRect(player, grid.getTile(playerPos.getX() + 1, playerPos.getY())).equals(cd.RIGHT)){
            player.setX(player.getX() + player.getSpeed());
        }
        if (state.isKeyBeingHold(controls[3]) && !cd.collidesRectAgainstRect(player,grid.getTile(playerPos.getX(), playerPos.getY() + 1)).equals(cd.BOT)){
            player.setY(player.getY() + player.getSpeed());
        }

        if (state.isKeyPressed(controls[4])) {
            player.dropBomb();
        }
    }

}
