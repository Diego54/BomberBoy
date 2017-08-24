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
    }

    @Override
    public void update(DeltaState deltaState) {

    }

    @Override
    Color getColor() {
        return Color.GRAY;
    }
}
