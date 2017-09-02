package game.battle.scene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import util.Vector2D;

import java.awt.*;

/**
 * Created by Pelotita on 24/8/2017.
 */
public class Bomb extends RichGameComponent {

    private Player player;
    private double elapsed = 0.0;
    private double countdown = 1.5;
    CollisionDetector cd = CollisionDetector.INSTANCE;

    public Bomb(Player player){
        Vector2D playerpos = player.getTile();
        this.setX((playerpos.getX().intValue()*w)+12.5);
        this.setY((playerpos.getY().intValue()*h)+9);//TODO arreglar este hardcodeo
        this.player = player;
        this.setAppearance(new Circle(Color.BLACK, 28));
    }

    @Override
    public void update(DeltaState deltaState) {
        super.update(deltaState);
        this.elapsed += deltaState.getDelta();
        if (elapsed >= countdown){
            this.explode();
        }
    }
    @Override
    public void explode(){
        spawnFire();
        destroy();
        getScene().getGrid().removeTile(this);
        getPlayer().restoreStock();
    }

    private void spawnFire() {
        Fire f1 = new Fire(getTile());
        this.getScene().addComponent(f1);
        Vector2D original = getTile();

        Vector2D firePos1 = original.clone();
        Vector2D firePos2 = original.clone();
        Vector2D firePos3 = original.clone();
        Vector2D firePos4 = original.clone();
        for (int i = 0; i < getPlayer().getFireRadius(); i++) {
            firePos1.setX(firePos1.getX()+1);
            RichGameComponent elementOnTile = getScene().getGrid().getTile(firePos1);
            if (getTile().getX() >= 0){
                Fire f3 = new Fire(firePos1);
                this.getScene().addComponent(f3);
                if(elementOnTile!=null) {
                    break;
                }
            }else{
                break;
            }
        }
        for (int i = 0; i < getPlayer().getFireRadius(); i++) {
            firePos2.setY(firePos2.getY()+1);
            RichGameComponent elementOnTile = getScene().getGrid().getTile(firePos2);
            if (getTile().getX() >= 0){
                Fire f3 = new Fire(firePos2);
                this.getScene().addComponent(f3);
                if(elementOnTile!=null) {
                    break;
                }
            }else{
                break;
            }
        }
        for (int i = 0; i < getPlayer().getFireRadius(); i++) {
            firePos3.setX(firePos3.getX()-1);
            RichGameComponent elementOnTile = getScene().getGrid().getTile(firePos3);
            if (getTile().getX() >= 0){
                Fire f3 = new Fire(firePos3);
                this.getScene().addComponent(f3);
                if(elementOnTile!=null) {
                    break;
                }
            }else{
                break;
            }
        }
        for (int i = 0; i < getPlayer().getFireRadius(); i++) {
            firePos4.setY(firePos4.getY()-1);
            RichGameComponent elementOnTile = getScene().getGrid().getTile(firePos4);
            if (getTile().getX() >= 0){
                Fire f3 = new Fire(firePos4);
                this.getScene().addComponent(f3);
                if(elementOnTile!=null) {
                    break;
                }
            }else{
                break;
            }
        }

    }

    public Player getPlayer() {
        return player;
    }

}
