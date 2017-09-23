package client;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pelotita on 21/9/2017.
 */
public interface Observable {
    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer){
        this.observers.add(observer);
    }

    default void deleteObserver(Observer observer){
        this.observers.remove(observer);
    }

    default void notify(JSONObject jsonObject){
        this.observers.forEach(observer -> observer.update(jsonObject));
    }

}
