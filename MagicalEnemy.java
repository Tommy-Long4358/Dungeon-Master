/** Represents a special type of Enemy Entity.
 *  Extends Enemy to gain access to the name and HP stats and methods. 
 *  Implements Magical to use magical attacks.
 * */
public class MagicalEnemy extends Enemy implements Magical
{
	/** Initializes a magical enemy's name and max HP.
	 *  @param n    Magical enemy's name.
	 *  @param mHp  Magical enemy's max HP.
	 */
	public MagicalEnemy(String n, int mHp)
	{
		super(n, mHp);
	}
	
	/** Attack method for magical enemy that randomly chooses which spell to cast on the Enemy.
	 *  @param e  Entity being attacked.
	 *  @return Randomly chosen magic attack.
	 */
	@Override
	public String attack(Entity e)
	{
		int spellRandomizer = (int)(Math.random() * 3) + 1;
		
		if (spellRandomizer == 1)
		{
			return super.getName() + " shoots " + e.getName() + " for " + magicMissle(e) + " damage.";
		}
		else if (spellRandomizer == 2)
		{
			return super.getName() + " throws fireball at " + e.getName() + " for " + fireball(e) + " damage.";
		}
		else
		{
			return super.getName() + " zaps " + e.getName() + " for " + thunderclap(e) + " damage.";
		}
	}
	
	/** Magic Missile attack method with a random damage range.
	 *  @param e  Entity being attacked.
	 *  @return String value of the dmg being done on the Entity.
	 */
	@Override
	public String magicMissle(Entity e)
	{
		int dmg = (int)(Math.random() * 7) + 4;
		e.takeDamage(dmg);
		
		return String.valueOf(dmg);
	}
	
	/** Fireball attack method with a random damage range.
	 *  @param e  Entity being attacked.
	 *  @return String value of the dmg being done on the Entity.
	 */
	@Override
	public String fireball(Entity e)
	{
		int dmg = (int)(Math.random() * 7) + 4;
		e.takeDamage(dmg);
		
		return String.valueOf(dmg);
	}

	/** Thunderclap attack method with a random damage range.
	 *  @param e  Entity being attacked.
	 *  @return String value of the dmg being done on the Entity.
	 */
	@Override
	public String thunderclap(Entity e)
	{
		int dmg = (int)(Math.random() * 7) + 4;
		e.takeDamage(dmg);
		
		return String.valueOf(dmg);
	}
}