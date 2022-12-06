import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Tile 
{
    private Image image;

    private Point position;

    public Tile(File imageFile, Point position)
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
    }
}
