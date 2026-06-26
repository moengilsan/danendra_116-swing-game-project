import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

public class TopScorersFrame extends JFrame {
    private final PlayerService playerService = new PlayerService();

    public TopScorersFrame() {
        setTitle("FunPro Arena - Top 5 Scorers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 460);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContent());
    }

    private JPanel buildContent() {
        JPanel page = UiStyle.page();
        page.add(UiStyle.header("Top 5 Scorers", "Ranked by score, then by total wins."), BorderLayout.NORTH);

        String[] columns = {"Rank", "Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(38);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.setGridColor(new Color(225, 230, 240));
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        header.setBackground(UiStyle.PRIMARY_DARK);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        try {
            List<Player> players = playerService.getTopFiveScorers();
            for (int index = 0; index < players.size(); index++) {
                Player player = players.get(index);
                model.addRow(new Object[]{
                        index + 1,
                        player.getUsername(),
                        player.getWins(),
                        player.getLosses(),
                        player.getDraws(),
                        player.getScore()
                });
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this,
                    "Could not load the leaderboard.\n" + exception.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(225, 230, 240)));
        page.add(scrollPane, BorderLayout.CENTER);
        return page;
    }
}
