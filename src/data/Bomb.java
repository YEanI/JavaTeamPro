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
        object = new DrawingObject();
        object.setImage("/images/ic_add_circle_outline_black.png", DEFAULT_BOMB_SIZE);
        dy = 0;
        ay = 0;

    }


    public void setImage(String s, int defaultBombSize) {
        object = new DrawingObject();
        object.setImage(s, defaultBombSize);
    }

    public enum Grade {
        A, B, C, D, F;

    }

}
