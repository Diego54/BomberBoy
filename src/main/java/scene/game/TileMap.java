package scene.game;

/**
 * Created by Pelotita on 29/7/2017.
 */
public class TileMap {
    private RichGameComponent[][] map;
    public TileMap(int with, int height){
        map = new RichGameComponent[with][height];
    }

    public RichGameComponent getTile(int x, int y){
        return map[x][y];
    }

    public void addTile(RichGameComponent rgc){
        int[] pos = rgc.getTile();
        map[pos[0]][pos[1]]= rgc;
    }

}
