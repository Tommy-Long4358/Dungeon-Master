/** EnemyDecorator.java - Decorates the Enemy with additional statuses, like warlock or warrior that give the enemy increased HP and dmg.*/
public abstract class EnemyDecorator extends Enemy
{
    private Enemy enemy;

    /** Constructor - Initializes enemy's name and HP.
     *  @param e -  Enemy itself
	 *  @param n -  Enemy's name
	 *  @param h -  Enemy's health
	 */
    public EnemyDecorator(Enemy e, String n, int h)
    {
        super(n, h);
        enemy = e;
    
    }

    /** String method that returns the enemy's attack method
     *  @param e -  Entity to be attacked by the Enemy.
     */
    public String attack(Entity e)
    {        
        return enemy.attack(e);
    }
    

}
