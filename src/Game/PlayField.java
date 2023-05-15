package Game;

import java.awt.*;
import java.util.List;

public class PlayField {

    private static PlayField playFieldInstance;
    private Block[][] board;
    private final int boardVisibleStartIndex;

    public static void createInstance(int cellWidth, int cellHeight) {
        // will override old instance if called again
        playFieldInstance = new PlayField(cellWidth, cellHeight + 2);
    }

    public static PlayField getPlayFieldInstance() {
        return playFieldInstance;
    }

    private PlayField(int cellWidth, int cellsHeight) {
        boardVisibleStartIndex = 2;
        initBoard(cellWidth, cellsHeight);
    }

    public void drawBoard(Graphics g) {
        for (int i = boardVisibleStartIndex; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Block block = board[i][j];
                if (block != null) {
                    g.drawImage(
                            block.texture,
                            boardSpaceToPixelSpace(block.col),
                            boardSpaceToPixelSpace(block.row - boardVisibleStartIndex),
                            null
                    );
                }
            }
        }
    }

    public boolean addTetromino(Tetromino tetromino) {
        for (int i = 1; i < board[0].length - 1; i++) {
            if (board[boardVisibleStartIndex][i] != null)
                return false;
        }
        for (Block block : tetromino.blocks) {
            board[block.row][block.col] = block;
        }
        return true;
    }

    public void applyUpdateToTetromino(Tetromino tetromino, List<Point> updates) {
        for (int i = 0; i < updates.size(); i++) {
            Block block = tetromino.blocks.get(i);
            Point updatedBlockPos = updates.get(i);
            board[block.row][block.col] = null;
            block.row = updatedBlockPos.y;
            block.col = updatedBlockPos.x;
        }
        for (Block block : tetromino.blocks) {
            board[block.row][block.col] = block;
        }
    }

    public boolean validTetrominoMovementUpdate(Tetromino tetromino, List<Point> update) {
        // need to check if there is no shared point with board and updates
        for (Point point : update) {
            int row = point.y;
            int col = point.x;
            if (outOfBounds(row, col))
                return false;
            boolean tempFlag = true;
            for (Block block : tetromino.blocks) {
                if (block.col == col && block.row == row) {
                    tempFlag = false;
                    break;
                }
            }
            if (tempFlag && board[row][col] != null)
                return false;
        }
        return true;
    }

    private boolean outOfBounds(int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
    }

    private int boardSpaceToPixelSpace(int pos) {
        return pos * Block.BLOCK_EDGE_LENGTH;
    }

    public int checkForLineClear() {
        for (int i = boardVisibleStartIndex; i < board.length - 1; i++) {
            int nonNullBlockCount = 0;
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == null)
                    break;
                nonNullBlockCount++;
            }
            if (nonNullBlockCount == board[i].length - 2) {
                return i;
            }
        }
        return -1;
    }

    public void handleLineClear(int row) {
        // clear board[row]
        // shift all above down
        for (int i = 1; i < board[row].length - 1; i++)
            board[row][i] = null;
        shiftBoardDown(row);
    }

    private void shiftBoardDown(int row) {
        // removed row already.
        // need to shift all that above row down until get to boardStartIndex
        row--;
        while (row >= boardVisibleStartIndex) {
            shiftRowDown(row);
            row--;
        }
    }

    private void shiftRowDown(int row) {
        for (int i = 1; i < board[row].length - 1; i++) {
            board[row + 1][i] = board[row][i];
            if (board[row + 1][i] != null)
                board[row + 1][i].row++;
            board[row][i] = null;
        }
    }

    private void initBoard(int cellsWidth, int cellsHeight) {
        board = new Block[cellsHeight][cellsWidth];
        // init board right border
        for (int row = 0; row < cellsHeight; row++) {
            board[row][0] = new Block(
                    "border-block",
                    row,
                    0
            );
        }
        // init board bottom border
        for (int col = 1; col < cellsWidth; col++) {
            board[cellsHeight - 1][col] = new Block(
                    "border-block",
                    cellsHeight - 1,
                    col
            );
        }
        // init board left border
        for (int row = 0; row < cellsHeight - 1; row++) {
            board[row][cellsWidth - 1] = new Block(
                    "border-block",
                    row,
                    cellsWidth - 1
            );
        }
    }
}
