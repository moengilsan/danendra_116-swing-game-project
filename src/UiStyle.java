import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

public class UiStyle {
    public static final Color BACKGROUND = new Color(244, 247, 252);
    public static final Color CARD = Color.WHITE;
    public static final Color PRIMARY = new Color(45, 91, 210);
    public static final Color PRIMARY_DARK = new Color(30, 64, 175);
    public static final Color TEXT = new Color(25, 35, 55);
    public static final Color MUTED = new Color(102, 112, 133);
    public static final Color SUCCESS = new Color(21, 128, 61);
    public static final Color DANGER = new Color(190, 40, 40);

    private UiStyle() {
    }

    public static JPanel page() {
        JPanel panel = new JPanel(new BorderLayout(16, 16));
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(24, 28, 24, 28));
        return panel;
    }

    public static JPanel card() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 240)),
                new EmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }

    public static JPanel header(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout(4, 4));
        header.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 27));
        titleLabel.setForeground(TEXT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(MUTED);

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);
        return header;
    }

    public static JButton primaryButton(String text) {
        JButton button = baseButton(text);
        button.setBackground(new Color(232, 238, 252));
        button.setForeground(PRIMARY_DARK);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY, 2),
                new EmptyBorder(10, 16, 10, 16)
        ));
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
        button.setUI(new BasicButtonUI());
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setBorder(new EmptyBorder(12, 18, 12, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(180, 46));
        return button;
    }

    public static JLabel centeredLabel(String text, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(color);
        return label;
    }
}
