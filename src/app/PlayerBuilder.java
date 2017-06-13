package app;

import data.DrawingObject;
import data.Player;

import java.awt.*;

import static app.GameConstants.SCREEN_WIDTH;

/**
 * Created by minchul on 2017-06-13.
 */
public class PlayerBuilder {

    private double maxDx;
    private double brakingForce;
    private double ax;
    private String filePath;
    private int size;

    public PlayerBuilder setMax_dx(double maxDx) {
        this.maxDx = maxDx;
        return this;
    }

    public PlayerBuilder setBraking_force(double brakingForce) {
        this.brakingForce = brakingForce;
        return this;
    }

    public PlayerBuilder setAx(double ax) {
        this.ax = ax;
        return this;
    }

    public PlayerBuilder setImage(String filePath, int size) {
        this.filePath = filePath;
        this.size = size;
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setMaxDx(maxDx);
        player.setAx(ax);
        player.setBrakingForce(brakingForce);
        player.getObject().setImage(filePath, size);
        final DrawingObject object = player.getObject();
        final int height = object.getHeight();
        object.setPoint(new Point(SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT - height));
        return player;
    }
}
