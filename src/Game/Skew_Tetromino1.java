package Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Skew_Tetromino1 extends Tetromino {
    /*
           [1][0]
        [3][*]
     */
    public Skew_Tetromino1() {
        blocks = new LinkedList<>();
        blocks.add(new Block("green-block", 0, 5));
        blocks.add(new Block("green-block", 0, 4));
        blocks.add(new Block("green-block", 1, 4));
        blocks.add(new Block("green-block", 1, 3));
    }

    @Override
    public List<List<Point>> getCounterClockRotations() {
        switch (rotationState) {
            case 0 -> {
                return state0ToNeg1();
            }
            case -1 -> {
                return stateNeg1To2();
            }
            case 2 -> {
                return state2To1();
            }
            case 1 -> {
                return state1To0();
            }
        }
        return null;
    }

    @Override
    public List<List<Point>> getClockRotations() {
        switch (rotationState) {
            case 0 -> {
                return state0To1();
            }
            case 1 -> {
                return state1To2();
            }
            case 2 -> {
                return state2ToNeg1();
            }
            case -1 -> {
                return stateNeg1To0();
            }
        }
        return null;
    }

    private List<List<Point>> state0ToNeg1() {
        /*
             [1][0]    ->   [0]
          [3][*]            [1][*]
                               [3]
        */
        rotationState = -1;
        Point[] wallKickData = new Point[] {
                new Point(1, 0),
                new Point(1, 1),
                new Point(0, -2),
                new Point(1, -2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col - 2, blocks.get(0).row));
        basicRotation.add(new Point(blocks.get(1).col - 1, blocks.get(1).row + 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col + 1, blocks.get(3).row + 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> stateNeg1To2() {
        /*
            [0]                [*][3]
            [1][*]    ->    [0][1]
               [3]
         */
        rotationState = 2;
        Point[] wallKickData = new Point[] {
                new Point(-1, 0),
                new Point(-1, -1),
                new Point(0, 2),
                new Point(-1, 2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col, blocks.get(0).row + 2));
        basicRotation.add(new Point(blocks.get(1).col + 1, blocks.get(1).row + 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col + 1, blocks.get(3).row - 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> state2To1() {
        /*
               [*][3]   ->   [3]
            [0][1]           [*][1]
                                [0]
         */
        rotationState = 1;
        Point[] wallKickData = new Point[] {
                new Point(-1, 0),
                new Point(-1, 1),
                new Point(0, -2),
                new Point(-1, -2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col + 2, blocks.get(0).row));
        basicRotation.add(new Point(blocks.get(1).col + 1, blocks.get(1).row - 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col - 1, blocks.get(3).row - 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> state1To0() {
        /*
            [3]               [1][0]
            [*][1]    ->   [3][*]
               [0]
         */
        rotationState = 0;
        Point[] wallKickData = new Point[] {
                new Point(1, 0),
                new Point(1, -1),
                new Point(0, 2),
                new Point(1, 2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col, blocks.get(0).row - 2));
        basicRotation.add(new Point(blocks.get(1).col - 1, blocks.get(1).row - 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col - 1, blocks.get(3).row + 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> state0To1() {
        /*
               [1][0]   ->    [3]
            [3][*]            [*][1]
                                 [0]
         */
        Point[] wallKickData = new Point[] {
                new Point(-1, 0),
                new Point(-1, +1),
                new Point(0, -2),
                new Point(-1, -2)
        };
        rotationState = 1;
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col, blocks.get(0).row + 2));
        basicRotation.add(new Point(blocks.get(1).col + 1, blocks.get(1).row + 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col + 1, blocks.get(3).row - 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> state1To2() {
        /*
            [3]                [*][3]
            [*][1]    ->    [0][1]
               [0]
         */
        rotationState = 2;
        Point[] wallKickData = new Point[] {
                new Point(1, 0),
                new Point(1, -1),
                new Point(0, 2),
                new Point(1, 2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col - 2, blocks.get(0).row));
        basicRotation.add(new Point(blocks.get(1).col - 1, blocks.get(1).row + 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col + 1, blocks.get(3).row + 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> state2ToNeg1() {
        /*
               [*][3]  ->  [0]
            [0][1]         [1][*]
                              [3]
         */
        rotationState = -1;
        Point[] wallKickData = new Point[] {
                new Point(1, 0),
                new Point(1, 1),
                new Point(0, -2),
                new Point(1, -2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col, blocks.get(0).row - 2));
        basicRotation.add(new Point(blocks.get(1).col - 1, blocks.get(1).row - 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col - 1, blocks.get(3).row + 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }

    private List<List<Point>> stateNeg1To0() {
        /*
            [0]             [1][0]
            [1][*]   ->  [3][*]
               [3]
         */
        rotationState = 0;
        Point[] wallKickData = new Point[] {
                new Point(-1, 0),
                new Point(-1, -1),
                new Point(0, 2),
                new Point(-1, 2)
        };
        List<Point> basicRotation = new LinkedList<>();
        basicRotation.add(new Point(blocks.get(0).col + 2, blocks.get(0).row));
        basicRotation.add(new Point(blocks.get(1).col + 1, blocks.get(1).row - 1));
        basicRotation.add(new Point(blocks.get(2).col, blocks.get(2).row));
        basicRotation.add(new Point(blocks.get(3).col - 1, blocks.get(3).row - 1));
        List<List<Point>> rotations = createWallKickRotations(basicRotation, wallKickData);
        rotations.add(0, basicRotation);
        return rotations;
    }
}
