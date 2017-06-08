package data;

import java.awt.*;

/**
 * Created by minchul on 2017-06-08.
 */
public abstract class BaseItem {
    Image image;
    Point point;

    public BaseItem() {
        this.point = new Point(0, 0);
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getY() {
        return (int) point.getY();
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight(){
        return image.getHeight(null);
    }

    abstract void move();
}
