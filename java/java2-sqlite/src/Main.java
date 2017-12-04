import org.sqlite.*;

import java.sql.*;
import java.util.ArrayList;


class MyConnection{
    private Connection connection = null;

    public MyConnection(String url){
        try{
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            System.out.println("connection to SQLite has been established, try 'getConnection()' get a conncetion");
        }catch (SQLException e){
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("NO JDBC jar of sqlite");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public ArrayList<ArrayList> myExecuteQuery(String sql) throws SQLException {
        ArrayList<ArrayList> results = new ArrayList<ArrayList>();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);


        return results;
    }
}


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:A:\\gitStore\\Alogorithm_C\\Algorithm_C\\java\\earthquakes-1.sqlite";
        MyConnection myConnection = new MyConnection(url);

        Connection conn = myConnection.getConnection();
        String sql = "select * from plates;";

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            while (rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String info = rs.getString("info");
                System.out.printf("%-2s\t%-2s\t%-30s\t%-100s\n",String.valueOf(i++), code, name, info);
            }
        }catch (SQLException e){

        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection to SQLite has been exited.");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
