package data;

import lombok.Data;
import util.ImageUtil;

import java.awt.*;

import static util.GameConstants.*;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Player {

    private DrawingObject object;

    private double ax = 0;
    private double dx = 0;

    private double maxDx = 0;
    private double brakingForce = 0;

    public Player() {
        object = new DrawingObject();
        object.setImage(ImageUtil.loadImage("/images/character_mario.png", DEFAULT_CHARACTER_SIZE));

    }

    public Player(CharacterReport characterReport) {
        this.brakingForce = characterReport.getBrakingForce();
        this.maxDx = characterReport.getMaxDx();
        this.ax = characterReport.getAx();
        this.object = new DrawingObject();
        this.object.setImage(ImageUtil.loadImage(characterReport.getPath(), DEFAULT_CHARACTER_SIZE));
        this.object.getPoint().setLocation(SCREEN_WIDTH / 2, SCREEN_HEIGHT - DEFAULT_CHARACTER_SIZE);
    }

    public void moveLeft() {
        final double newDx = dx - ax;
        dx = newDx > maxDx ? newDx : -maxDx;
        move();
    }

    public void moveRight() {
        final double newDx = dx + ax;
        dx = newDx < maxDx ? newDx : maxDx;
        move();
    }

    public void brake() {
        final double absDx = Math.abs(dx) - brakingForce;
        if (absDx > 0) {
            dx = dx > 0 ? absDx : -absDx;
        } else {
            dx = 0;
        }
        move();
    }

    private void move() {
        final Point point = object.getPoint();
        double newX = point.getX() + dx;
        if (newX < 0) {
            newX = 0;
            dx = 0;
        } else if (newX + object.getWidth() > SCREEN_WIDTH) {
            newX = SCREEN_WIDTH - object.getWidth();
            dx = 0;
        }
        point.setLocation(newX, point.getY());
    }
}
