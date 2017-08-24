package scene.game;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import util.KeyBinder;

import java.awt.*;


/**
 * Created by sergio on 27/07/17.
 */
public class Player extends RichGameComponent {

    public double speed;
    KeyBinder kb = KeyBinder.INSTANCE;

    public Player(){
        setAppearance(new Rectangle(Color.RED,w,h));
        setX(53);
        setY(46);
        setZ(3);
        speed = 2;
    }

    public void die(){
        this.setDestroyPending(true);
    }

    @Override
    public void update(DeltaState deltaState) {
        kb.checkKeys(deltaState,this, this.getScene().getGrid());
        super.update(deltaState);
    }

}
