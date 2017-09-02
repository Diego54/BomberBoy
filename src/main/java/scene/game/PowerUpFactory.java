package scene.game;

import util.Vector2D;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by Pelotita on 2/9/2017.
 */
public class PowerUpFactory {
    Class<?extends PowerUp>[] commonPowerUps = new Class[]{FireRadiusUp.class, BombStockUp.class};
//    Class[] RarePowerups = {aegis,multibomb,etc};

    public PowerUp getPowerUp(Vector2D pos){
        Random generator = new Random();
        int i = generator.nextInt(commonPowerUps.length);
        PowerUp newInstance = null;
        try {
            newInstance = commonPowerUps[i].getDeclaredConstructor(Vector2D.class).newInstance(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newInstance;
    }

}
