package scene.game;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 24/8/2017.
 */
public class Fire extends RichGameComponent {

    private double elapsed = 0.0;
    private double countdown = 0.6;
    private boolean mustBurn;

    public Fire(Vector2D pos) {
        this.setX((pos.getX().intValue()*w)+16.5);
        this.setY((pos.getY().intValue()*h)+13);
        this.setAppearance(new Circle(new Color(255, 77, 0), 20));
        mustBurn = true;
        setZ(2);
    }

    public void update(DeltaState ds) {
        this.elapsed += ds.getDelta();
        RichGameComponent rgc = getScene().getGrid().getTile(getTile());
        if (rgc != null && mustBurn){
            rgc.explode();
            mustBurn = false;
        }
        if (elapsed >= countdown) {
            this.destroy();
        }
    }
}
