package data;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static data.Player.Direction.LEFT;
import static data.Player.Status.ACCEL;
import static data.Player.Status.IDLE;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Player {
    private DrawingObject object;
    private Status status = IDLE;
    private Direction direction = LEFT;
    private int ax = 0;
    private int dx = 0;

    private int max_dx = 0;
    private int braking_force = 0;

    public Player() {
        object = new DrawingObject();
        object.setImage("/images/ic_android_black.png");
    }

    public void move(final int SCREEN_WIDTH) {
        if(status == ACCEL){
            if(direction == LEFT){
                dx -= ax;
            }else{
                dx += ax;
            }
            if(dx > max_dx){
                dx = max_dx;
            }else if (dx < -max_dx){
                dx = -max_dx;
            }
        }else{
            if(direction == LEFT){
                dx += braking_force;
                if(dx > 0){
                    dx = 0;
                }
            }else{
                dx -= braking_force;
                if(dx < 0){
                    dx = 0;
                }
            }
        }
        Point point = object.getPoint();
        int newX = point.x + dx;
        if(newX < 0){
            newX = 0;
            dx = 0;
        }else if(newX + object.getWidth() > SCREEN_WIDTH){
            newX = SCREEN_WIDTH - object.getWidth();
            dx = 0;
        }
        point.x = newX;
    }

    public enum Status {
        ACCEL, IDLE
    }

    public enum Direction {
        LEFT, RIGHT
    }
}
