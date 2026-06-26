import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    public Player login(String username, String password) throws SQLException {
        String sql = "SELECT id, username, wins, losses, draws, score "
                + "FROM players WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? mapPlayer(result) : null;
            }
        }
    }

    public Player findById(int playerId) throws SQLException {
        String sql = "SELECT id, username, wins, losses, draws, score FROM players WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playerId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? mapPlayer(result) : null;
            }
        }
    }

    public Player updateStatistics(Player player, String gameResult) throws SQLException {
        String normalizedResult = gameResult == null ? "" : gameResult.trim().toUpperCase();
        String sql;

        switch (normalizedResult) {
            case "WIN" -> sql = "UPDATE players SET wins = wins + 1, score = score + 10 WHERE id = ?";
            case "LOSS" -> sql = "UPDATE players SET losses = losses + 1 WHERE id = ?";
            case "DRAW" -> sql = "UPDATE players SET draws = draws + 1, score = score + 3 WHERE id = ?";
            default -> throw new IllegalArgumentException("Unknown game result: " + gameResult);
        }

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, player.getId());
            statement.executeUpdate();
        }

        return findById(player.getId());
    }

    public List<Player> getTopFiveScorers() throws SQLException {
        String sql = "SELECT id, username, wins, losses, draws, score "
                + "FROM players ORDER BY score DESC, wins DESC, username ASC LIMIT 5";
        List<Player> players = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                players.add(mapPlayer(result));
            }
        }
        return players;
    }

    private Player mapPlayer(ResultSet result) throws SQLException {
        return new Player(
                result.getInt("id"),
                result.getString("username"),
                result.getInt("wins"),
                result.getInt("losses"),
                result.getInt("draws"),
                result.getInt("score")
        );
    }
}
