package scene.game;

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
        double chance = Math.random()*100;
//        if(chance<100){
//            FireRadiusUp powerup = new FireRadiusUp(getPositionAsVector());
//            getScene().getGrid().addTile(powerup);
//            getScene().addComponent(powerup);
//            TileMap coso = getScene().getGrid();
//            coso.hashCode();
//        }
        super.destroy();
        getScene().getGrid().removeTile(this);
    }

    @Override
    Color getColor() {
        return Color.GRAY;
    }
}
