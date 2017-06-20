package data;

/**
 * Created by minchul on 2017-06-21.
 */
public enum Grade {
    A(4, "/images/A.png"), B(3, "/images/B.png"), C(2, "/images/C.png"), D(1, "/images/D.png"), F(0, "/images/F.png");

    private int score;
    private String imagePath;

    Grade(int score, String imagePath) {
        this.score = score;
        this.imagePath = imagePath;
    }

    public int getScore() {
        return score;
    }

    public String getImagePath() {
        return imagePath;
    }
}
