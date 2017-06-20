package data;

import lombok.Data;
import util.ImageUtil;

import java.awt.*;
import java.util.Map;
import java.util.Random;

import static data.Grade.*;
import static data.Grade.F;
import static util.GameConstants.DEFAULT_BOMB_SIZE;
import static util.GameConstants.SCREEN_WIDTH;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Bomb {
    private DrawingObject object;
    private double dy, ay;
    private Grade grade;

    public Bomb(CharacterReport characterReport) {
        final Random random = new Random();
        final int prob = random.nextInt(100);

        Grade grade;
        double ay;

        final Map<String, Integer> probability = characterReport.getPercent();
        final Map<String, Double> speed = characterReport.getGradeSpeed();

        if(prob < probability.get("A")){
            grade = A;
        }else if(prob < probability.get("A") + probability.get("B")){
            grade = B;
        }else if(prob < probability.get("A") + probability.get("B") + probability.get("C")){
            grade = C;
        }else if(prob < probability.get("A") + probability.get("B") + probability.get("C") + probability.get("D")){
            grade = D;
        }else{
            grade = F;
        }
        ay = speed.get(grade.name());

        this.grade = grade;
        this.ay = ay;
        this.dy = 0;
        this.object = new DrawingObject();

        object.setImage(ImageUtil.loadImage(grade.getImagePath(), DEFAULT_BOMB_SIZE));
        object.getPoint().setLocation(random.nextInt(SCREEN_WIDTH - DEFAULT_BOMB_SIZE) , 0);

    }

    public void move() {
        final Point point = object.getPoint();
        dy += ay;
        point.setLocation(point.getX(), point.getY() + dy);
    }
}
