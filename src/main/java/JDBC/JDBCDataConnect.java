package JDBC;

import java.sql.*;

public class JDBCDataConnect {

    private static Connection conn = null;

    public JDBCDataConnect(){
        try {
            getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() throws SQLException {
        if (conn == null){
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_hm","root","PASSWORDGOESHERE!");

        }

        return conn;
    }
    ;
    public static void main(String[] args) {
        try {

            Connection db = JDBCDataConnect.getInstance();
            Statement s = db.createStatement();

            ResultSet ans = s.executeQuery("select * from employee");

            while (ans.next()){
                System.out.println(ans.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }



}
