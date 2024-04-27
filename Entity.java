/** Entity.java - Describes a character in the game. */
public abstract class Entity
{
	/* Represents characters name. */
	private final String name;
	
	/* Represents the character's max HP. */
	private final int maxHp;
	
	/* Represents the character's current hp. */
	private int hp;
	
    /** Constructor - Initializes an Entity with name and max HP.
     *  @param n -   Name of constructed entity
     *  @param mHp -  Max hit points of constructed entity
     */
	public Entity (String n, int mHp)
	{
		name = n;
		maxHp = mHp;
		hp = maxHp;
	}
	
	/** Gets the entity's name.
     *  @return Name of entity
     */
	public String getName()
	{
		return name;
	}
	
	/** Gets the entity's HP.
     *  @return Entity's HP
     */
	public int getHP()
	{
		return hp;
	}
	
	/** Gets entity's max HP.
	 *  @return Entity's max HP
	 */
	public int getMaxHP()
	{
		return maxHp;
	}
	
	/** Adds HP to the entity's current HP.
     *  @param h  - Amount of hit points to heal by
     */
	public void heal(int h) 
	{
		if ((hp + h) > maxHp)
		{
			hp = maxHp;
		}
		else
		{
			hp += h;
		}
	}
	
	/** Subtracts HP from the entity's current HP
     *  @param d - Amount of HP to decrease the entity's current HP by.
     */
	public void takeDamage(int d)
	{
		// if condition for if the HP goes below 0 HP.
		if ((hp - d) < 0)
		{
			hp = 0;
		}
		else
		{
			hp -= d;
		}
	}
	
	/** Abstract Method that allows an entity to attack another entity
     *  @param e - Entity being attacked
     */
	public abstract String attack(Entity e);
	
	/** Displays entity's name and hp over maxHp
     *  @return String representation of entity's name and current hit points
     */
	@Override
	public String toString()
	{
		return getName() + "\n" + "HP: " +  getHP() + "/" + maxHp;
	}
}