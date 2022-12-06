/*
 * Tile Names:
 * 
 * u = up
 * l = left
 * r = right
 * d = down
 * 
 * Order: u -> l -> r -> d
 * 
 * If a tile does not have one of these, skip letter
 */

import javax.swing.*;

public class Main 
{
    public static void main(String[] args) 
    {
        int numberOfPlayers = 0;

        do
        {
            numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "How many players will be playing?"));
        }
        while(numberOfPlayers != 2 && numberOfPlayers != 3 && numberOfPlayers != 4);

        GUI gui = new GUI(numberOfPlayers);

        gui.gameInit();
    }
}
