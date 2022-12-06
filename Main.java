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
        int numberOfPlayers = Integer.valueOf(JOptionPane.showInputDialog(null, "How many players will be playing?"));

        GUI gui = new GUI(numberOfPlayers);

        gui.gameInit();
    }
}
