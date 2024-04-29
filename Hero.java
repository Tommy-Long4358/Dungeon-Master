import java.awt.Point;

/* Hero.java - Describes a character that represents the user. */
public class Hero extends Entity implements Magical
{
	/* The user's current location */
	private Point loc;
	
	/* The map level the user is currently on */
	private int level;
	
	/* The user's current gold amount */
	private int gold;
	
	/* The user's current number of keys */
	private int key;
	
	/** Constructor - Initializes hero with a name, starting inventory, and starting location.
	 *  @param n  Name of user
	 */
	public Hero(String n)
	{
		super(n, 25);
		level = 1;
        gold = 0;
		key = 0;

		// Load map
        Map.getInstance().loadMap(level);

        loc = Map.getInstance().findStart();
	}
	
	/** Increments user's level and loads next map. */
	public void levelUp()
	{
		level += 1;
		
		if ((level % 3) != 0)
		{
			level = level % 3;
            Map.getInstance().loadMap(level);
		}
		else
		{
            Map.getInstance().loadMap(3);    
		}
	}

	/** Gets the current level of the user.
	 *	@return current level of the user.
	 */
	public int getLevel()
	{
		return level;
	}

	/** Gets the current location of the user.
	 *	@return current location of the user.
	 */
	public Point getLoc()
	{
		return loc;
	}
	
	/** Moves the user to the upper side of the map.
	 *  @return The user's new location or "x" if out of bounds.
	 */
	public char goNorth()
	{
		// Get location of user
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		// New point that represents going north.
		Point p = new Point(x - 1, y);
		
		// Check if new point is out of bounds of the map
		if (p.getX() >= 0)
		{
			// Set user's location to new location
			loc = p;

			// Get char at new location
            return Map.getInstance().getCharAtLoc(loc); 
		}

		// New point is out of bounds; return 'x'
		return 'x';
	}
	
	/** Moves the user to the bottom side of the map.
	 *  @return The user's new location or "x" if out of bounds.
	 */
	public char goSouth()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x + 1, y);
		
		if (p.getX() <= 5)
		{
			loc = p;
            return Map.getInstance().getCharAtLoc(loc);
		}
	
		return 'x';
	}
	
	
	/** Moves the user to the right side of the map.
	 *  @return The hero's new location or "x" if out of bounds.
	 */
	public char goEast()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x, y + 1);
		
		if (p.getY() <= 5)
		{
			loc = p;
            return Map.getInstance().getCharAtLoc(loc);
        }
	
		return 'x';
	}
	
	
	/** Moves the user to the left side of the map.
	 *  @return The hero's new location or "x" if out of bounds.
	 */
	public char goWest()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x, y - 1);
		
		if (p.getY() >= 0)
		{
			loc = p;
            return Map.getInstance().getCharAtLoc(loc);
		}
		
		return 'x';
	}
	
	/** Gets the user's current amount of gold.
	 *  @return user's current amount of gold.
	 */
	public int getGold()
	{
		return gold;
	}
	
	/** Increments user's gold amount.
	 *  @param g amount of gold the user obtained.
	 */
	public void collectGold(int g)
	{
		gold += g;
	}
	
	/** Decreases the user's gold amount
	 *  @param g  the amount of gold the user spent.
	 */
	public void spendGold(int g)
	{
		if (gold - g < 0)
		{
			gold = 0;
		}
		else
		{
			gold -= g;
		}
	}
	
	/** Checks whether the user has a key.
	 *  @return  false if the user has no keys and true if the user has at least 1 key.
	 */
	public boolean hasKey()
	{
		return key > 0;
	}
	
	/** Increments the amount of keys the user has. */
	public void pickUpKey()
	{
		key += 1;
	}
	
	/** Boolean method that uses a key from the user's key amount.
	 *  @return true if a key has been used or false if it hasn't been used.
	 */
	public boolean useKey()
	{
		if (key > 0)
		{
			key -= 1;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/** Allows the user to use an attack with a random damage output. This is a normal attack.
	 * @param e  Entity object being attacked.
	 * @return String representation of physical damage.
	 */
	@Override
	public String attack(Entity e)
	{
		int physicalDmg = (int)(Math.random() * 3) + 1;
		e.takeDamage(physicalDmg);

		return super.getName() + " hits " + e.getName() + " for " + physicalDmg + " damage.";
	}

	/**  Allows the user to use an attack with a random damage output. This is a magical attack.
	 *   @param e Entity being hit by Magic missile.
	 *   @return  String representation of magical damage.
	 */
	@Override
	public String magicMissle(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);

		return super.getName() + " shoots " + e.getName() + " for " + dmg + " damage.";
	}
	
	
    /** Allows the user to use an attack with a random damage output. This is a magical attack.
	 *  @param e  Entity being hit by Fireball.
	 *  @return  String representation of magical damage.
	 */
	@Override
	public String fireball(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);

		return super.getName() + " throws a fireball at " + e.getName() + " for " + dmg + " damage.";
	}
	
	
	/** Allows the user to use an attack with a random damage output. This is a magical attack.
	 *  @param e Entity being hit by Thunderclap.
	 *  @return  String representation of magical damage.
	 */  
	@Override
	public String thunderclap(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);
		
		return super.getName() + " zaps " + e.getName() + " for " + dmg + " damage.";
	}

	/** toString method that displays the user's name, hp, level, gold, and map.
	 *  @return String that contains the user's name, hp, level, gold, and map
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n" + "Level: " + level + "\n" + "Gold: " + gold + "\n" + Map.getInstance().mapToString(loc);
	}
	
}
