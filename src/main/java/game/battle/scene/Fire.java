package game.battle.scene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import util.Vector2D;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

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

    private void killClosePlayers(List<Player> players){
        List<Player> playersAux = players;
        Iterator<Player> iterator = playersAux.iterator();
        while(iterator.hasNext()) {
            Player p = iterator.next();
            if (CollisionDetector.INSTANCE.collidesCircleAgainstRect(getX(), getY(), 10, p.getX(), p.getY(), w, h)) {
                iterator.remove();
                p.die();
            }
        }
    }

    public void update(DeltaState ds) {
        this.elapsed += ds.getDelta();
        List<Player> alivePlayers = getScene().getPlayers();
        RichGameComponent rgc = getScene().getGrid().getTile(getTile());
        if (rgc != null && mustBurn){
            rgc.explode();
            mustBurn = false;
        }
        killClosePlayers(alivePlayers);
        if (elapsed >= countdown) {
            this.destroy();
        }
    }
}
