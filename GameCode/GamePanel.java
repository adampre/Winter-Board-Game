import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Random;
import java.awt.Point;
import java.io.File;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener, ActionListener
{
    private final File[] IMAGEFILES = {new File("PlayerImages/Player1.png"), new File("PlayerImages/Player2.png"), new File("PlayerImages/Player3.png"), new File("PlayerImages/Player4.png")};   
    private final Random RANDOM = new Random();

    private int tileSize;

    private Tile[][] board;
    private LinkedList<Fort> forts;

    private Player[] players;
    private int currentPlayer;

    private Game game;

    private ChoicePanel choicePanel;

    public GamePanel(int numberOfPlayers, int dimension)
    {
        this.setLayout(new BorderLayout());

        choicePanel = new ChoicePanel();
        this.add(choicePanel, BorderLayout.EAST);

        this.tileSize = (dimension - 50) / (numberOfPlayers + 4);

        board = new Tile[numberOfPlayers + 4][numberOfPlayers + 4];
        forts = new LinkedList<Fort>();

        players = new Player[numberOfPlayers];
        currentPlayer = 0;

        game = new Game();
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
                if(Math.random() < 0.8) 
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
        }

        for(int i = 0; i < players.length + 3; i++)
        {
            Point index = null;
            boolean exists = false;

            do
            {
                index = new Point(RANDOM.nextInt(board[0].length), RANDOM.nextInt(board.length));
            }
            while(containsFort(index));

            if(!exists)
            {
                forts.add(new Fort(index, tileSize / 3));
            }
        }

        return generate;
    }

    private boolean containsFort(Point index)
    {
        for(int i = 0; i < forts.size(); i++)
        {
            if(forts.get(i).position.equals(index))
            {
                return true;
            }
        }

        return false;
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

        for(int i = 0; i < forts.size(); i++)
        {
            if(forts.get(i).image != null)
            {
                g.drawImage(forts.get(i).image, forts.get(i).position.x * tileSize, forts.get(i).position.y * tileSize, forts.get(i).dimension, forts.get(i).dimension, null);
            }
        }
    }

    private void drawLegalMoves(Graphics g)
    {
        List<Point> moves = game.generateMoves(players[currentPlayer].indexes, players[currentPlayer].indexes, players[currentPlayer].speed, board);

        //alternates between green and red
        Color color = Color.GREEN;

        for(int i = 0; i < moves.size(); i++)
        {
            g.setColor(color);

            g.fillOval((moves.get(i).x * tileSize) + (tileSize / 3), (moves.get(i).y * tileSize) + (tileSize / 3), tileSize / 3, tileSize / 3);

            if(color.equals(Color.GREEN))
            {
                color = Color.RED;
            }
            else
            {
                color = Color.GREEN;
            }
        }
    }

    private Point getIndexOfClick(Point click)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if((board[j][i].position.x < click.x + 8 && click.x < board[j][i].position.x + tileSize + 8) && 
                   (board[j][i].position.y + 30 < click.y && click.y < board[j][i].position.y + tileSize + 30))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    private void executeMove(Point index)
    {
        if(game.isLegalMove(players[currentPlayer].indexes, players[currentPlayer].speed, index, board))
        {

        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        drawBoard(g);

        drawPlayers(g);

        drawLegalMoves(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Point index = getIndexOfClick(e.getPoint());
        
        if(index != null)
        {
            //executeMove(index);
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //if()
        
    }
}
