import java.awt.Point;

/* Hero.java - Describes a character that represents the user. */
public class Hero extends Entity implements Magical
{
	/* Represents the hero's current location */
	private Point loc;
	
	/* Represents the hero's map level */
	private int level;
	
	/* Represents the hero's current gold amount */
	private int gold;
	
	/* Represents the hero's current number of keys the hero owns */
	private int key;
	
	/** Constructor - Initializes hero with a name, 25 HP, 1 level, 0 gold, 0 keys, and loads the current map the Hero is in and its current location.
	 *  @param n  Name of hero
	 */
	public Hero(String n)
	{
		super(n, 25);
		level = 1;
        gold = 0;
		key = 0;
        
        Map.getInstance().loadMap(level);
        loc = Map.getInstance().findStart();
        
	}
	
	/** Increments hero's level and loads next map. */
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

	/** Gets the current level of the Hero. 
	 *	@return current level of the Hero.
	 */
	public int getLevel()
	{
		return level;
	}

	/** Gets the current location of the Hero. 
	 *	@return current location of the Hero.
	 */
	public Point getLoc()
	{
		return loc;
	}
	
	/** Allows user to move north in the map.
	 *  @return The hero's new location or "x" if out of bounds.
	 */
	public char goNorth()
	{
		// Get the X and Y coordinate from the point class getter methods that was imported.
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		// New point that represents going North.
		Point p = new Point(x - 1, y);
		
		// If the new point coordinates are in the range of the map, the hero's new location is set to "p".
		if ( ((p.getX() > -1) && (p.getX() < 5)) && ((p.getY() > -1) && (p.getY() < 5)) )
		{
			loc = p;
            
            return Map.getInstance().getCharAtLoc(loc); 
		}
		
		return 'x';
	}
	
	/** Allows user to move south in the map.
	 *  @return The hero's new location or "x" if out of bounds
	 */
	public char goSouth()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x + 1, y);
		
		if ( ((p.getX() > -1) && (p.getX() < 5)) && ((p.getY() > -1) && (p.getY() < 5)) )
		{
			loc = p;
			
            return  Map.getInstance().getCharAtLoc(loc);
		}
	
		return 'x';
	}
	
	
	/** Allows user to move east in the map.
	 *  @return The hero's new location or "x" if out of bounds
	 */
	public char goEast()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x, y + 1);
		
		if ( ((p.getX() > -1) && (p.getX() < 5)) && ((p.getY() > -1) && (p.getY() < 5)) )
		{
			loc = p;
			
            return  Map.getInstance().getCharAtLoc(loc);
        }
	
		return 'x';
	}
	
	
	/** Allows user to move west in the map.
	 *  @return The hero's new location or "x" if out of bounds
	 */
	public char goWest()
	{
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		Point p = new Point(x, y - 1);
		
		if ( ((p.getX() > -1) && (p.getX() < 5)) && ((p.getY() > -1) && (p.getY() < 5)) )
		{
			loc = p;
			
            return Map.getInstance().getCharAtLoc(loc);
		}
		
		return 'x';
	}
	
	/** Gets the hero's current amount of gold.
	 *  @return hero's current amount of gold
	 */
	public int getGold()
	{
		return gold;
	}
	
	/** Increments the amount of gold the hero has by its parameter after the hero has slain an enemy.
	 *  @param  the amount of gold the hero got from slaying an enemy 
	 */
	public void collectGold(int g)
	{
		gold += g;
	}
	
	/** Decreases the amount of gold the hero has by its parameter after the hero has bought something from the Store method in Main.java.
	 *  @param g  the amount of gold the hero has spent in the store.
	 */
	public void spendGold(int g)
	{
		gold -= g;
	
	}
	
	/** Boolean method that determines whether the hero has a key or not.
	 *  @return  false if the hero has no keys and true if the hero has at least 1 key.
	 */
	public boolean hasKey()
	{
		if (key <= 0)
		{
			return false;
		}
		
		return true;
	}
	
	/** Increments the number of keys the hero owns to open doors that go to the next map and level. */
	public void pickUpKey()
	{
		key += 1;
	}
	
	/** Boolean method that uses a key from the hero's collection of keys.
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
	
	/** Allows the hero to use an attack with a random damage output. This is for a normal attack.
	 * @param e  Entity being attacked
	 * @return String representation of physical damage
	 */
	@Override
	public String attack(Entity e)
	{
		int physicalDmg = (int)(Math.random() * 3) + 1;
		e.takeDamage(physicalDmg);

		return super.getName() + " hits " + e.getName() + " for " + physicalDmg + " damage.";
	}

	/**  Magic missile attack method with random damage range. This is a magical attack.
	 *   @param e Entity being hit by Magic missile
	 *   @return  String representation of magical damage
	 */
	@Override
	public String magicMissle(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);

		return super.getName() + " shoots " + e.getName() + " for " + dmg + " damage.";
	}
	
	
    /** Fireball attack method with random damage range. This is a magical attack.
	 *  @param e  Entity being hit by Fireball
	 *  @return  String representation of magical damage
	 */
	@Override
	public String fireball(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);

		return super.getName() + " throws a fireball at " + e.getName() + " for " + dmg + " damage.";
	}
	
	
	/** Thunder Clap attack method with random damage range. This is a magical attack.
	 *  @param e Entity being hit by Thunderclap
	 *  @return  String representation of magical damage
	 */  
	@Override
	public String thunderclap(Entity e)
	{
		int dmg = (int)(Math.random() * 4) + 1;
		e.takeDamage(dmg);
		
		return super.getName() + " zaps " + e.getName() + " for " + dmg + " damage.";
	}

	/** toString method that displays the hero's name, hp, level, gold, and map.
	 *  @return Displays hero's name, hp, level, gold, and map 
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n" + "Level: " + level + "\n" + "Gold: " + gold + "\n" + Map.getInstance().mapToString(loc);
	}
	
}
