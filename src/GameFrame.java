import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private final PlayerService playerService = new PlayerService();
    private final GameLogic gameLogic = new GameLogic();
    private final JButton[] boardButtons = new JButton[9];
    private final JLabel statusLabel = new JLabel("Your turn - choose an empty square.");
    private boolean gameFinished;

    public GameFrame(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        setTitle("FunPro Arena - Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(590, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContent());
    }

    private JPanel buildContent() {
        JPanel page = UiStyle.page();
        page.add(UiStyle.header("Tic-Tac-Toe", "You are X. The computer is O."), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(14, 14));
        center.setOpaque(false);

        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        statusLabel.setForeground(UiStyle.PRIMARY_DARK);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setBorder(new EmptyBorder(4, 0, 4, 0));
        center.add(statusLabel, BorderLayout.NORTH);

        JPanel boardCard = UiStyle.card();
        boardCard.setLayout(new GridLayout(3, 3, 9, 9));
        for (int index = 0; index < boardButtons.length; index++) {
            final int selectedIndex = index;
            JButton button = new JButton("");
            button.setFont(new Font("SansSerif", Font.BOLD, 50));
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBackground(Color.WHITE);
            button.setForeground(UiStyle.PRIMARY_DARK);
            button.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 230), 2));
            button.addActionListener(event -> handlePlayerMove(selectedIndex));
            boardButtons[index] = button;
            boardCard.add(button);
        }
        center.add(boardCard, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        JButton restart = UiStyle.secondaryButton("Restart Board");
        JButton returnMenu = UiStyle.secondaryButton("Return to Menu");
        restart.addActionListener(event -> resetGame());
        returnMenu.addActionListener(event -> returnToMenu());
        actions.add(restart);
        actions.add(returnMenu);
        center.add(actions, BorderLayout.SOUTH);

        page.add(center, BorderLayout.CENTER);
        return page;
    }

    private void handlePlayerMove(int index) {
        if (gameFinished || !gameLogic.makeMove(index, 'X')) {
            if (!gameFinished) {
                statusLabel.setText("That square is already occupied. Try another.");
                statusLabel.setForeground(UiStyle.DANGER);
            }
            return;
        }

        boardButtons[index].setText("X");
        boardButtons[index].setForeground(UiStyle.PRIMARY_DARK);

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN", "You won! +10 points");
            return;
        }
        if (gameLogic.isDraw()) {
            finishGame("DRAW", "The game is a draw. +3 points");
            return;
        }

        int computerIndex = gameLogic.computerMove();
        if (computerIndex >= 0) {
            boardButtons[computerIndex].setText("O");
            boardButtons[computerIndex].setForeground(UiStyle.DANGER);
        }

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSS", "The computer won. Better luck next time!");
        } else if (gameLogic.isDraw()) {
            finishGame("DRAW", "The game is a draw. +3 points");
        } else {
            statusLabel.setText("Your turn - choose an empty square.");
            statusLabel.setForeground(UiStyle.PRIMARY_DARK);
        }
    }

    private void finishGame(String result, String message) {
        if (gameFinished) {
            return;
        }
        gameFinished = true;
        setBoardEnabled(false);
        statusLabel.setText(message);
        statusLabel.setForeground(result.equals("LOSS") ? UiStyle.DANGER : UiStyle.SUCCESS);

        try {
            currentPlayer = playerService.updateStatistics(currentPlayer, result);
            JOptionPane.showMessageDialog(this, message + "\nYour statistics were saved.",
                    "Game Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this,
                    "The game ended, but statistics could not be saved.\n" + exception.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetGame() {
        gameLogic.resetBoard();
        gameFinished = false;
        for (JButton button : boardButtons) {
            button.setText("");
            button.setEnabled(true);
        }
        statusLabel.setText("Your turn - choose an empty square.");
        statusLabel.setForeground(UiStyle.PRIMARY_DARK);
    }

    private void setBoardEnabled(boolean enabled) {
        for (JButton button : boardButtons) {
            button.setEnabled(enabled);
        }
    }

    private void returnToMenu() {
        try {
            Player refreshedPlayer = playerService.findById(currentPlayer.getId());
            if (refreshedPlayer != null) {
                currentPlayer = refreshedPlayer;
            }
        } catch (SQLException ignored) {
        }
        new MainMenuFrame(currentPlayer).setVisible(true);
        dispose();
    }
}
