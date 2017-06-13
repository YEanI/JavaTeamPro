package data;

import lombok.Data;

import java.awt.*;

import static app.GameConstants.DEFAULT_BOMB_SIZE;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Bomb {
    private DrawingObject object;
    private double dy, ay;

    public Bomb() {
        object = new DrawingObject();
        object.setImage("/images/ic_add_circle_outline_black.png", DEFAULT_BOMB_SIZE);
        dy = 0;
        ay = 0;

    }

    public void move() {
        dy += ay;
        Point point = object.getPoint();
        point.setLocation(point.getX(), point.getY() + dy);
    }


}
