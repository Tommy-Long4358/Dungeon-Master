import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Map class that represents the map the hero goes through. */
public class Map
{
	/* Represents types of rooms in maze */
	private char [][] map;
	
	/* Represents truth value of whether maze room has been visited */
	private boolean [][] revealed;
	
	private static Map instance;
	
	/** Private Map Constructor
	 *  Initializes a 5x5 2D array set of characters that represents the different rooms that is displayed in the Map.txt files.
	 *  Initalizes a 5x5 set of booleans that determines if these rooms have been visited or not.
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
	
	/** Reads a map from the .txt files and writes its content to the map char 2D array.
	 *  @param mapNum   Level of the map
	 *  @throws   FileNotFoundException if map file does not exist
	 */
	public void loadMap(int mapNum)
	{
		try
		{
			Scanner read = new Scanner(new File("Map" + mapNum));
			
			String character = "";
			while (read.hasNext())
			{
				
				String line = read.nextLine();
				
				// separates each line in a string array
				String [] chars = line.split(" ");
				
				// Adds each index of chars string array to character.
				for (int i = 0; i < map[0].length; i++)
				{
					character = character + chars[i];	
				}
			}
			
			read.close();
			
			int charPosition = 0;
			for (int x = 0; x < map.length; x++)
			{
				for (int y = 0; y < map[0].length; y++)
				{
					// converts index at character into char.
					char letter = character.charAt(charPosition);
					
					// Assigns char to certain coordinate in map 2D array.
					map[x][y] = letter; 

					// Fills the 2D boolean array with false.
                    revealed[x][y] = false;
					
					charPosition++;
				}
			}
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
	
	/** Point method that finds the start of map.
	 *  @return location of map start
	 */
	public Point findStart()
	{
		Point p = new Point();
		
		for (int x = 0; x < map.length; x++)
		{
			for (int y = 0; y < map[0].length; y++)
			{
				// If X and Y location is equal to 's', return the location and reveal it as true.
				if (map[x][y] == 's')
				{
					int a = x;
					int b = y;
				
					p = new Point(a, b);
                    revealed[x][y] = true;	
				}
			}		
		}
		
		return p;
	}
	
	/** Reveals the character of the location the Hero wants to go to.
	 *  @param P A Point location on the map to be revealed.
	 */
	public void reveal(Point P)
	{
		int x = P.getX();
		int y = P.getY();
		
		revealed[x][y] = true;
	}
	
	/** Removes the character at a location after it has been visited.
	 *  @param P Location of user/hero on map
	 */
	public void removeCharAtLoc(Point P)
	{
		for (int x = 0; x < map.length; x++)
		{
			for (int y = 0; y < map[0].length; y++)
			{
				// Once the hero steps out of the start, the char at the start is converted to 's' to represent the starting location.
				if (map[x][y] == 's')
				{
					map[x][y] = 's';
				}
				// "f" is left on the map if the hero has no keys and can't progress to the next map.
                else if (map[x][y] == 'f')
                {
                    map[x][y] = 'f';
                }
				// For every visited location the hero goes to, its listed as 'n' to show it has been visited.
				else if ((x == P.getX()) && (y == P.getY()))
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
				 else if (revealed[x][y] == false)
				 {
					 mapString += "x ";
				 }
				 
				 // If the char at that location is true, it shows the char at that location ("n")
				 else if (revealed[x][y] == true)
				 {
					 mapString = mapString + map[x][y] + " ";
				 } 
			 }
			 
			 mapString += "\n";
		 }
		 
		 return mapString;
	}
}
