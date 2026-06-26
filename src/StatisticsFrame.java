import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

public class StatisticsFrame extends JFrame {
    private final PlayerService playerService = new PlayerService();
    private Player currentPlayer;

    public StatisticsFrame(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        setTitle("FunPro Arena - My Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(560, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        refreshPlayer();
        setContentPane(buildContent());
    }

    private void refreshPlayer() {
        try {
            Player refreshedPlayer = playerService.findById(currentPlayer.getId());
            if (refreshedPlayer != null) {
                currentPlayer = refreshedPlayer;
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this,
                    "Could not refresh statistics. Showing the last known values.\n" + exception.getMessage(),
                    "Database Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JPanel buildContent() {
        JPanel page = UiStyle.page();
        page.add(UiStyle.header("My Statistics", "Current record for " + currentPlayer.getUsername()),
                BorderLayout.NORTH);

        JPanel card = UiStyle.card();
        card.setLayout(new GridLayout(1, 4, 12, 0));
        card.add(statCard("Wins", currentPlayer.getWins(), UiStyle.SUCCESS));
        card.add(statCard("Losses", currentPlayer.getLosses(), UiStyle.DANGER));
        card.add(statCard("Draws", currentPlayer.getDraws(), UiStyle.MUTED));
        card.add(statCard("Score", currentPlayer.getScore(), UiStyle.PRIMARY_DARK));
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private JPanel statCard(String name, int value, java.awt.Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel number = UiStyle.centeredLabel(String.valueOf(value), 32, color);
        JLabel label = UiStyle.centeredLabel(name, 13, UiStyle.MUTED);
        label.setBorder(new EmptyBorder(6, 0, 0, 0));
        panel.add(number, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        return panel;
    }
}
