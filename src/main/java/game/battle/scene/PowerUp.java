package game.battle.scene;

import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 26/8/2017.
 */
abstract public class PowerUp extends RichGameComponent{

    public PowerUp(Vector2D tilePos, Color color){
        setAppearance(new com.uqbar.vainilla.appearances.Rectangle(color,w,h));
        this.setX(tilePos.getX());
        this.setY(tilePos.getY());
    }

    @Override
    public void explode() {
        super.explode();
        getScene().getGrid().removeTile(this);
    }

    @Override
    public void applyEfectIn(Player player) {
        efect(player);
        getScene().getGrid().removeTile(this);
        destroy();
    }

    abstract protected void efect(Player player);

}
