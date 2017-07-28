package scene.game;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

import java.awt.*;


/**
 * Created by sergio on 27/07/17.
 */
public class Player extends GameComponent<Battlefield> {

    double speed;
    GameComponent<Battlefield> unBloque;
    String TOP = "TOP";
    String BOT = "BOT";
    String LEFT = "LEFT";
    String RIGHT = "RIGHT";

    public Player(GameComponent gc){
        setAppearance(new Rectangle(Color.RED,20,20));
        setX(20);
        setY(20);
        speed = 3;
        unBloque = gc;
    }

    String collideSide(){
        double w = 20;
        double h = 20;
        double dx = (this.getX()+10) - (unBloque.getX()+10);
        double dy = (this.getY()+10) - (unBloque.getY()+10);

        if (Math.abs(dx) <= w && Math.abs(dy) <= h){
            /* collision! */
            double wy = w * dy;
            double hx = h * dx;

            if (wy > hx) {
                if (wy > -hx) {
                    return TOP;
                } else {
                    return RIGHT;
                }
            }else{
                if (wy > -hx) {
                    return LEFT;
                }else{
                    return BOT;
                }
            }
        }
        return "";
    }

    @Override
    public void update(DeltaState deltaState) {
        super.update(deltaState);
        checkKeys(deltaState);
    }

    public void checkKeys(DeltaState state) {
        double x = this.getX();
        double y = this.getY();
        if (state.isKeyBeingHold(Key.LEFT) && !collideSide().equals(LEFT)) { //&& isAllignedTo(this.getY(), h) && inFieldLimit() && this.puedeMoverLeft()) {
            System.out.println(collideSide());
            this.setX(this.getX() - speed);
            //fixPosition(x, y);
        }

        if (state.isKeyBeingHold(Key.UP) && !collideSide().equals(TOP)){// && isAllignedTo(this.getX(), w) && inFieldLimit() && this.puedeMoverUp()) {
            System.out.println(collideSide());
            this.setY(this.getY() - speed);
            //fixPosition(x, y);
        }
        if (state.isKeyBeingHold(Key.RIGHT) && !collideSide().equals(RIGHT)){// && isAllignedTo(this.getY(), h) && inFieldLimit() && this.puedeMoverRight()) {
            System.out.println(collideSide());
            this.setX(this.getX() + speed);
            //fixPosition(x, y);
        }

        if (state.isKeyBeingHold(Key.DOWN) && !collideSide().equals(BOT)){//&& isAllignedTo(this.getX(), w) && inFieldLimit() && this.puedeMoverDown()) {
            System.out.println(collideSide());
            this.setY(this.getY() + speed);
            //fixPosition(x, y);
        }
        if (state.isKeyPressed(Key.A)) {
            //this.dropBomb(state);
        }
    }

}
