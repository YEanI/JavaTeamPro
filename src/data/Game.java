package data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Game {
    private String userName;
    private Player player;
    private int score;
    private int crushNumber;
    private int senester;
    private int curriculargrade;

    public Game() {
        score = 0;
        crushNumber = 0;
        senester= 0;
        curriculargrade = 0;
    }

}
