package game.battle.scene;

import com.uqbar.vainilla.GameComponent;
import util.Vector2D;

/**
 * Created by Pelotita on 31/7/2017.
 */
public class RichGameComponent extends GameComponent<Battlefield> {

    public int w = 53;
    public int h = 46;

    protected Vector2D getCenter(){
        return new Vector2D(getX()+(w/2),getY()+(h/2));
    }

    public Vector2D getTile(){
        Vector2D r = getCenter();
        return new Vector2D(r.getX()/w, r.getY()/h);
    }

    public Vector2D getPositionAsVector(){
        return new Vector2D(getX(),getY());
    }

    public void explode(){destroy();}

    public void applyEfectIn(Player player) {
        player.stepBack();
    }
}
