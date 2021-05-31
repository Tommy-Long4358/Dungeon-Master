/* Class that decorates the Enemy with additional HP and attack damage.
   Extends EnemyDecorator to do the action of "decorating" an Enemy. */
public class Warrior extends EnemyDecorator
{
    public Warrior(Enemy e)
    {
        super(e, e.getName(), e.getMaxHP() + 2);
    }

    public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 3) + 1;
        e.takeDamage(dmg);
        
		return super.attack(e) + "\n" + "hits " + e.getName() + " for " + dmg + " damage.";
    }

    public String getName()
    {
        return super.getName() + " Warrior";
    }
}
