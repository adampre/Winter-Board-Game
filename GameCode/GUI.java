import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame
{
    private GamePanel gamePanel; 

    public GUI(int numberOfPlayers)
    {
        this.setTitle("Winter Game");
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 1400, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(numberOfPlayers, this.getWidth() - 400);
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
