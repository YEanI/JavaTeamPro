package data;

import lombok.Data;

import java.awt.*;

import static app.GameConstants.DEFAULT_BOMB_SIZE;
import static data.Bomb.Grade.*;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Bomb {
    private DrawingObject object;
    private double dy, ay;
    private Grade grade;

    public Bomb() {
        dy = 0;
        ay = 0;
    }

    public void move() {
        dy += ay;
        Point point = object.getPoint();
        point.setLocation(point.getX(), point.getY() + dy);
    }

    public void setImage(String s, int defaultBombSize) {
        object = new DrawingObject();
        object.setImage(s, defaultBombSize);
    }

    public enum Grade {
        A, B, C, D, F;

    }

}
