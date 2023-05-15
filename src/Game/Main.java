package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{

    private static final int WINDOW_HEIGHT = 504 + 37;
    private static final int WINDOW_WIDTH = 288 + 14 + 150;
    private static MainMenu mainMenu;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        Main m = new Main();
    }

    public Main() {
        JPanel container = new JPanel();
        CardLayout cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        gamePanel = new GamePanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, "mainMenu");
            }
        });
        mainMenu = new MainMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startGame();
                cardLayout.show(container, "gamePanel");
            }
        });
        container.add(gamePanel, "gamePanel");
        container.add(mainMenu, "mainMenu");
        cardLayout.show(container, "mainMenu");


        setContentPane(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}