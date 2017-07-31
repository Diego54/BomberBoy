package scene.game;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;

/**
 * Created by sergio on 27/07/17.
 */
public class Battlefield extends GameScene {
    public Battlefield(){
        Block unbloque = new Block(200,200);
        addComponent(new Player(unbloque));
        buildBattlefield();
        addComponent(unbloque);
    }

    public void buildBattlefield(){
        buildFieldLimits();
        //buildSolidBlocks();
        //fillDestructibleBlock();
        //placePlayers();
    }

    private void buildFieldLimits() {
        buildLine(0,0,15,0);
        buildLine(0,552,15,0);
        buildLine(0,46,11,1);
        buildLine(742,46,11,1);
    }

    private void buildLine(double xPos, double yPos, int blockAmount, int orientation){
        double x = xPos;
        double y = yPos;
        switch (orientation){
            case 0: for (int i = 0; i < blockAmount; i++) {
                Block block = new Block(x,yPos);
                x = x + block.getAppearance().getWidth();
                addComponent(block);

            }break;
            case 1: for (int i = 0; i < blockAmount; i++) {
                Block block = new Block(xPos, y);
                y += block.getAppearance().getHeight();
                addComponent(block);
            }break;
        }
    }

}
