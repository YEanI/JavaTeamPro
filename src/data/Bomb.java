package data;

import javax.swing.*;
import java.net.URL;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Bomb extends BaseItem {

    private static final int MAX_DY = 10;
    public static final int BOMB_ACCCELATE = 2;
    int dy;
    int ay;

    public Bomb(int x, int ay) {
        super();
        URL imageURL = getClass().getResource("../images/ic_add_circle_outline_black.png");
        image = new ImageIcon(imageURL).getImage();
        dy = 0;
        ay = BOMB_ACCCELATE;
        this.point.setLocation(x, this.point.getY());
        this.ay = ay;
    }

    @Override
    public void move() {
        if(dy + ay < MAX_DY){
            this.dy += this.ay;
        }
        point.setLocation(point.getX(), point.getY() + dy);
    }

}