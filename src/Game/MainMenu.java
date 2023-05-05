package Game;

import javax.swing.*;

public class MainMenu extends JPanel {

    private final JFrame parentWindow;

    public MainMenu(JFrame parentWindow) {
        this.parentWindow = parentWindow;
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            startGame();
        });

        add(startButton);
        setVisible(true);
    }

    private void startGame() {
        parentWindow.setContentPane(new GamePanel(parentWindow));
        parentWindow.validate();
        parentWindow.invalidate();
    }
}
