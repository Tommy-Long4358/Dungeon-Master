/* A class that creates a Froglok Enemy with a name and HP that is created by the Enemy super class, and an attack method. */
public class Froglok extends Enemy 
{
    /** Constructor that calls the Enemy class to initalize the Froglok's name and HP as an Entity object. */
    public Froglok()
    {
        super("Froglok", 3);
    }

    /** String method overriden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by a Froglok
     *  @return String of how much dmg a Froglok did to the Entity.
     */
    @Override    
	public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 3) + 1;
		
		e.takeDamage(dmg);
		return " hits " + e.getName() + " for " + dmg + " damage."; 
    }
}
