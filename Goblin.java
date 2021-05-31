/* A class that creates a Goblin Enemy with a name and HP that is created by the Enemy super class, and an attack method. */
public class Goblin extends Enemy
{
    /* Constructor that calls the Enemy class to initalize the Goblin's name and HP as an Entity object. */
    public Goblin()
    {
        super("Goblin", 2);
    
    }

    /** String method overriden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by a Goblin
     *  @return String of how much dmg a Goblin did to the Entity.
     */
    @Override    
	public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 2) + 1;
		
		e.takeDamage(dmg);
		return " hits " + e.getName() + " for " + dmg + " damage."; 
    }

    
}