package game.battle.scene;

import com.uqbar.vainilla.DeltaState;

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
//            FireRadiusUp powerup = new FireRadiusUp(getPositionAsVector());
            PowerUp powerup = puf.getPowerUp(getPositionAsVector());
            getScene().getGrid().addTile(powerup);
            getScene().addComponent(powerup);
        }else{
            getScene().getGrid().removeTile(this);
        }
        super.destroy();
    }

    @Override
    Color getColor() {
        return Color.GRAY;
    }
}
