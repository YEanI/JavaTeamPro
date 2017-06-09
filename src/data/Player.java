package data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static data.Player.Status.IDLE;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Player extends BaseItem {
    private final static int MAX_DX = 8;
    public static final int WIDTH = 600;
    private int ax = 0;
    private int dx = 0;
    private  Status status = IDLE;
    private Direction direction = Direction.LEFT;



    public Player(int windowHeight) throws IOException {
        URL playerUrl = BaseItem.class.getResource("/images/ic_android_black.png");
        image = ImageIO.read(playerUrl);
        this.point = new Point(0, 0);
        int imageHeight = image.getHeight(null);
        this.point.setLocation(0, windowHeight - imageHeight);
    }


    @Override
    public void move() {

        switch (status){
            case ACCEL:
                if(direction == Direction.LEFT){
                    dx = dx -1;
                }
                else{
                    dx = dx + 1;
                }
                break;
            case IDLE:
                if(direction == Direction.LEFT){
                    if(dx < 0){
                        dx = dx +1;
                    }
                }
                else{
                    if(dx > MAX_DX){
                        dx = dx-1;
                    }
                }
        }

        int newX;
        newX = (int) (point.getX() + dx);
        if(newX < 0){
            newX = 0;
        }else if(newX > WIDTH - getWidth() ){
            newX = WIDTH - getWidth();
        }
        point.setLocation(newX, point.getY());

    }

    public void setStatus(Status s, Direction d) {
        this.status = s;
        this.direction = d;
    }

    public enum Status{
        ACCEL, IDLE
    }
    public enum Direction{
        LEFT, RIGHT
    }
}
