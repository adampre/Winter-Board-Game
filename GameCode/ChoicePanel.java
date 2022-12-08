import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class ChoicePanel extends JPanel
{
    public JButton throwSnowball;
    public JButton moveTiles;

    public JTextArea playerDisplay;

    public boolean canThrowSnowball;
    public boolean canMoveTiles;

    public ChoicePanel()
    {
        this.setPreferredSize(new DimensionUIResource(430, HEIGHT));
        this.setLayout(new FlowLayout());

        canThrowSnowball = true;
        canMoveTiles = true;

        throwSnowball = new JButton("Throw a Snowball");

        moveTiles = new JButton("Move Tiles");

        playerDisplay = new JTextArea();
        playerDisplay.setPreferredSize(new DimensionUIResource(400, 500));
        playerDisplay.setEditable(false);
        playerDisplay.setBackground(Color.WHITE);
        this.add(playerDisplay);

        this.add(moveTiles);
        this.add(throwSnowball);
    }

    public void reset()
    {
        this.add(moveTiles);
        this.add(throwSnowball);

        canThrowSnowball = true;
        canMoveTiles = true;
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
