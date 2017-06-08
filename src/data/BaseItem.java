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

    public boolean checkCrush(BaseItem target) {
        int targetX = (int) this.point.getX();
        int targetWidth = this.getWidth();
        int targetY = (int) this.point.getY();
        int targetHeight = this.getHeight();
        int bomb_x = target.getX();
        int bomb_width = target.getWidth();
        int bomb_y = target.getY();
        int bomb_height = target.getHeight();
        if(bomb_x < targetX && targetX < bomb_x + bomb_width && bomb_y+bomb_height > targetY){
            return true;
        }
        if(bomb_x < targetX + targetWidth && targetX + targetWidth < bomb_x + bomb_width && bomb_y+bomb_height > targetY){
            return true;
        }
        return (targetX < bomb_x && bomb_x + bomb_width < targetX + targetWidth && bomb_y+bomb_height > targetY);
    }
}
