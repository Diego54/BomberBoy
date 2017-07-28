package util;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;
import scene.game.Player;

/**
 * Created by sergio on 28/07/17.
 */
public class KeyBinder{

    private CollisionDetector cd = CollisionDetector.INSTANCE;
    public static final KeyBinder INSTANCE = new KeyBinder();

    public void checkKeys(DeltaState state, Player player) {
        double x = player.getX();
        double y = player.getY();
        if (state.isKeyBeingHold(Key.LEFT) && !cd.collidesRectAgainstRect(player,player.unBloque).equals(cd.LEFT)) {
            player.setX(player.getX() - player.speed);
        }
        if (state.isKeyBeingHold(Key.UP) && !cd.collidesRectAgainstRect(player,player.unBloque).equals(cd.TOP)){
            player.setY(player.getY() - player.speed);
        }
        if (state.isKeyBeingHold(Key.RIGHT) && !cd.collidesRectAgainstRect(player, player.unBloque).equals(cd.RIGHT)){
            player.setX(player.getX() + player.speed);
        }

        if (state.isKeyBeingHold(Key.DOWN) && !cd.collidesRectAgainstRect(player,player.unBloque).equals(cd.BOT)){
            player.setY(player.getY() + player.speed);
        }
        if (state.isKeyPressed(Key.A)) {
            //player.dropBomb(state);
        }
    }

}
