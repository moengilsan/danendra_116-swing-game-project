import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel databaseStatus = new JLabel();
    private final PlayerService playerService = new PlayerService();

    public LoginFrame() {
        this(true);
    }

    LoginFrame(boolean checkDatabase) {
        setTitle("FunPro Arena - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 430);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContent());
        if (checkDatabase) {
            updateDatabaseStatus();
        } else {
            databaseStatus.setText("Database connected");
            databaseStatus.setForeground(UiStyle.SUCCESS);
        }
    }

    private JPanel buildContent() {
        JPanel page = UiStyle.page();
        page.add(UiStyle.header("FunPro Arena", "Java Swing Tic-Tac-Toe with MSSQL"), BorderLayout.NORTH);

        JPanel card = UiStyle.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Player Login");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setForeground(UiStyle.TEXT);
        welcome.setAlignmentX(LEFT_ALIGNMENT);

        card.add(welcome);
        card.add(Box.createRigidArea(new Dimension(0, 14)));
        card.add(fieldLabel("Username"));
        card.add(Box.createRigidArea(new Dimension(0, 7)));
        prepareField(usernameField);
        card.add(usernameField);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(fieldLabel("Password"));
        card.add(Box.createRigidArea(new Dimension(0, 7)));
        prepareField(passwordField);
        card.add(passwordField);
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        JButton loginButton = UiStyle.primaryButton("Log In");
        loginButton.setAlignmentX(LEFT_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        loginButton.addActionListener(event -> attemptLogin());
        passwordField.addActionListener(event -> attemptLogin());
        card.add(loginButton);
        card.add(Box.createRigidArea(new Dimension(0, 12)));

        databaseStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
        databaseStatus.setAlignmentX(LEFT_ALIGNMENT);
        card.add(databaseStatus);

        JLabel hint = new JLabel("Demo account: student1 / 12345");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(UiStyle.MUTED);
        hint.setBorder(new EmptyBorder(8, 0, 0, 0));
        hint.setAlignmentX(LEFT_ALIGNMENT);
        card.add(hint);

        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(UiStyle.TEXT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }

    private void prepareField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setPreferredSize(new Dimension(380, 42));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(198, 207, 223)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        field.setAlignmentX(LEFT_ALIGNMENT);
    }

    private void updateDatabaseStatus() {
        boolean connected = DatabaseManager.testConnection();
        databaseStatus.setText(connected ? "Database connected" : "Database unavailable - check setup and configuration");
        databaseStatus.setForeground(connected ? UiStyle.SUCCESS : UiStyle.DANGER);
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Player player = playerService.login(username, password);
            if (player == null) {
                JOptionPane.showMessageDialog(this, "Invalid username or password.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
                return;
            }

            JOptionPane.showMessageDialog(this, "Welcome, " + player.getUsername() + "!",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            new MainMenuFrame(player).setVisible(true);
            dispose();
        } catch (SQLException exception) {
            updateDatabaseStatus();
            JOptionPane.showMessageDialog(this,
                    "Could not connect to SQL Server.\n" + exception.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
