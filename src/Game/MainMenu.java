package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    public MainMenu(ActionListener startGame) {
        setFocusable(false);
        setBackground(Color.LIGHT_GRAY);
        JButton button = new JButton("START GAME");
        button.addActionListener(startGame);
        add(button);
    }
}
