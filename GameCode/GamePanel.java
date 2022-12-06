import java.awt.*;
import javax.swing.*;

import java.util.Random;

public class GamePanel extends JPanel
{
    private final Random RANDOM = new Random();

    public Tile[][] board;

    public Player[] players;

    public GamePanel(int numberOfPlayers)
    {
        board = new Tile[numberOfPlayers + 2][numberOfPlayers + 4];

        players = new Player[numberOfPlayers];
    }

    public void initPlayers()
    {
        for(int i = 0; i < players.length; i++)
        {
            String answer = JOptionPane.showInputDialog(this, "");
        }
    }

    public void initBoard()
    {

    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
    }
}
