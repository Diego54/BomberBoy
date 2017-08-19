package scene.game;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;

/**
 * Created by sergio on 27/07/17.
 */
public class Battlefield extends GameScene {
//contiene la logica de construccion del campo de batalla
    //BattlefieldBuilder builder = new BattlefieldBuilder();

    TileMap grid;

    public TileMap getGrid() {
        return grid;
    }

    public void setGrid(TileMap grid) {
        this.grid = grid;
    }

    private void fillGrid(){
        for (GameComponent gc : this.getComponents()) {
            grid.addTile((RichGameComponent) gc);
        }
    }

    public Battlefield(){
        grid = new TileMap(15,13);
        buildBattlefield();
        fillGrid();
        addComponent(new Player());
    }

    public void buildBattlefield(){
        buildFieldLimits();
        buidBlockGrid();
        //fillDestructibleBlock();
        //placePlayers();
    }

    private void buildFieldLimits() {
        buildLine(0,0,15,true);
        buildLine(0,552,15,true);
        buildLine(0,46,11,false);
        buildLine(742,46,11,false);
    }

    private void buidBlockGrid(){
        for (int i = 1; i <= 5; i++){
            buildDottedLine(106, i*92,7,true);
        }
    }

    private void buildLine(double xPos, double yPos, int blockAmount, Boolean horizontal){//TODO hacer un builder piola con un template method
        double x = xPos;
        double y = yPos;
        Block block = null;
        for (int i = 0; i < blockAmount; i++) {
            if (horizontal) {
                block = new Block(x, yPos);
                x += block.getAppearance().getWidth();
            }else{
                block = new Block(xPos, y);
                y += block.getAppearance().getHeight();
            }
            addComponent(block);
        }
    }

    private void buildDottedLine(double xPos, double yPos, int blockAmount, Boolean horizontal){//TODO hacer un builder piola con un template method
        double x = xPos;
        double y = yPos;
        Block block = null;
        for (int i = 0; i < blockAmount; i++) {
            if (horizontal) {
                block = new Block(x, yPos);
                x += block.getAppearance().getWidth()*2;
            }else{
                block = new Block(xPos, y);
                y += block.getAppearance().getHeight()*2;
            }
            addComponent(block);
        }
    }

}
