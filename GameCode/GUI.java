import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
    private GamePanel gamePanel; 

    public GUI(int numberOfPlayers)
    {
        this.setTitle("Winter Game");
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(numberOfPlayers, this.getWidth());
        gamePanel.initPlayers();
        gamePanel.initBoard();

        this.add(gamePanel, BorderLayout.CENTER);
    }

    public void gameInit()
    {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
    
}
