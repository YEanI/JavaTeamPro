import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */
class Game {
    Character character;
    List<Missile> missileList;

    public Game() {
        character = new Character();
        missileList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            missileList.add(new Missile());
        }
    }

    public boolean isCrush() {
        for (Missile m : missileList) {
            if (character.check(m)) {
                return true;
            }
        }
        return false;
    }
}
