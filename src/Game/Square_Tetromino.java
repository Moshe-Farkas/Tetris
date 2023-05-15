package Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Square_Tetromino extends Tetromino {

    public Square_Tetromino() {
        blocks = new LinkedList<>();
        blocks.add(new Block("yellow-block", 0, 3));
        blocks.add(new Block("yellow-block", 0, 4));
        blocks.add(new Block("yellow-block", 1, 3));
        blocks.add(new Block("yellow-block", 1, 4));
    }

    @Override
    List<List<Point>> getCounterClockRotations() {
        return new LinkedList<>();
    }

    @Override
    public List<List<Point>> getClockRotations() {
        return new LinkedList<>();
    }
}
