import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Player 
{
    public Image image;

    //position of player
    public Point indexes;

    public int health;
    public int speed;
    public int strength;
    public int numberofSnowballs;

    public boolean isSafe;

    public Player(File imageFile, Point indexes, int speed, int strength)
    {
        if(imageFile != null)
        {
            try
            {
                image = ImageIO.read(imageFile);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        this.indexes = indexes;

        this.health = 10;
        this.speed = speed;
        this.strength = strength;

        numberofSnowballs = strength;

        isSafe = false;
    }
}
