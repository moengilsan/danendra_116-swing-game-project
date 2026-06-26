import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/game_project?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final Properties CONFIG = loadConfiguration();

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        String url = setting("DB_URL", "db.url", DEFAULT_URL);
        String user = setting("DB_USER", "db.user", "root");
        String password = setting("DB_PASSWORD", "db.password", "");
        return DriverManager.getConnection(url, user, password);
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(2);
        } catch (SQLException exception) {
            return false;
        }
    }

    public static String getConnectionDescription() {
        return setting("DB_URL", "db.url", DEFAULT_URL);
    }

    private static String setting(String environmentName, String propertyName, String defaultValue) {
        String environmentValue = System.getenv(environmentName);
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }
        return CONFIG.getProperty(propertyName, defaultValue).trim();
    }

    private static Properties loadConfiguration() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config/database.properties")) {
            properties.load(input);
        } catch (IOException ignored) {
        }
        return properties;
    }
}
