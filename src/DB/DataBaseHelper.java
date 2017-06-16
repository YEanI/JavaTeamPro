package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyeo on 2017-06-16.
 */
public class DataBaseHelper {
    private Connection connection = null;
    public void connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnectDB(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addRecord(String userName, int score, int semester, String charName){
        try {
            String addRecordSQL = "INSERT INTO tests(id, userName, score, semester, charName)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addRecordSQL);

            preparedStatement.setString(2, userName); //user name
            preparedStatement.setInt(3, score); //score
            preparedStatement.setInt(4, semester); //semester
            preparedStatement.setString(5, charName); //charName

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public List<DBRecord> readRecord() {
        Statement statement = null;
        List<DBRecord> dbRecordList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tests ORDER BY score ASC;");

            //3. getColumnName  특정 인덱스값에 해당하는 필드 이름을 반환
            while (rs.next()) {
                String userName = rs.getString("userName");
                int score = rs.getInt("score");
                int semester = rs.getInt("semester");
                String charName = rs.getString("charName");
                DBRecord record = new DBRecord(userName, score, semester, charName);
               dbRecordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbRecordList;
    }

    public void createTable(Statement statement){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS tests (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT," +
                "score INTEGER," +
                "semester INTEGER ," +
                "charName TEXT " +
                ");";
        try {
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
