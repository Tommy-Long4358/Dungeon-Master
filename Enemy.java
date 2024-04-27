/** Enemy.java - Represents a type of Entity. */
public abstract class Enemy extends Entity
{
	/** Enemy Constructor - Initializes enemy's name and HP.
	 *  @param n      Enemy's name
	 *  @param mHP  Enemy's max hit points
	 */
	public Enemy(String n, int mHP)
	{
		super(n, mHP);
	}
}
