import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */
public class Main {
    static class Game {
        Character character;
        List<Missile> missileList;
        public Game(){
            character = new Character();
            missileList = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                missileList.add(new Missile());
            }
        }

        public boolean isCrush() {
            for (Missile m : missileList) {
                if (character.check(m)){
                    return true;
                }
            }
            return false;
        }
    }

    static class Missile {

    }

    static class Character{
        public boolean check(Missile missile){
            System.out.println("hi!");
            return true;
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        boolean isCrush = false;
        for (Missile m : game.missileList) {
            if (game.character.check(m)){
                isCrush = true;
                break;
            }
        }

        if(isCrush){
            //충돌 확인
        }

        game.isCrush();
    }
}
