package ml.bjorn.bow.botsofwar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    private int x;
    private int y;

    public Coordinates() {}
    public Coordinates(Integer[] coords) {
        x = coords[0];
        y = coords[1];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{X: " + x + ", Y: " + y + "}";
    }
}