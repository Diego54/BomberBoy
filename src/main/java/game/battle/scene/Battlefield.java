package game.battle.scene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;
import util.Vector2D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 27/07/17.
 */
public class Battlefield extends GameScene {
//contiene la logica de construccion del campo de batalla
    //TODO BattlefieldBuilder builder = new BattlefieldBuilder();
    private List<Player> players;
    private TileMap grid;

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

    public List<Player> getPlayers() {
        return players;
    }

    public Battlefield(){
        players = new ArrayList<Player>();
        grid = new TileMap(15,13);
        buildBattlefield();
        fillGrid();

        addComponent(new ResetButton());
        Key[] player1Controls = {Key.LEFT,Key.UP,Key.RIGHT,Key.DOWN,Key.A};
        Player playerOne = new Player(player1Controls, new Color(0,200,66),new Vector2D(1,1),"Mario");
        addComponent(playerOne);
        players.add(playerOne);

        Key[] player2Controls = {Key.LEFT,Key.UP,Key.RIGHT,Key.DOWN,Key.A};
        Player playerTwo = new Player(player2Controls, new Color(130, 47,24),new Vector2D(13,11),"JorgeNitales");
        addComponent(playerTwo);
        players.add(playerTwo);
    }

    public void buildBattlefield(){
        buildFieldLimits();
        fillDestructibleBlocks(1, 1, 12, 10);
        buidBlockGrid();
        emptyBlocks();
        //placePlayers();
    }

    private void buildFieldLimits() {
        buildLine(0,0,15,true,Solid.class,1.0);
        buildLine(0,552,15,true,Solid.class,1.0);
        buildLine(0,46,11,false,Solid.class,1.0);
        buildLine(742,46,11,false,Solid.class,1.0);
    }

    private void buidBlockGrid(){
        for (int i = 1; i <= 5; i++){
            buildLine(106, i*92,7,true,Solid.class,2.0);
        }
    }

    private void buildLine(double xPos, double yPos, int blockAmount, Boolean horizontal,Class<? extends Block> c,double distance){//TODO hacer un builder piola.
        double x = xPos;
        double y = yPos;
        Block block = null;
        for (int i = 0; i < blockAmount; i++) {
            try {
                if (horizontal) {
                    block = c.getDeclaredConstructor(double.class, double.class).newInstance(x, yPos);
                    x += block.getAppearance().getWidth()*distance;
                } else {
                    block = c.getDeclaredConstructor(double.class, double.class).newInstance(xPos, y);
                    y += block.getAppearance().getHeight()*distance;
                }
                addComponent(block);
            }catch (Exception e){
                //log exception
            }
        }
    }

    public void fillDestructibleBlocks(int startX, int startY, int endX, int endY){
        //llena un cuadrado debloques rompibles
        for (int i=startX; i < (endX+2); i=i+1){
            for (int j = startY; j < (endY+2); j=j+1){
                double chance = Math.random()*100;
                if(chance<90){
                    Destructible block = new Destructible(i,j);
                    getGrid().addTile(block);
                    addComponent(block);
                }
            }
        }
    }

    public void emptyBlocks(){
        RichGameComponent[] toRemove = {grid.getTile(1d,1d),
        grid.getTile(2d,1d),
        grid.getTile(1d,2d),

        grid.getTile(12d,1d),
        grid.getTile(13d,1d),
        grid.getTile(13d,2d),

        grid.getTile(1d,10d),
        grid.getTile(1d,11d),
        grid.getTile(2d,11d),

        grid.getTile(12d,11d),
        grid.getTile(13d,10d),
        grid.getTile(13d,11d)};
        removeComponents(toRemove);
        for (RichGameComponent i: toRemove) {
            grid.removeTile(i);
        }
    }

}
