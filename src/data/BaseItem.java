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

    public int getHeight() {
        return image.getHeight(null);
    }

    abstract void move();

    public boolean checkCrush(BaseItem target) {
        int player_x = (int) this.point.getX();
        int player_width = this.getWidth();
        int player_y = (int) this.point.getY();
        int player_height = this.getHeight();
        int bomb_x = target.getX();
        int bomb_width = target.getWidth();
        int bomb_y = target.getY();
        int bomb_height = target.getHeight();
        if (bomb_x < player_x && player_x < bomb_x + bomb_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        if (bomb_x < player_x + player_width && player_x + player_width < bomb_x + bomb_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        if (player_x < bomb_x && bomb_x + bomb_width < player_x + player_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        return false;
    }
}
