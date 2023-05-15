package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block {

    private final static BufferedImage blueBlock;
    private final static BufferedImage borderBlock;
    private final static BufferedImage greenBlock;
    private final static BufferedImage lightBlueBlock;
    private final static BufferedImage orangeBlock;
    private final static BufferedImage purpleBlock;
    private final static BufferedImage redBlock;
    private final static BufferedImage yellowBlock;
    private final static BufferedImage blueGhostBlock;
    private final static BufferedImage purpleGhostBlock;
    private final static BufferedImage redGhostBlock;
    private final static BufferedImage orangeGhostBlock;
    private final static BufferedImage yellowGhostBlock;
    private final static BufferedImage lightBlueGhostBlock;
    static {
        try {
            blueBlock = ImageIO.read(new File("src\\resources\\blue-block.png"));
            borderBlock = ImageIO.read(new File("src\\resources\\border-block.png"));
            greenBlock = ImageIO.read(new File("src\\resources\\green-block.png"));
            lightBlueBlock = ImageIO.read(new File("src\\resources\\light-blue-block.png"));
            orangeBlock = ImageIO.read(new File("src\\resources\\orange-block.png"));
            purpleBlock = ImageIO.read(new File("src\\resources\\purple-block.png"));
            redBlock = ImageIO.read(new File("src\\resources\\red-block.png"));
            yellowBlock = ImageIO.read(new File("src\\resources\\yellow-block.png"));
            blueGhostBlock = ImageIO.read(new File("src\\resources\\ghost-blue-block.png"));
            purpleGhostBlock = ImageIO.read(new File("src\\resources\\ghost-purple-block.png"));
            redGhostBlock = ImageIO.read(new File("src\\resources\\ghost-red-block.png"));
            orangeGhostBlock = ImageIO.read(new File("src\\resources\\ghost-orange-block.png"));
            yellowGhostBlock = ImageIO.read(new File("src\\resources\\ghost-yellow-block.png"));
            lightBlueGhostBlock = ImageIO.read(new File("src\\resources\\ghost-light-blue-block.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final int BLOCK_EDGE_LENGTH = 24;
    public BufferedImage texture;
    public int row;
    public int col;

    public Block(String textureName, int row, int col) {
        texture = assignTexture(textureName);
        this.row = row;
        this.col = col;
    }

    private BufferedImage assignTexture(String textureName) {
        switch (textureName) {
            case "border-block":  return borderBlock;
            case "blue-block": return blueBlock;
            case "green-block": return greenBlock;
            case "light-blue-block": return lightBlueBlock;
            case "orange-block": return orangeBlock;
            case "purple-block": return purpleBlock;
            case "red-block": return redBlock;
            case "yellow-block": return yellowBlock;
            case "ghost-blue-block": return blueGhostBlock;
            case "ghost-purple-block": return purpleGhostBlock;
            case "ghost-light-blue-block": return lightBlueGhostBlock;
            case "ghost-yellow-block": return yellowGhostBlock;
            case "ghost-orange-block": return orangeGhostBlock;
            case "ghost-red-block": return redGhostBlock;
        }
        return null;
    }
}
