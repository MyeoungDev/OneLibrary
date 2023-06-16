package bit.edu.onelibrary.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class ConnectionManager {

    // db.properties 파일 경로를 상수로 정의합니다. final 키워드를 사용하여 재할당을 막습니다.
    private static final String PROPERTIES_PATH = "src/main/resources/db.properties";

    // 데이터베이스 연결을 관리하는 Connection 객체를 반환하는 메소드입니다.
    public static Connection getConnection() throws IOException {

        // db.properties 파일에서 데이터베이스 연결 정보를 읽어오기 위해 Properties 객체를 생성합니다.
        //Properties는 Java에서 제공하는 클래스로, key-value 쌍으로 이루어진 데이터를 저장하는 데 사용됩니다.
        Properties properties = new Properties();
        Connection connection;

        // try-with-resources 구문을 사용하여 FileReader 객체를 생성합니다.
        // FileReader 객체를 사용하여 db.properties 파일을 읽어옵니다.
        try (FileReader fileReader = new FileReader(PROPERTIES_PATH)) {
            // Properties 객체에 db.properties 파일에서 읽어온 데이터를 로드합니다.
            properties.load(fileReader);

            // Properties 객체에서 데이터베이스 연결 정보를 읽어옵니다.
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String password = properties.getProperty("password");
            String username = properties.getProperty("username");

            // JDBC 드라이버를 로드하고, 데이터베이스에 연결합니다.
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }

        // Connection 객체를 반환합니다.
        return connection;
    }
        // Connection, PreparedStatement, ResultSet 객체를 닫는 메소드입니다.
    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement,
                                       ResultSet resultSet) {

        // Connection 객체가 null이 아닌 경우, close() 메소드를 호출하여 연결을 닫습니다.
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // PreparedStatement 객체가 null이 아닌 경우, close() 메소드를 호출하여 연결을 닫습니다.
        if (Objects.nonNull(preparedStatement)) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // ResultSet 객체가 null이 아닌 경우, close() 메소드를 호출하여 연결을 닫습니다.
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
