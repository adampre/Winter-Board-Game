import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame
{
    private GamePanel gamePanel; 

    public GUI(int numberOfPlayers)
    {
        this.setTitle("Winter Game");
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 1100, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(numberOfPlayers, this.getWidth() - 100);
        gamePanel.initPlayers();
        gamePanel.initBoard();

        this.addMouseListener(gamePanel);

        this.add(gamePanel, BorderLayout.CENTER);
    }

    public void gameInit()
    {
        this.setVisible(true);
    }
}
