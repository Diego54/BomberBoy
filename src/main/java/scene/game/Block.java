package scene.game;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

import java.awt.*;

/**
 * Created by sergio on 27/07/17.
 */
class Block extends RichGameComponent {

    public Block(double x, double y){
        setAppearance(new Rectangle(Color.BLACK,w,h));
        setZ(1);
        setX(x);
        setY(y);
    }

}
