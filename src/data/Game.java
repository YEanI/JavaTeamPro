package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */
class Game {
    final static private int MAX_TIME = 60; //second
    int level, time;
    Player player;
    List<Bomb> bombList;

    public Game() {
        level = 1;
        time = MAX_TIME;
//        player = new Player();
        bombList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bombList.add(new Bomb());
        }
    }

    public void levelUp(){
        level++;
        time = MAX_TIME;
        //TODO remove all bombs
    }

    public boolean isCrush() {
        for (Bomb m : bombList) {
            if (player.checkCrush(m)) {
                return true;
            }
        }
        return false;
    }
}
