package scene.game;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

import java.awt.*;

/**
 * Created by sergio on 27/07/17.
 */
abstract class Block extends RichGameComponent {

    public Block(double x, double y){
        setAppearance(new Rectangle(this.getColor(),w,h));

        setX(x);
        setY(y);
    }

    public Block(){}

    abstract Color getColor();

}
