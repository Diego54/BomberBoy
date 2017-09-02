package game.battle.scene;

import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 26/8/2017.
 */
public class BombStockUp extends PowerUp {

    public BombStockUp(Vector2D tilePos) {
        super(tilePos, Color.BLUE);
    }

    @Override
    protected void efect(Player player) {
        player.restoreStock();
    }
}
