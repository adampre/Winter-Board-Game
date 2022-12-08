import java.awt.Point;

import java.util.List;
import java.util.ArrayList;

public class Game 
{
    public boolean isLegalMove(Point position, int speed, Point click, Tile[][] board)
    {
        List<Point> moves = generateMoves(position, position, speed, board);

        for(int i = 0; i < moves.size(); i++)
        {
            if(moves.get(i).equals(click))
            {
                return true;
            }
        }

        return false;
    }

    public List<Point> generateMoves(Point originalPosition, Point position, int speed, Tile[][] board)
    {
        List<Point> moves = new ArrayList<Point>();

        if(speed == 0)
        {
            return moves;
        }

        for(int i = 0; i < board[position.x][position.y].tileType.length(); i++)
        {
            switch(board[position.x][position.y].tileType.substring(i, i + 1))
            {
                case "-":
                break;

                case "u":
                moves.add(new Point(position.x, position.y - 1));
                moves.addAll(generateMoves(originalPosition, new Point(position.x, position.y - 1), speed - 1, board));
                break;

                case "l":
                moves.add(new Point(position.x - 1, position.y));
                moves.addAll(generateMoves(originalPosition, new Point(position.x - 1, position.y), speed - 1, board));
                break;

                case "r":
                moves.add(new Point(position.x + 1, position.y));
                moves.addAll(generateMoves(originalPosition, new Point(position.x + 1, position.y), speed - 1, board));
                break;

                case "d":
                moves.add(new Point(position.x, position.y + 1));
                moves.addAll(generateMoves(originalPosition, new Point(position.x, position.y + 1), speed - 1, board));
                break;
            }
        }

        if(moves.indexOf(originalPosition) != -1)
        {
            moves.remove(moves.indexOf(originalPosition));
        }

        return moves;
    }
}
