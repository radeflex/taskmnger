package taskmanager.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    private static String dbUrl = "jdbc:mysql://localhost:3306/";
    private static final String dbUserName = "root";
    private static final String dbUserPasswd = "38913891";
    private static boolean isStarted = false;

    private static void init(Statement stmt) {
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("init.sql");
        try {
            String StringSQL = new String(fileStream.readAllBytes());
            for (String line : StringSQL.split(";")) {
                if (!line.isBlank()) {
                    stmt.execute(line.trim());
                }
            }
            dbUrl += "tmdb";
            isStarted = true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbUserPasswd);
            if (!isStarted) {
                init(connection.createStatement());
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to DB!");
            e.printStackTrace();
        }
        return connection;
    }
}
