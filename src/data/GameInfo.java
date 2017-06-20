package data;

import lombok.Data;
import util.GameConstants;

import static data.Grade.F;
import static util.GameConstants.CREDIT_PER_CRASH;
import static util.GameConstants.CREDIT_PER_SEMESTER;

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

    public GameInfo(CharacterReport characterReport) {
        this.characterName = characterReport.getName();
        score = 0;
        academicCredit = 0;
        semester = 0;
        fullCredit = 0;
        scoreList = new double[12];
    }

    public void
    onCrushBomb(Bomb bomb) {
        score += bomb.getGrade().getScore();
        scoreList[semester] += bomb.getGrade().getScore();
        fullCredit += CREDIT_PER_CRASH;

        if (bomb.getGrade() != F) {
            academicCredit += CREDIT_PER_CRASH;
        }

        if (fullCredit % CREDIT_PER_SEMESTER == 0) {
            scoreList[semester] /= (CREDIT_PER_SEMESTER / CREDIT_PER_CRASH);
            semester += 1;
        }

    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < semester; i++) {
            stringBuilder.append(i + 1).append("학기평점 : ").append(String.format("%.1f", scoreList[i])).append("\n");
        }
        return stringBuilder.toString();
    }

    public double getPoint() {
        return (double) score / (academicCredit / GameConstants.CREDIT_PER_CRASH);
    }
}
