package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JFrame parentWindow;
    private final int gameScreenWidth = 288;
    private final int gameScreenHeight = 504;

    public GamePanel(JFrame parentWindow) {
        this.parentWindow = parentWindow;
        setBackground(Color.BLACK);

        setVisible(true);
    }

}
