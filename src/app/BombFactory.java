package app;

import data.Bomb;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.GameConstants.DEFAULT_BOMB_SIZE;
import static app.GameConstants.SCREEN_WIDTH;

/**
 * Created by 이예은 on 2017-06-16.
 */
public class BombFactory {
    private static Random random = new Random();
    private static int[] probability = new int[]{15, 45, 75, 90, 100};
    private static double[] velocity = new double[]{0.3, 0.2, 0.08, 0.05, 0.07};

    static void setData(List<Integer> probability, List<Integer> velocity) {
        BombFactory.probability = new int[]{30, 50, 70, 80, 100};
//        BombFactory.probability = probability;
        BombFactory.velocity = new double[]{0.1, 0.07, 0.3, 0.04, 0.09};

    }

    public static Bomb newBomb() {

        final int a = random.nextInt(100);
        Bomb b = new Bomb();
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
                b.setImage("/images/A.png", DEFAULT_BOMB_SIZE);
                break;
            case B:
                b.setAy(velocity[1]);
                b.setImage("/images/B.png", DEFAULT_BOMB_SIZE);
                break;
            case C:
                b.setAy(velocity[2]);
                b.setImage("/images/C.png", DEFAULT_BOMB_SIZE);
                break;
            case D:
                b.setAy(velocity[3]);
                b.setImage("/images/D.png", DEFAULT_BOMB_SIZE);
                break;
            case F:
                b.setAy(velocity[4]);
                b.setImage("/images/F.png", DEFAULT_BOMB_SIZE);
                break;

        }
        b.getObject().setPoint(new Point(random.nextInt(SCREEN_WIDTH), 0));
        return b;
    }

}
