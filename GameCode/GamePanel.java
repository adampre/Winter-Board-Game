import java.awt.*;
import javax.swing.*;

import java.util.Random;
import java.awt.Point;
import java.io.File;

public class GamePanel extends JPanel
{
    private final File[] IMAGEFILES = {new File("PlayerImages/Player1.png"), new File("PlayerImages/Player2.png"), new File("PlayerImages/Player3.png"), new File("PlayerImages/Player4.png")};   
    private final File FORTIMAGE = new File("fort.png"); 
    private final Random RANDOM = new Random();

    private int dimension;
    private int tileSize;

    public Tile[][] board;

    public Player[] players;

    public GamePanel(int numberOfPlayers, int dimension)
    {
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

    private String[][] generateBoard()
    {
        String[][] generate = new String[board[0].length][board.length];

        for(int i = 0; i < generate.length; i++)
        {
            for(int j = 0; j < generate[i].length; j++)
            {
                String possiblePaths = "ulrd";

                if(j == 0)
                {
                    possiblePaths = remove(possiblePaths, "l");
                }
                else if(j == generate[i].length - 1)
                {
                    possiblePaths = remove(possiblePaths, "r");
                }

                if(i == 0)
                {
                    possiblePaths = remove(possiblePaths, "u");
                }
                else if(i == generate.length - 1)
                {
                    possiblePaths = remove(possiblePaths, "d");
                }

                generate[j][i] = possiblePaths;
            }
        }

        for(int i = 0; i < generate.length; i++)
        {
            for(int j = 0; j < generate[i].length; j++)
            {
                double random = Math.random();

                //up
                if(i != 0 && random < 0.25)
                {
                    if(amountOfPaths(generate[j][i]) < 2 && amountOfPaths(generate[j][i - 1]) < 2)
                    {
                        generate[j][i] = "-" + generate[j][i].substring(1, 4);
                        generate[j][i - 1] = generate[j][i - 1].substring(0, 3) + "-";
                    }
                }
                //left
                else if(j != 0 && random < 0.5)
                {
                    if(amountOfPaths(generate[j][i]) < 2 && amountOfPaths(generate[j - 1][i]) < 2)
                    {
                        generate[j][i] = generate[j][i].substring(0, 1) + "-" + generate[j][i].substring(2, 4);
                        generate[j - 1][i] = generate[j - 1][i].substring(0, 2) + "-" + generate[j - 1][i].substring(3, 4);
                    }
                }
                //right
                else if(j != generate[i].length - 1 && random < 0.75)
                {
                    if(amountOfPaths(generate[j][i]) < 2 && amountOfPaths(generate[j + 1][i]) < 2)
                    {
                        generate[j + 1][i] = generate[j + 1][i].substring(0, 1) + "-" + generate[j + 1][i].substring(2, 4);
                        generate[j][i] = generate[j][i].substring(0, 2) + "-" + generate[j][i].substring(3, 4);
                    }
                }
                //down
                else if(i != generate.length - 1)
                {
                    if(amountOfPaths(generate[j][i]) < 2 && amountOfPaths(generate[j][i + 1]) < 2)
                    {
                        generate[j][i + 1] = "-" + generate[j][i + 1].substring(1, 4);
                        generate[j][i] = generate[j][i].substring(0, 3) + "-";
                    }
                }
            }
        }

        return generate;
    }

    private int amountOfPaths(String word)
    {
        int count = 0; 

        for(int i = 0; i < word.length(); i++)
        {
            if(word.substring(i, i + 1).equalsIgnoreCase("-"))
            {
                count++;
            }
        }

        return count;
    }

    private String remove(String word, String letter)
    {
        return word.substring(0, word.indexOf(letter)) + "-" + word.substring(word.indexOf(letter) + 1, word.length());
    }

    public void initBoard()
    {
        String[][] generatedBoard = generateBoard();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.println(generatedBoard[j][i]);
                board[j][i] = new Tile(new File("Tiles/" + generatedBoard[j][i] + ".png"), new Point(j, i), generatedBoard[j][i]);
            }
        }
    }

    private void drawPlayers(Graphics g)
    {
        for(int i = 0; i < players.length; i++)
        {
            g.drawImage(players[i].image, players[i].indexes.x * tileSize, players[i].indexes.y * tileSize, tileSize, tileSize, null);
        }
    }

    private void drawBoard(Graphics g)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].image != null)
                {
                    g.drawImage(board[j][i].image, board[j][i].position.x * tileSize, board[j][i].position.y * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        drawBoard(g);

        drawPlayers(g);
    }
}
