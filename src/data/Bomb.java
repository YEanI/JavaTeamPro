package data;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Bomb {

    private DrawingObject object;
    private int dy, ay;

    public Bomb(){
        object = new DrawingObject();
        object.setImage("/images/ic_add_circle_outline_black.png");
        dy = 0;
        ay = 0;

    }

    public void move() {
        dy += ay;
        Point point = object.getPoint();
        object.setPoint(new Point(point.x, point.y + dy));
    }

}
