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
public class Player {
    private final static int MAX_DX = 8;
    public static final int WIDTH = 600;
    private Image image;
    private final Point point;
    private int ax = 0;
    private int dx = 0;


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
}
