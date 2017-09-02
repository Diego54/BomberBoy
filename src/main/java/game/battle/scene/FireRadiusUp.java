package game.battle.scene;

import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 26/8/2017.
 */
public class FireRadiusUp extends PowerUp {
    public FireRadiusUp(Vector2D tilePos) {
        super(tilePos, Color.ORANGE);
    }

    @Override
    protected void efect(Player player) {
        player.fireUp();
    }
}
