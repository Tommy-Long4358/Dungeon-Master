/* A class that creates an Enemy Entity type. */
public class Troll extends Enemy
{
    /** Constructor that calls the Enemy class to initialize the Troll's name and HP as an Entity object. */
    public Troll()
    {
        super("Troll", 5);
    }

    /** String method overridden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by a Troll
     *  @return String of how much dmg a Troll did to the Entity.
     */
    @Override    
	public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 5);
		
		e.takeDamage(dmg);
		return " hits " + e.getName() + " for " + dmg + " damage."; 
    }
}
