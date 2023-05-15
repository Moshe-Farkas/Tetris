package Game;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Tetromino {

    protected List<Block> blocks;
    protected int rotationState = 0;
    abstract List<List<Point>> getCounterClockRotations();
    abstract List<List<Point>> getClockRotations();

    public List<Point> getDownMove() {
        List<Point> downMoves = new LinkedList<>();
        for (Block block : blocks) {
            downMoves.add(new Point(block.col, block.row + 1));
        }
        return downMoves;
    }

    public List<Point> getRightMove() {
        List<Point> rightMoves = new LinkedList<>();
        for (Block block : blocks) {
            rightMoves.add(new Point(block.col + 1, block.row));
        }
        return rightMoves;
    }

    public List<Point> getLeftMove() {
        List<Point> leftMoves = new LinkedList<>();
        for (Block block : blocks) {
            leftMoves.add(new Point(block.col - 1, block.row));
        }
        return leftMoves;
    }

    protected List<List<Point>> createWallKickRotations(List<Point> basicRotation, Point[] wallKickData) {
        List<List<Point>> rotations = new LinkedList<>();
        for (Point wallKick : wallKickData) {
            List<Point> kickedRotation = new LinkedList<>();
            for (Point blockPoint : basicRotation) {
                kickedRotation.add(new Point(blockPoint.x + wallKick.x, blockPoint.y + wallKick.y));
            }
            rotations.add(kickedRotation);
        }
        return rotations;
    }
}
