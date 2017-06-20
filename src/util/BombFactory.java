package util;

import data.Bomb;
import data.DrawingObject;

import java.awt.*;
import java.util.Random;

import static util.GameConstants.DEFAULT_BOMB_SIZE;
import static util.GameConstants.SCREEN_WIDTH;

/**
 * Created by 이예은 on 2017-06-16.
 */
public class BombFactory {
    private static BombFactory instance;
    private BombFactory(){
        random = new Random();
        probability = new int[]{15, 45, 75, 90, 100};
        velocity = new double[]{0.3, 0.2, 0.08, 0.05, 0.07};
    }

    public static BombFactory getInstance(){
        if(instance == null){
            instance = new BombFactory();
        }
        return instance;
    }

    final private Random random;
    private static int[] probability;
    private static double[] velocity;

    void setData(int[] probability, double [] velocity) {
        BombFactory.probability = probability;
        BombFactory.velocity = velocity;

    }

    public Bomb newBomb() {
        final int a = random.nextInt(100);
        Bomb b = new Bomb();
        final DrawingObject drawingObject = b.getObject();
        if (a < probability[0]) {
            b.setGrade(Bomb.Grade.A);
        } else if (a < probability[1]) {
            b.setGrade(Bomb.Grade.B);
        } else if (a < probability[2]) {
            b.setGrade(Bomb.Grade.C);
        } else if (a < probability[3]) {
            b.setGrade(Bomb.Grade.D);
        } else if (a < probability[4]) {
            b.setGrade(Bomb.Grade.F);
        }

        switch (b.getGrade()) {
            case A:
                b.setAy(velocity[0]);
                drawingObject.setImage(ImageUtil.loadImage("/images/A.png", DEFAULT_BOMB_SIZE));
                break;
            case B:
                b.setAy(velocity[1]);
                drawingObject.setImage(ImageUtil.loadImage("/images/B.png", DEFAULT_BOMB_SIZE));
                break;
            case C:
                b.setAy(velocity[2]);
                drawingObject.setImage(ImageUtil.loadImage("/images/C.png", DEFAULT_BOMB_SIZE));
                break;
            case D:
                b.setAy(velocity[3]);
                drawingObject.setImage(ImageUtil.loadImage("/images/D.png", DEFAULT_BOMB_SIZE));
                break;
            case F:
                b.setAy(velocity[4]);
                drawingObject.setImage(ImageUtil.loadImage("/images/F.png", DEFAULT_BOMB_SIZE));
                break;

        }
        final Point point = b.getObject().getPoint();
        point.setLocation(random.nextInt(SCREEN_WIDTH) - DEFAULT_BOMB_SIZE, 0);
        return b;
    }

}
