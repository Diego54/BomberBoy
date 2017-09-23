package util;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;
import game.battle.scene.Player;

import game.battle.scene.RichGameComponent;
import game.battle.scene.TileMap;
/**
 * Created by sergio on 28/07/17.
 */
public class KeyBinder{

    private Key[] controls;

    private CollisionDetector cd = CollisionDetector.INSTANCE;

    public KeyBinder(Key[] controls){
        this.controls = controls;
    }

    public void checkKeys(DeltaState state, Player player, TileMap grid) {
        Vector2D playerPos = player.getTile();

        RichGameComponent obstacleLeft = grid.getTile(playerPos.getX() - 1, playerPos.getY());
        RichGameComponent obstacleTop = grid.getTile(playerPos.getX() , playerPos.getY() -1);
        RichGameComponent obstacleRight = grid.getTile(playerPos.getX() + 1, playerPos.getY());
        RichGameComponent obstacleBot = grid.getTile(playerPos.getX(), playerPos.getY() + 1);

        if (state.isKeyBeingHold(controls[0])){
            player.setX(player.getX() - player.getSpeed());
            player.mustCollideWith(obstacleLeft,cd.collidesRectAgainstRect(player,obstacleLeft).equals(cd.LEFT));
            player.notify(player.toJson());
        }
        if (state.isKeyBeingHold(controls[1])){
            player.setY(player.getY() - player.getSpeed());
            player.mustCollideWith(obstacleTop,cd.collidesRectAgainstRect(player,obstacleTop).equals(cd.TOP));
            player.notify(player.toJson());
        }
        if (state.isKeyBeingHold(controls[2])){
            player.setX(player.getX() + player.getSpeed());
            player.mustCollideWith(obstacleRight,cd.collidesRectAgainstRect(player, obstacleRight).equals(cd.RIGHT));
            player.notify(player.toJson());
        }
        if (state.isKeyBeingHold(controls[3])){
            player.setY(player.getY() + player.getSpeed());
            player.mustCollideWith(obstacleBot,cd.collidesRectAgainstRect(player,obstacleBot).equals(cd.BOT));
            player.notify(player.toJson());
        }

        if (state.isKeyPressed(controls[4])) {
            player.dropBomb();
            player.notifyDropedBomb(player.toJson());
        }
    }

}
