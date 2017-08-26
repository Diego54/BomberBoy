package util;

import java.sql.Struct;

/**
 * Created by Pelotita on 19/8/2017.
 */
public class Vector2D {

    Double x;
    Double y;

    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    public Vector2D clone(){
        return new Vector2D(getX(),getY());
    }

    @Override
    public String toString() {
        return "Vecto2d{ " + getX() + " @ " + getY() +" }";
    }
}
