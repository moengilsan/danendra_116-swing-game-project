import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private final char[] board = new char[9];
    private final Random random = new Random();

    public GameLogic() {
        resetBoard();
    }

    public void resetBoard() {
        for (int index = 0; index < board.length; index++) {
            board[index] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= board.length || board[index] != ' ') {
            return false;
        }
        board[index] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] patterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : patterns) {
            if (board[pattern[0]] == symbol
                    && board[pattern[1]] == symbol
                    && board[pattern[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        if (checkWinner('X') || checkWinner('O')) {
            return false;
        }
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public int computerMove() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int index = 0; index < board.length; index++) {
            if (board[index] == ' ') {
                emptyCells.add(index);
            }
        }
        if (emptyCells.isEmpty()) {
            return -1;
        }

        int chosenIndex = emptyCells.get(random.nextInt(emptyCells.size()));
        board[chosenIndex] = 'O';
        return chosenIndex;
    }

    public char[] getBoard() {
        return board.clone();
    }
}
