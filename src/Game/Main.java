package Game;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int WINDOW_HEIGHT = 504 + 37;
    private static final int WINDOW_WIDTH = 288 + 14 + 150;

    // 37 height
    // 14 width

//    width: [120, 144, 168, 192, 216, 240, 264, 288, 312, 336]
//    height: [408, 432, 456, 480, 504, 528, 552, 576]

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.setContentPane(new MainMenu(window));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}