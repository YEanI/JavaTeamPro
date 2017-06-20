package data;

import lombok.Data;

/**
 * Created by 이예은 on 2017-06-08.
 */

@Data
public class GameInfo {

    private String characterName;
    private int score;
    private int academicCredit;
    private int semester;
    private int fullCredit;

    private double[] scoreList;

    public GameInfo() {
        score = 0;
        academicCredit = 0;
        semester = 0;
        fullCredit = 0;
        scoreList = new double[12];

    }

}
