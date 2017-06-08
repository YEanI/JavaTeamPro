package data;

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

    }

    public void levelUp(){
        level++;
        time = MAX_TIME;
        //TODO remove all bombs
    }

    public boolean isCrush() {
        for (Bomb m : bombList) {
            if (m.checkCrush(player)) {
                return true;
            }
        }
        return false;
    }
}
