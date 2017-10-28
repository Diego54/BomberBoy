package game.battle.scene;

import com.uqbar.vainilla.DeltaState;
import org.json.JSONObject;

import java.awt.*;

/**
 * Created by Pelotita on 21/8/2017.
 */
public class Destructible extends Block {

    public Destructible(double x, double y) {
        super(x, y);
    }

    public Destructible(int x, int y) {
        super(x,y);
        this.setX(x*w);
        this.setY(y*h);
        setZ(1);
    }

    @Override
    public void update(DeltaState deltaState) {

    }

    @Override
    public void destroy() {
        PowerUpFactory puf = new PowerUpFactory();
        double chance = Math.random()*100;
        if(chance<20){
            PowerUp powerup = puf.getPowerUp(getPositionAsVector());
            getScene().getGrid().addTile(powerup);
            getScene().addComponent(powerup);
            powerup.notify("spawnPowerup");
        }else{
            getScene().getGrid().removeTile(this);
        }
        super.destroy();
    }

    @Override
    Color getColor() {
        return Color.GRAY;
    }
    public JSONObject toJson(){
        JSONObject data = new JSONObject();
        try {
            data.put("x", getTile().getX());
            data.put("y", getTile().getY());
            data.put("clazz", getClass());
            return data;
        }catch (Exception e){
            throw new RuntimeException("Error - Json");
        }
    }
}
