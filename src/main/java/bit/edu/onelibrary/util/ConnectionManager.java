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

    private static final String PROPERTIES_PATH = "/Users/parkjungyeong/OneLibrary/src/main/resources/db.properties";

    public static Connection getConnection() throws IOException {

        Properties properties = new Properties();
        Connection connection;

        try (FileReader fileReader = new FileReader(PROPERTIES_PATH)) {
            properties.load(fileReader);

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String password = properties.getProperty("password");
            String username = properties.getProperty("username");

            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return connection;
    }

    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement,
                                       ResultSet resultSet) {

        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.nonNull(preparedStatement)) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
