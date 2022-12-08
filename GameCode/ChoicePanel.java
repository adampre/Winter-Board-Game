import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class ChoicePanel extends JPanel
{
    public JButton throwSnowball;
    public JButton moveTiles;

    public boolean canThrowSnowball;
    public boolean canMoveTiles;

    public ChoicePanel()
    {
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new DimensionUIResource(130, HEIGHT));
        this.setLayout(new FlowLayout());

        canThrowSnowball = true;
        canMoveTiles = true;

        throwSnowball = new JButton("Throw a Snowball");
        this.add(throwSnowball);

        moveTiles = new JButton("Move Tiles");
        this.add(moveTiles);
    }

    public void update()
    {
        if(!canThrowSnowball)
        {
            this.remove(throwSnowball);
        }

        if(!canMoveTiles)
        {
            this.remove(moveTiles);
        }
    }
}
