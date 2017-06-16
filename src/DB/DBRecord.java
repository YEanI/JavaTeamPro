package DB;

/**
 * Created by suyeo on 2017-06-16.
 */
public class DBRecord {
    public String userName;
    public int score;
    public int semester;
    public String charName;

    public DBRecord(String userName, int score, int semester, String charName){
        this.userName = userName;
        this.score = score;
        this.semester = semester;
        this.charName = charName;
    }
}
