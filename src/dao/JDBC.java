package dao;

import java.sql.Connection;
import java.sql.*;

public class JDBC {
        public static Connection getJdbcConnection(){
            final String url = "jdbc:mysql://localhost:3306/studentdb?serverTimeZone=UTC";
            final String user ="root";
            final String password = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url,user,password);
                return connection;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
}
