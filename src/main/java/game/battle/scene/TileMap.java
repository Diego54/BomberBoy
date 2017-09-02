package game.battle.scene;

import util.Vector2D;

/**
 * Created by Pelotita on 29/7/2017.
 */
public class TileMap {
    private RichGameComponent[][] map;
    public TileMap(int with, int height){
        map = new RichGameComponent[with][height];
    }

    public RichGameComponent getTile(Vector2D v){
        return map[v.getX().intValue()][v.getY().intValue()];
    }

    public RichGameComponent getTile(Double x, Double y){
        return map[x.intValue()][y.intValue()];
    }

    public void addTile(RichGameComponent rgc){
        Vector2D pos = rgc.getTile();
        map[pos.getX().intValue()][pos.getY().intValue()]= rgc;
    }
    public void removeTile(RichGameComponent rgc){
        if(rgc != null){
            Vector2D pos = rgc.getTile();
            map[pos.getX().intValue()][pos.getY().intValue()]=null;
        }
    }

}
