package app;

import data.Bomb;

import java.awt.*;

/**
 * Created by minchul on 2017-06-13.
 */
public class BombBuilder {
    private Point point;
    private double ay;

    public BombBuilder setPoint(Point point) {
        this.point = point;
        return this;
    }

    public BombBuilder setAy(double ay) {
        this.ay = ay;
        return this;
    }

    public Bomb build() {
        Bomb bomb = new Bomb();
        bomb.setAy(ay);
        bomb.getObject().setPoint(point);
        return bomb;
    }
}
