import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class UiStyle {
    public static final Color BACKGROUND = new Color(238, 238, 238);
    public static final Color CARD = new Color(250, 250, 250);
    public static final Color PRIMARY = new Color(70, 70, 70);
    public static final Color PRIMARY_DARK = Color.BLACK;
    public static final Color TEXT = Color.BLACK;
    public static final Color MUTED = Color.DARK_GRAY;
    public static final Color SUCCESS = new Color(0, 120, 0);
    public static final Color DANGER = new Color(170, 0, 0);

    private UiStyle() {
    }

    public static JPanel page() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        return panel;
    }

    public static JPanel card() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(15, 15, 15, 15)
        ));
        return panel;
    }

    public static JPanel header(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout(2, 2));
        header.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(TEXT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(MUTED);

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);
        return header;
    }

    public static JButton primaryButton(String text) {
        JButton button = baseButton(text);
        return button;
    }

    public static JButton secondaryButton(String text) {
        JButton button = baseButton(text);
        button.setBackground(new Color(232, 238, 252));
        button.setForeground(PRIMARY_DARK);
        return button;
    }

    private static JButton baseButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(170, 36));
        return button;
    }

    public static JLabel centeredLabel(String text, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(color);
        return label;
    }
}
