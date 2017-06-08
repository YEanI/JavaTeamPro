package data;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Player {
    private final static int MAX_DX = 8;
    public static final int WIDTH = 500;
    private Image image;
    private final Point point;
    private int ax = 0;
    private int dx = 0;
    private Status status = Status.IDLE;
    private Direction direction = Direction.LEFT;


    public Player(){
        URL playerUrl = getClass().getResource("../images/ic_android_black.png");
        image = new ImageIcon(playerUrl).getImage();
        this.point = new Point(0, 0);
    }

    public Player(int windowHeight) {
        this();
        int imageHeight = image.getHeight(new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        this.point.setLocation(0, windowHeight - imageHeight);
    }


    public boolean checkCrush(Bomb bomb) {
        int player_x = (int) this.point.getX();
        int player_width = this.getWidth();
        int player_y = (int) this.point.getY();
        int player_height = this.getHeight();
        int bomb_x = bomb.getX();
        int bomb_width = bomb.getWidth();
        int bomb_y = bomb.getY();
        int bomb_height = bomb.getHeight();
        if(bomb_x < player_x && player_x < bomb_x + bomb_width && bomb_y+bomb_height > player_y){
            return true;
        }
        if(bomb_x < player_x + player_width && player_x + player_width < bomb_x + bomb_width && bomb_y+bomb_height > player_y){
            return true;
        }
        if(player_x < bomb_x && bomb_x + bomb_width < player_x + player_width && bomb_y+bomb_height > player_y){
            return true;
        }
        return false;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getY() {
        return (int) point.getY();
    }

    public void setAX(int newAX) {
        this.ax = newAX;
    }

    public void move() {
        switch (status){
            case ACCEL:
                if(direction == Direction.LEFT){
                    dx = dx - 1;
                }
                else{
                    dx = dx + 1;
                }
                break;
            case IDLE:
                if(direction == Direction.LEFT){
                    if(dx < 0) {
                        dx = dx + 1;
                    }
                }else{

                }
                break;
        }
//        if(point.getX() < 0){
//            dx = 0;
//            ax = 0;
//            point.setLocation(0, point.getY());
//            return;
//        }
//        if(point.getX() > WIDTH - getWidth()){
//            dx = 0;
//            ax = 0;
//            point.setLocation(WIDTH - getWidth(), point.getY());
//            return;
//        }
//        if(dx + ax < MAX_DX && dx + ax > MAX_DX * -1){
//            this.dx += this.ax;
//        }

        point.setLocation(point.getX() + dx, point.getY());

    }

    private int getWidth() {
        return image.getWidth(new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    private int getHeight(){
        return image.getHeight(new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    public void stop() {
        ax =0;
        dx =0;
    }

    public void setStatus(Status status, Direction direction) {
        this.status = status;
        this.direction = direction;
    }

    public enum Status{
        ACCEL, IDLE
    }
    public enum Direction{
        LEFT, RIGHT
    }
}
