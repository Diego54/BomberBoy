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
    public GameComponent<Battlefield> unBloque;
    KeyBinder kb = KeyBinder.INSTANCE;

    public Player(GameComponent gc){
        setAppearance(new Rectangle(Color.RED,53,46));
        setX(20);
        setY(20);
        speed = 0.7;
        unBloque = gc;
    }

    public void die(){
        this.setDestroyPending(true);
    }

    @Override
    public void update(DeltaState deltaState) {
        super.update(deltaState);
        kb.checkKeys(deltaState,this);
    }

}
