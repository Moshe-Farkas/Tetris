package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class GamePanel extends JPanel {

    private final int gameScreenWidth = 288;
    private final int FRAME_RATE = 60;
    private final int gameScreenHeight = 504;
    private final JButton restartButton;
    private final JButton mainMenuButton;
    private final Input input;
    private PlayField playField;
    private boolean gameRunning;
    private Tetromino currentTetromino;
    private TetrominoGenerator tetrominoGenerator;
    private long tetrominoElapsedTimeTracker;
    private long tetrominoUpdateThreshold; // translates to the speed the current tetromino moves
    private int currentScore = 0;
    private int highScore;

    public GamePanel(ActionListener switchToMainMenu) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.addActionListener(switchToMainMenu);
        mainMenuButton.setBounds(gameScreenWidth / 2 - 100, gameScreenHeight / 2, 200, 30);
        mainMenuButton.setVisible(false);
        restartButton = new JButton("RESTART");
        restartButton.addActionListener(e -> startGame());
        restartButton.setBounds(gameScreenWidth / 2 - 100, (gameScreenHeight / 2) + 35, 200, 30);
        restartButton.setVisible(false);
        add(restartButton);
        add(mainMenuButton);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                onVisible();
            }
        });
        input = new Input();
        addKeyListener(input);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playField.drawBoard(g);
        drawUpcomingTetromino(g);
        g.setColor(Color.YELLOW);
        g.drawString(String.format("High-Score: %d", highScore), gameScreenWidth + 25, 130);
        g.drawString(String.format("Current Score: %d", currentScore), gameScreenWidth + 25, 145);
    }

    public void startGame() {
        restartButton.setVisible(false);
        mainMenuButton.setVisible(false);
        currentScore = 0;
        highScore = loadHighScore();

        handleHighScoreUpdate();

        tetrominoUpdateThreshold = 195;
        PlayField.createInstance(12, 21);
        playField = PlayField.getPlayFieldInstance();

        tetrominoGenerator = new TetrominoGenerator();
        currentTetromino = tetrominoGenerator.nextTetromino();
        playField.addTetromino(currentTetromino);

        new Thread(() -> {
            gameRunning = true;
            long frameStart;
            while (gameRunning) {
                frameStart = System.nanoTime();
                update();
                repaint();
                input.resetKeyPressed();

                long frameDuration = System.nanoTime() - frameStart;
                try {
                    double amountToSleep = (long) (1000000000d / FRAME_RATE) - frameDuration;
                    amountToSleep /= 1000000d;
                    if (amountToSleep < 0)
                        amountToSleep = 0;

                    Thread.sleep((long)amountToSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tetrominoElapsedTimeTracker += System.nanoTime() - frameStart;
            }
            gameEnded();
        }).start();
    }

    private void update() {
        handleTetrominoDownMovement();
        if (input.keyPressed == input.RIGHT_KEY) {
            handleTetrominoRightMovement();
        }
        else if (input.keyPressed == input.LEFT_KEY) {
            handleTetrominoLeftMovement();
        }
        else if (input.keyPressed == input.UP_KEY) {
            handleClockWiseRotation();
        }
        else if (input.keyPressed == input.DOWN_KEY) {
            handleCounterClockWiseRotation();
        }
    }

    private void handleTetrominoDownMovement() {
        if ((tetrominoElapsedTimeTracker / 1000000d) >= tetrominoUpdateThreshold) {
            tetrominoElapsedTimeTracker = 0;
            List<Point> downMovement = currentTetromino.getDownMove();
            if (playField.validTetrominoMovementUpdate(currentTetromino, downMovement)) {
                playField.applyUpdateToTetromino(currentTetromino, downMovement);
            }
            else {
                newTetromino();
                checkForLineClear();
            }
        }
    }

    private void handleTetrominoLeftMovement() {
        List<Point> leftMoves = currentTetromino.getLeftMove();
        if (playField.validTetrominoMovementUpdate(currentTetromino, leftMoves)) {
            playField.applyUpdateToTetromino(currentTetromino, leftMoves);
        }
    }

    private void handleTetrominoRightMovement() {
        List<Point> rightMoves = currentTetromino.getRightMove();
        if (playField.validTetrominoMovementUpdate(currentTetromino, rightMoves)) {
            playField.applyUpdateToTetromino(currentTetromino, rightMoves);
        }
    }

    private void handleClockWiseRotation() {
        // gets all 5 rotations starting with basic rotation and applies the first valid on.
        List<List<Point>> rotations = currentTetromino.getClockRotations();
        for (List<Point> rotation : rotations) {
            if (playField.validTetrominoMovementUpdate(currentTetromino, rotation)) {
                playField.applyUpdateToTetromino(currentTetromino, rotation);
                break;
            }
        }
    }

    private void handleCounterClockWiseRotation() {
        // gets all 5 rotations starting with basic rotation and applies the first valid on.
        List<List<Point>> rotations = currentTetromino.getCounterClockRotations();
        for (List<Point> rotation : rotations) {
            if (playField.validTetrominoMovementUpdate(currentTetromino, rotation)) {
                playField.applyUpdateToTetromino(currentTetromino, rotation);
                break;
            }
        }
    }

    private void newTetromino() {
        currentTetromino = tetrominoGenerator.nextTetromino();
        if (!playField.addTetromino(currentTetromino)) {
            gameEnded();
        }
    }

    private void checkForLineClear() {
        if (playField.checkForLineClear() != -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int linesCleared = 0;
            while (playField.checkForLineClear() != -1) {
                linesCleared++;
                playField.handleLineClear(playField.checkForLineClear());
            }
            updateScore(linesCleared);
            updateSpeed();
        }
    }

    private void updateScore(int linesCleared) {
        currentScore += linesCleared;
    }

    private void updateSpeed() {
        if (currentScore % 10 == 0)
            tetrominoUpdateThreshold -= 10;
    }

    private int loadHighScore() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("src\\resources\\high-score.txt"));
            highScore = Integer.parseInt(in.readLine());
            in.close();
            return highScore;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void gameEnded() {
        gameRunning = false;
        handleHighScoreUpdate();
        restartButton.setVisible(true);
        mainMenuButton.setVisible(true);
        repaint();
    }

    private void handleHighScoreUpdate() {
        if (highScore > currentScore)
            return;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src\\resources\\high-score.txt", false));
            out.write(Integer.toString(currentScore));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onVisible() {
        requestFocusInWindow();
    }

    private void drawUpcomingTetromino(Graphics g) {
        for (Block block : tetrominoGenerator.upcomingTetromino().blocks) {
            g.drawImage(
                    block.texture,
                    (block.col * Block.BLOCK_EDGE_LENGTH) + gameScreenWidth - 30,
                    (block.row * Block.BLOCK_EDGE_LENGTH) + 20,
                    null
            );
        }
    }
}
