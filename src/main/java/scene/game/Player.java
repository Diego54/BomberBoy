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

    private double speed;
    private int fireRadius;
    private int bombAmount;
    KeyBinder kb = KeyBinder.INSTANCE;

    public Player(){
        setAppearance(new Rectangle(Color.RED,w,h));
        setX(53);
        setY(46);
        setZ(3);

        fireRadius = 2;
        bombAmount = 2;
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

    public void dropBomb(){
        if (bombAmount > 0) {
            bombAmount --;
            Bomb b = new Bomb(this);
            getScene().addComponent(b);
            getScene().getGrid().addTile(b);
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getBombAmount() {
        return bombAmount;
    }

    public void setBombAmount(int bombAmount) {
        this.bombAmount = bombAmount;
    }

    public void restoreStock() {
        bombAmount++;
    }

    public int getFireRadius() {
        return fireRadius;
    }

    public void fireUp() {
        this.fireRadius++;
    }
}
