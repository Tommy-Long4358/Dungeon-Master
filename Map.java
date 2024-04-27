import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Map class that represents the map the hero goes through. */
public class Map
{
	/* 2D Grid with each space representing a room. */
	private char [][] map;
	
	/* Represents truth value of whether maze room has been visited */
	private boolean [][] revealed;
	
	private static Map instance;
	
	/** Private Map Constructor
	 *  Initializes a 5x5 2D array set of characters that represents the different rooms that is displayed in the Map.txt files.
	 *  Initializes a 5x5 set of booleans that determines if these rooms have been visited or not.
	 */
	private Map()
	{
        map = new char[5][5];
        revealed = new boolean[5][5];
	}
	
	/** Static Singleton method that makes only one instance of the Map class only.
	 *  @return an object of the Map class.
	 */
	public static Map getInstance()
	{
		if (instance == null)
		{
			instance = new Map();
		}
		
		return instance;
	}
	
	/** Reads a map from the .txt files and writes its content to the 2D map array.
	 *  @param mapNum   Level of the map
	 *  @throws   FileNotFoundException if map file does not exist
	 */
	public void loadMap(int mapNum)
	{
		try
		{
			Scanner read = new Scanner(new File("Map" + mapNum));
			int index = 0;

			// Read each line of the text file
			while (read.hasNext())
			{
				String line = read.nextLine();
				
				// Separates each line in a string array
				String [] chars = line.split(" ");

				for (int i = 0; i < map[0].length; i++)
				{
					// Assign char to map
					map[index][i] = chars[i].charAt(0);

					// Mark room as false; not revealed
					revealed[index][i] = false;
				}

				index += 1;
			}

			// Close file
			read.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Map not found!");
		}
	}
	
	/** Char method that gets the coordinates of Point p.
	 *  @param p  Point on map
	 *  @return Coordinates of p on the map 2D array as a char.
	 */
	public char getCharAtLoc(Point p)
	{
		int x = (int) p.getX();
	    int y = (int) p.getY();
	    
	    return map[x][y];
	}
	
	/** Point method that finds the starting location in a map.
	 *  @return location of starting position
	 */
	public Point findStart()
	{
		Point p = new Point();

		// Search through map for start location denoted by 's' in map array
		for (int x = 0; x < map.length; x++)
		{
			for (int y = 0; y < map[0].length; y++)
			{

				if (map[x][y] == 's')
				{
					// Declare X and Y points for starting position
					p.x = x;
					p.y = y;

					// Reveal room on map
                    revealed[x][y] = true;	
				}
			}		
		}
		
		return p;
	}
	
	/** Reveals the character of the location the user is heading towards.
	 *  @param P A Point location on the map the user is heading towards.
	 */
	public void reveal(Point P)
	{
		int x = (int) P.getX();
		int y = (int) P.getY();
		
		revealed[x][y] = true;
	}
	
	/** Changes the character at a location to be an empty room after it has been visited.
	 *  @param P Location of user on map
	 */
	public void removeCharAtLoc(Point P)
	{
		for (int x = 0; x < map.length; x++)
		{
			for (int y = 0; y < map[0].length; y++)
			{
				// Only change the room to empty if the hero is currently on it and it isn't the starting or finish position
				if ((x == P.getX() && y == P.getY()) && (map[x][y] != 's' || map[x][y] != 'f'))
				{
					map[x][y] = 'n';
				}
			}
		}
	}
	
	/** Map's toString function
	 *  @param P  Hero's location
	 *  @return String of the map with the hero's current position, revealed rooms, and any unrevealed rooms represented by 5x5s
	 */
	public String mapToString(Point P)
	{
		 String mapString = "";
		 
		 for (int x = 0; x < revealed.length; x++)
		 {
			 for (int y = 0; y < revealed[0].length; y++)
			 {
				 // Use "*" to represent the hero's current location on the map
				 if ((x == P.getX()) && (y == P.getY()))
				 {
					 mapString += "* ";
				 }
				 
				 // Use "x" to represent all the unrevealed rooms 
				 else if (!revealed[x][y])
				 {
					 mapString += "x ";
				 }
				 
				 // If the char at that location is true, it shows the char at that location ("n")
				 else if (revealed[x][y])
				 {
					 mapString += (map[x][y] + " ");
				 } 
			 }
			 
			 mapString += "\n";
		 }
		 
		 return mapString;
	}
}
