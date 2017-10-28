package game.battle.scene;

import org.json.JSONObject;
import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 26/8/2017.
 */
abstract public class PowerUp extends RichGameComponent{

    public PowerUp(Vector2D tilePos, Color color){
        setAppearance(new com.uqbar.vainilla.appearances.Rectangle(color,w,h));
        this.setX(tilePos.getX());
        this.setY(tilePos.getY());
    }

    @Override
    public void explode() {
        super.explode();
        getScene().getGrid().removeTile(this);
    }

    @Override
    public void applyEfectIn(Player player) {
        efect(player);
        getScene().getGrid().removeTile(this);
        destroy();
    }

    public JSONObject toJson(){
        JSONObject data = new JSONObject();
        try {
            data.put("x", getX());
            data.put("y", getY());
            data.put("clazz", getClass());
            return data;
        }catch (Exception e){
            throw new RuntimeException("Error - Json");
        }
    }

    abstract protected void efect(Player player);

    public void notify(String message){
        getScene().gameClient.notifyPowerupSpawn(message, this);
    }

}
