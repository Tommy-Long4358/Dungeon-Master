/* A type of Enemy Entity. */
public class Goblin extends Enemy
{
    /** Constructor that calls the Enemy class to initialize the Goblin's name and HP. */
    public Goblin()
    {
        super("Goblin", 2);
    }

    /** String method overridden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by a Goblin.
     *  @return String representation of how much dmg a Goblin did to the Entity.
     */
    @Override    
	public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 2) + 1;
		
		e.takeDamage(dmg);
		return " hits " + e.getName() + " for " + dmg + " damage."; 
    }
}