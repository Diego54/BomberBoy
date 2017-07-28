package scene.game;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

import java.awt.*;

/**
 * Created by sergio on 27/07/17.
 */
public class Block extends GameComponent<Battlefield> {

    public Block(){
        setAppearance(new Rectangle(Color.BLACK,20,20));
        setX(30);
        setY(50);
    }

}
