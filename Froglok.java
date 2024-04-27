/* A class that creates an Enemy Entity. */
public class Froglok extends Enemy 
{
    /** Constructor that calls the Enemy class to initialize the Froglok's name and HP. */
    public Froglok()
    {
        super("Froglok", 3);
    }

    /** String method overridden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by a Froglok.
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
