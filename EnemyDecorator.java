/** Decorator design pattern class that decorates the Enemy with additional statuses,
 *  like warlock or warrior and increased HP and damage.*/
public abstract class EnemyDecorator extends Enemy
{
    private Enemy enemy;

    /** Initializes enemy's name and HP.
     *  @param e Enemy itself
	 *  @param n -  Enemy's name
	 *  @param h -  Enemy's health
	 */
    public EnemyDecorator(Enemy e, String n, int h)
    {
        super(n, h);
        enemy = e;
    
    }

    /** String method that returns the enemy's attack method
     *  @param e Entity to be attacked by the Enemy.
     */
    public String attack(Entity e)
    {        
        return enemy.attack(e);
    }
}