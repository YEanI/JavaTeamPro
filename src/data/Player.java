package data;

import lombok.Data;
import util.ImageUtil;

import static app.GameConstants.DEFAULT_CHARACTER_SIZE;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Player {

    private DrawingObject object;

    private String characterName;
    private double ax = 0;
    private double dx = 0;

    private double maxDx = 0;
    private double brakingForce = 0;

    public Player() {
        object = new DrawingObject();
        object.setImage(ImageUtil.loadImage("/images/character_mario.png", DEFAULT_CHARACTER_SIZE));

    }

}
