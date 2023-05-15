package Game;

import java.util.Random;

public class TetrominoGenerator {

    private int lastTetromino;
    private Tetromino upcomingTetromino;

    public TetrominoGenerator() {
        upcomingTetromino = genNewTetromino();
    }

    public Tetromino nextTetromino() {
        Tetromino temp = upcomingTetromino;
        upcomingTetromino = genNewTetromino();
        return temp;
    }

    public Tetromino upcomingTetromino() {
        return upcomingTetromino;
    }

    private Tetromino genNewTetromino() {
        Random random = new Random();
        int randomPick = random.nextInt(0, 7);
        while (randomPick == lastTetromino) randomPick = random.nextInt(0, 7);
        lastTetromino = randomPick;
        switch (randomPick) {
            case 0: return new L_Tetromino1();
            case 1: return new L_Tetromino2();
            case 2: return new T_Tetromino();
            case 3: return new Skew_Tetromino1();
            case 4: return new Skew_Tetromino2();
            case 5: return new Square_Tetromino();
            case 6: return new Straight_Tetromino();
            default: return null;
        }
    }
}
