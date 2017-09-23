package client;

import com.uqbar.vainilla.GameScene;
import game.battle.scene.Battlefield;
import game.battle.scene.Player;
import io.socket.client.IO;
import io.socket.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

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
//                .on("getStartedInfo", this::getStartedInfo)
//                .on("registerNewPlayer", this::registerNewPlayer)
//                .on("playerDisconnected", this::playerDisconnected)
                .on("playerMoved", this::playerMoved);
    }

    @Override
    public void update(JSONObject jsonObject) {
        socket.emit("playerMoved",jsonObject);
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
}
