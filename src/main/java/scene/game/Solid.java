package scene.game;

import java.awt.*;

/**
 * Created by Pelotita on 21/8/2017.
 */
public class Solid extends Block {
    public Solid(double x, double y) {
        super(x, y);
    }

    @Override
    Color getColor() {
        setZ(2);
        return Color.BLACK;
    }
}
