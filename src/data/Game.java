package data;

import lombok.Data;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class Game {
    private String characterName;
    private int score;
    private int academicCredit;
    private int semester;
    private int currCalGrade;

    private double[] scoreList;

    public Game() {
        score = 0;
        academicCredit = 0;
        semester = 0;
        currCalGrade = 0;
        scoreList = new double[12];

    }

}
