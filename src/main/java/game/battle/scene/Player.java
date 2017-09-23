package game.battle.scene;

import client.Observable;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;
import org.json.JSONObject;
import util.KeyBinder;
import util.Vector2D;

import java.awt.*;


/**
 * Created by sergio on 27/07/17.
 */
public class Player extends RichGameComponent implements Observable{

    int id;
    private double speed;
    private int fireRadius;
    private int bombAmount;
    KeyBinder kb;

    String nickName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return nickName != null ? nickName.equals(player.nickName) : player.nickName == null;
    }

    @Override
    public int hashCode() {
        return nickName != null ? nickName.hashCode() : 0;
    }
    private Vector2D oldPosition;

    public Player(Key[] controls, Color color,Vector2D tilePosition, String name){
        setAppearance(new Rectangle(color,w,h));
        setX(tilePosition.getX()*w);
        setY(tilePosition.getY()*h);
        setZ(1);
        nickName = name;
        kb= new KeyBinder(controls);
        fireRadius = 2;
        bombAmount = 2;
        speed = 2;
    }

    public void die(){
            destroy();
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

    public JSONObject toJson(){
        JSONObject data = new JSONObject();
        try {
            data.put("id",getId());
            data.put("x", getX());
            data.put("y", getY());
            data.put("name", nickName);
            return data;
        }catch (Exception e){
            throw new RuntimeException("Error - Json");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
