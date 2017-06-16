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
    private Grade grade;

    public Bomb() {
        object = new DrawingObject();
        object.setImage("/images/ic_add_circle_outline_black.png", DEFAULT_BOMB_SIZE);
        dy = 0;
        ay = 0;
        grade = Grade.A;
    }

    public void move() {
        dy += ay;
        Point point = object.getPoint();
        point.setLocation(point.getX(), point.getY() + dy);
    }

    public enum Grade{
        A, B, C, D, F
    }
}
