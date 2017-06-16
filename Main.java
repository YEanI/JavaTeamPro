package fileprac;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by 조은지 on 2017-06-09.
 */
public class Main {
    public static Connection makeConnection(){
        String url = "jdbc:mysql://127.0.0.1/book_db";
        String id = "root";
        String password ="rhfms2clrhk";
        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con= DriverManager.getConnection(url,id,password);
            System.out.println("데이터베이스 연결성공");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private static void addBook(String title, String publisher, String year,int price ){
        Connection con = makeConnection ();

        try{
            Statement stmt = con.createStatement();
            String s = "INSERT INTO books (title, publisher, year, price) VALUES";
            s +="('" + title + "','" + publisher + "','"+ year + "','" + price +"')";
            System.out.println(s);
            int i = stmt.executeUpdate(s);
            if(i==1)
                System.out.println("레코드 추가 성공");
            else
                System.out.println("레코드 추가 실패");
        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public static void main(String arg[]) throws  SQLException{
        Connection con = makeConnection();
        Statement stmt = con.createStatement();

        ResultSet rs=

                stmt.executeQuery("SELECT * FROM books");

        while(rs.next()){
            int number = rs.getInt("book_id");
            String name = rs.getString("title");
            System.out.println("book_id: " + number + " title: " + name);
        }

        addBook("break java","cutie hyezie", "1998", 87000);

    }
}
