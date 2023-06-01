package bit.edu.onelibrary.notice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
    public static void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
        if (rs != null) {
            try {
                rs.close();//
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstmt =null;
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con = null;
    }
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        //Propertie class 활용하는 방법
        //환경정보는 값이 바뀌기 때문에 밖에서 설정되어야 한다.
        //Stream이용하여 값 설정
        Properties p = new Properties();
        String jdbcURL ="";
        String driver ="";
        String id ="";
        String password ="";

        try{
            p.load(new FileReader("data/db.properties"));
            jdbcURL=p.getProperty("jdbcURL");
            driver=p.getProperty("driver");
            id=p.getProperty("id");
            password=p.getProperty("password");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            Class.forName(driver);
            con = DriverManager.getConnection(jdbcURL,id,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
