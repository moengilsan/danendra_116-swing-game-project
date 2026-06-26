import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class MainMenuFrame extends JFrame {
    private final Player currentPlayer;

    public MainMenuFrame(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        setTitle("FunPro Arena - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 430);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContent());
    }

    private JPanel buildContent() {
        JPanel page = UiStyle.page();
        page.add(UiStyle.header("Hello, " + currentPlayer.getUsername(),
                "Choose an activity from the menu below."), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.setOpaque(false);

        JPanel summary = UiStyle.card();
        summary.setLayout(new GridLayout(1, 4, 6, 0));
        summary.add(stat("Wins", currentPlayer.getWins()));
        summary.add(stat("Losses", currentPlayer.getLosses()));
        summary.add(stat("Draws", currentPlayer.getDraws()));
        summary.add(stat("Score", currentPlayer.getScore()));
        center.add(summary, BorderLayout.NORTH);

        JPanel menuCard = UiStyle.card();
        menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));

        JButton startGame = wideButton(UiStyle.primaryButton("Start New Game"));
        JButton statistics = wideButton(UiStyle.secondaryButton("My Statistics"));
        JButton topScorers = wideButton(UiStyle.secondaryButton("Top 5 Scorers"));
        JButton exit = wideButton(UiStyle.secondaryButton("Exit Application"));

        startGame.addActionListener(event -> {
            new GameFrame(currentPlayer).setVisible(true);
            dispose();
        });
        statistics.addActionListener(event -> new StatisticsFrame(currentPlayer).setVisible(true));
        topScorers.addActionListener(event -> new TopScorersFrame().setVisible(true));
        exit.addActionListener(event -> System.exit(0));

        menuCard.add(startGame);
        menuCard.add(Box.createRigidArea(new Dimension(0, 10)));
        menuCard.add(statistics);
        menuCard.add(Box.createRigidArea(new Dimension(0, 10)));
        menuCard.add(topScorers);
        menuCard.add(Box.createRigidArea(new Dimension(0, 10)));
        menuCard.add(exit);
        center.add(menuCard, BorderLayout.CENTER);

        page.add(center, BorderLayout.CENTER);
        return page;
    }

    private JPanel stat(String name, int value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel valueLabel = UiStyle.centeredLabel(String.valueOf(value), 22, UiStyle.PRIMARY_DARK);
        JLabel nameLabel = UiStyle.centeredLabel(name, 12, UiStyle.MUTED);
        nameLabel.setBorder(new EmptyBorder(4, 0, 0, 0));
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.add(nameLabel, BorderLayout.SOUTH);
        return panel;
    }

    private JButton wideButton(JButton button) {
        button.setAlignmentX(LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        button.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return button;
    }
}
