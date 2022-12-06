import java.awt.Point;

public class Player 
{
    private Point indexes;

    private int health;
    private int speed;
    private int strength;
    private int numberofSnowballs;

    public Player(Point indexes, int health, int speed, int strength)
    {
        this.indexes = indexes;

        this.health = health;
        this.speed = speed;
        this.strength = strength;

        numberofSnowballs = strength;
    }
}
