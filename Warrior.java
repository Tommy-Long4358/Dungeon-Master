/* Class that decorates the Enemy with a title, additional HP, and attack damage.
   Extends EnemyDecorator to do the action of "decorating" an Enemy. */
public class Warrior extends EnemyDecorator
{
    /**
     * Instantiates warrior constructor.
     * @param e Enemy that is given the decorator.
     */
    public Warrior(Enemy e)
    {
        super(e, e.getName(), e.getMaxHP() + 2);
    }

    /**
     * Enemy's new attack method with increased damage.
     * @param e -  Entity to be attacked by the Enemy.
     * @return String description of attack.
     */
    public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 3) + 1;
        e.takeDamage(dmg);
        
		return super.attack(e) + "\n" + "hits " + e.getName() + " for " + dmg + " damage.";
    }

    /**
     * Getter method for new title of Enemy
     * @return Enemy's new name and title
     */
    public String getName() { return super.getName() + " Warrior"; }
}
