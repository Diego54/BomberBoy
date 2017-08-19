package scene.game;

import com.uqbar.vainilla.GameComponent;

/**
 * Created by Pelotita on 31/7/2017.
 */
public class RichGameComponent extends GameComponent<Battlefield> {

    public int w = 53;
    public int h = 46;

    protected int[] getCenter(){
        int[] r = new int[2];
        r[0] = (int) getX()+(w/2);
        r[1] = (int) getY()+(h/2);
        return r;
    }

    public int[] getTile(){
        int[] r = getCenter();
        r[0] = r[0]/w;
        r[1] = r[1]/h;
        return r;
    }
}
