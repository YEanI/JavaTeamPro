import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */
class Game extends JFrame{
    Character character;
    List<Missile> missileList;
    private JPanel panel;

    public Game() {

        panel = new JPanel();
        this.add(panel);

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
