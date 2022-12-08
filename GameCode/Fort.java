import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Fort 
{
    public Image image;

    public Point position;
    public int dimension;

    public Fort(Point point, int dimension)
    {
        try
        {
            image = ImageIO.read(new File("PlayerImages/fort.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        this.position = point;
        this.dimension = dimension;
    }
}
