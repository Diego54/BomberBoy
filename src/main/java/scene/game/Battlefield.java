package scene.game;

import com.uqbar.vainilla.GameScene;

/**
 * Created by sergio on 27/07/17.
 */
public class Battlefield extends GameScene {
    public Battlefield(){
        Block unbloque = new Block();
        addComponent(new Player(unbloque));
        addComponent(unbloque);
    }
}
