package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Game {
    Player player;
    List<Bomb> bombList;

    public Game() {
        player = new Player();
        bombList = new ArrayList<>();
    }

    public boolean isCrush() {
        for (Bomb m : bombList) {
            if (CHECk_CRUSH(player, m)) {
                return true;
            }
        }
        return false;
    }

    public static boolean CHECk_CRUSH(Player player, Bomb bomb) {
        final DrawingObject object1 = player.getObject();
        final DrawingObject object2 = bomb.getObject();
        final int bomb_x = object2.getPoint().x;
        final int bomb_y = object2.getPoint().y;
        int player_x = object1.getPoint().x;
        int player_y = object1.getPoint().y;
        int bomb_width = object2.getWidth();
        int bomb_height = object2.getHeight();
        int player_width = object1.getWidth();

        if (bomb_x < player_x && player_x < bomb_x + bomb_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        if (bomb_x < player_x + player_width && player_x + player_width < bomb_x + bomb_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        if (player_x < bomb_x && bomb_x + bomb_width < player_x + player_width && bomb_y + bomb_height > player_y) {
            return true;
        }
        return false;
    }
}
