package scene.game;

import com.uqbar.vainilla.appearances.*;
import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 26/8/2017.
 */
public class PowerUp extends RichGameComponent{

    public PowerUp(Vector2D tilePos, Color color){
        setAppearance(new com.uqbar.vainilla.appearances.Rectangle(color,w,h));
        this.setX(tilePos.getX());
        this.setY(tilePos.getY());
    }

    @Override
    public Boolean pickeable() {
        return true;
    }
}
