package client;

import org.json.JSONObject;

public interface Observer {

    void update(JSONObject jsonObject);

}