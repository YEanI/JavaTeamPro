package data;

import lombok.Data;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Player {
    private DrawingObject object;

    private double ax = 0;
    private double dx = 0;

    private double max_dx = 0;
    private double braking_force = 0;

    public Player() {
        object = new DrawingObject();
        object.setImage("/images/character_mario.png", 96);
    }

}
