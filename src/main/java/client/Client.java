package client;

import game.battle.scene.Battlefield;
import game.battle.scene.Destructible;
import game.battle.scene.Player;
import io.socket.client.IO;
import io.socket.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Client implements Observer {

    private Socket socket;
    private Battlefield scene;

    public Client(String serverUrl, Battlefield scene) {
        try {
            this.scene = scene;
            this.socket = IO.socket(serverUrl);
            this.configSocketEvents();
            this.socket.connect();
        } catch (Exception e) {
            throw new RuntimeException("Connection Error!");
        }
    }

    private void configSocketEvents() {
        socket
                .on("getBlocksResponse", this::fillDestructiblesFromServer)
                .on("playersAmountResult", this::fillDestructibles)
                .on("playerDropedBomb", this::playerDropedBomb)
                .on("playerMoved", this::playerMoved);
    }

    @Override
    public void update(JSONObject jsonObject) {
        socket.emit("playerMoved",jsonObject);
    }

    @Override
    public void updateDropedBomb(JSONObject jsonObject){
        socket.emit("playerDropedBomb",jsonObject);
    }

    public void playerMoved(Object[] args){
        JSONObject data = (JSONObject) args[0];
        try {
            int playerId = data.getInt("id");
            Double x = data.getDouble("x");
            Double y = data.getDouble("y");
            Player p = scene.getPlayers().get(playerId);
            p.setX(x);
            p.setY(y);
        } catch (JSONException e) {
            throw new RuntimeException("SocketIO - Move Character Error");
        }
    }
    public void playerDropedBomb(Object[] args){
        JSONObject data = (JSONObject) args[0];
        try {
            int playerId = data.getInt("id");
            Player p = scene.getPlayers().get(playerId);
            p.dropBomb();
        } catch (JSONException e) {
            throw new RuntimeException("SocketIO - Move Character Error");
        }
    }
    public void mustFillDestructibles(){
        socket.emit("playersAmount",new JSONObject());
    }

    public void fillDestructibles(Object[] args){
        JSONObject data = (JSONObject) args[0];
        try {
            int playeramount = data.getInt("players");
            if(playeramount<=1){
                List<JSONObject> blocks = fillDestructibleBlocks(1, 1, 12, 10);
                scene.emptyBlocks();
                socket.emit("createdBlocks",new JSONArray(blocks));
            }else{
                socket.emit("getBlocks",new JSONObject());
            }
        } catch (JSONException e) {
            throw new RuntimeException("SocketIO - Move Character Error");
        }
    }

    public void fillDestructiblesFromServer(Object[] args){
        JSONObject data = (JSONObject) args[0];
        try {
            JSONArray blocks = data.getJSONArray("blocks");
            for (int i = 0; i < blocks.length(); i++) {
                JSONObject object = blocks.getJSONObject(i);
                int x = object.getInt("x");
                int y = object.getInt("y");
                if(scene.getGrid().getTile(new Vector2D(x,y))==null ) {
                    Destructible block = new Destructible(x,y);
                    scene.getGrid().addTile(block);
                    scene.addComponent(block);
                }
            }
            scene.emptyBlocks();
        } catch (JSONException e) {
            throw new RuntimeException("SocketIO - Move Character Error");
        }
    }

    public List<JSONObject> fillDestructibleBlocks(int startX, int startY, int endX, int endY){
        List<JSONObject> blocks = new ArrayList<JSONObject>();
        //llena un cuadrado debloques rompibles
        for (int i=startX; i < (endX+2); i=i+1){
            for (int j = startY; j < (endY+2); j=j+1){
                double chance = Math.random()*100;
                if(chance<90){
                    if(scene.getGrid().getTile(new Vector2D(i,j))==null ) {
                        Destructible block = new Destructible(i, j);
                        scene.getGrid().addTile(block);
                        scene.addComponent(block);
                        blocks.add(block.toJson());
                    }
                }
            }
        }
        return blocks;
    }
}
