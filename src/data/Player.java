package data;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Player extends BaseItem {
    private final static int MAX_DX = 8;
    public static final int WIDTH = 600;
    private int ax = 0;
    private int dx = 0;


    public Player(int windowHeight) {
        URL playerUrl = getClass().getResource("../images/ic_android_black.png");
        image = new ImageIcon(playerUrl).getImage();
        this.point = new Point(0, 0);
        int imageHeight = image.getHeight(null);
        this.point.setLocation(0, windowHeight - imageHeight);
    }


    @Override
    public void move() {
        if(point.getX() < 0){
            dx = 0;
            ax = 0;
            point.setLocation(0, point.getY());
            return;
        }
        if(point.getX() > WIDTH - getWidth()){
            dx = 0;
            ax = 0;
            point.setLocation(WIDTH - getWidth(), point.getY());
            return;
        }
        if(dx + ax < MAX_DX && dx + ax > MAX_DX * -1){
            this.dx += this.ax;
        }

        point.setLocation(point.getX() + dx, point.getY());

    }

}
