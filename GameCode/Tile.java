import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Tile 
{
    public Image image;

    public Point position;

    public String tileType;

    public Tile(File imageFile, Point position, String tileType)
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

        this.position = position;

        this.tileType = tileType;
    }
}
