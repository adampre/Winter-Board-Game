import java.awt.*;
import javax.swing.*;

import java.util.Random;
import java.awt.Point;
import java.io.File;

public class GamePanel extends JPanel
{
    private final File[] IMAGEFILES = {new File("PlayerImages/Player1.png"), new File("PlayerImages/Player2.png"), new File("PlayerImages/Player3.png"), null};    
    private final Random RANDOM = new Random();
    private final String TILETYPES = "ulrd";

    private int dimension;

    private int tileSize;

    public Tile[][] board;

    public Player[] players;

    public GamePanel(int numberOfPlayers, int dimension)
    {
        this.setBackground(Color.BLUE);
        this.dimension = dimension;

        this.tileSize = (dimension - 50) / (numberOfPlayers + 4);

        board = new Tile[numberOfPlayers + 4][numberOfPlayers + 4];

        players = new Player[numberOfPlayers];
    }

    public void initPlayers()
    {
        for(int i = 0; i < players.length; i++)
        {
            String answer = "";

            int strength = 0; 
            int speed = 0; 

            do
            {
                answer = JOptionPane.showInputDialog(this, "Input skill allocation for Player " + (i + 1) + ". Put in order of \"Strength Speed\". Ex. 1 4 or 2 3");

                strength = Integer.parseInt(answer.substring(0, 1));
                speed = Integer.parseInt(answer.substring(2, 3));
            }
            while(answer.equalsIgnoreCase("") || (strength + speed != 5));

            players[i] = new Player(IMAGEFILES[i], new Point((i % 2) * (board[0].length - 1), (i / 2) * (board.length - 1)), speed, strength);           
        }
    }

    public void initBoard()
    {
        
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        for(int i = 0; i < players.length; i++)
        {
            g.drawImage(players[i].image, players[i].indexes.x * tileSize, players[i].indexes.y * tileSize, tileSize, tileSize, null);
        }
    }
}
