/* Class that decorates the Enemy with additional HP and attack damage.
   Extends EnemyDecorator to do the action of "decorating" an Enemy.
   Implements Magical to access magical spells. */
public class Warlock extends EnemyDecorator implements Magical
{
	/* Constructor that calls on the EnemyDecorator super class to initalize it with its title of "Warlock" and increase its max HP by 1.
	*/
    public Warlock(Enemy e)
    {
        super(e, e.getName(), e.getMaxHP() + 1);
    }

    /** String method that randomizes a spell to be used and is stacked along with the physical attack.
	 * @param e  Entity to be attacked
	 * @return  String of the attacks done
	 */
    public String attack(Entity e)
    {
        int selection = (int)(Math.random() * 3) + 1;

        if (selection == 1)
        {
            return super.attack(e) + "\n" + magicMissle(e);
        }
        else if (selection == 2)
        {
            return super.attack(e) + "\n" + fireball(e);
        }
        else
        {
            return super.attack(e) + "\n" + thunderclap(e);
        }
    }

	/** String method that returns the name of the Enemy with its title of "Warlock".
	 *  @return Enemy title with "Warlock"
	 */
    public String getName()
    {
        return super.getName() + " Warlock";
    }

    /**  Magic missile attack method with random damage range
	 *   @param e Entity being hit by Magic missile
	 *   @return  String representation of magical damage
	 */
	@Override
	public String magicMissle(Entity e)
	{
		int dmg = (int)(Math.random() * 2);
		e.takeDamage(dmg);

		return "shoots " + e.getName() + " for " + dmg  + " damage.";
	}
	
    /** Fireball attack method with random damage range.
	 *  @param e Entity being hit by Fireball
	 *  @return  String representation of magical damage
	 */
	@Override
	public String fireball(Entity e)
	{
        
		int dmg = (int)(Math.random() * 3);
		e.takeDamage(dmg);
		
		return "throws a fireball at " + e.getName() + " for " + dmg  + " damage.";
        
	}
	
	
	/** Thunderclap attack method with random damage range.
	 *  @param e Entity being hit by Thunderclap
	 *  @return  String representation of magical damage
	 */  
	@Override
	public String thunderclap(Entity e)
	{
		int dmg = (int)(Math.random() * 3) + 1;
		e.takeDamage(dmg);
		
        return "zaps " + e.getName() + " for " + dmg  + " damage.";
	}
}