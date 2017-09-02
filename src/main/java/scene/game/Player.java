package scene.game;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;
import util.KeyBinder;
import util.Vector2D;

import java.awt.*;


/**
 * Created by sergio on 27/07/17.
 */
public class Player extends RichGameComponent {

    private double speed;
    private int fireRadius;
    private int bombAmount;

    KeyBinder kb;
    private Vector2D oldPosition;

    public Player(Key[] controls, Color color,Vector2D tilePosition){
        setAppearance(new Rectangle(color,w,h));
        setX(tilePosition.getX()*w);
        setY(tilePosition.getY()*h);
        setZ(1);
        kb= new KeyBinder(controls);
        fireRadius = 2;
        bombAmount = 2;
        speed = 2;
    }

    public void die(){
        this.setDestroyPending(true);
    }

    @Override
    public void update(DeltaState deltaState) {
        oldPosition = getPositionAsVector();
        kb.checkKeys(deltaState,this, this.getScene().getGrid());
        super.update(deltaState);
    }

    public void dropBomb(){
        TileMap grid = getScene().getGrid();
        if (bombAmount > 0 && grid.getTile(getTile())==null) {
            bombAmount --;
            Bomb b = new Bomb(this);
            getScene().addComponent(b);
            grid.addTile(b);
        }
    }

    public double getSpeed() {
        return speed;
    }

    public int getBombAmount() {
        return bombAmount;
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

    public void mustCollideWith(RichGameComponent obstacleLeft, boolean collide) {
        if(collide){
            obstacleLeft.applyEfectIn(this);
        }
    }

    public void stepBack() {
        setX(oldPosition.getX());
        setY(oldPosition.getY());
    }
}
