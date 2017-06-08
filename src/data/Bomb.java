package data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Bomb {

    private static final int MAX_DY = 10;
    public static final int BOMB_ACCCELATE = 2;
    Image image;
    Point point;
    int dy;
    int ay;

    public Bomb() {
        URL imageURL = getClass().getResource("../images/ic_add_circle_outline_black.png");
        image = new ImageIcon(imageURL).getImage();
        this.point = new Point(0, 0);
        dy = 0;
        ay = BOMB_ACCCELATE;
    }

    public Bomb(int x) {
        this();
        this.point.setLocation(x, this.point.getY());
    }

    public Bomb(int x, int ay) {
        this(x);
        this.ay = ay;
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getY() {
        return (int) point.getY();
    }

    public void setAY(int newAY) {
        this.ay = newAY;
    }

    public void move() {
        if(dy + ay < MAX_DY){
            this.dy += this.ay;
        }
        point.setLocation(point.getX(), point.getY() + dy);
    }

    public Image getImage() {
        return image;
    }
    public int getWidth() {
        return image.getWidth(new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    public int getHeight(){
        return image.getHeight(new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
}
