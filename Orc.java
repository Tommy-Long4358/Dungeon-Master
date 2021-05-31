/* A class that creates a Orc Enemy with a name and HP that is created by the Enemy super class, and an attack method. */
public class Orc extends Enemy
{
    /** Constructor that calls the Enemy class to initalize the Orc's name and HP as an Entity object. */
    public Orc()
    {
        super("Orc", 4);
    }

    /** String method overriden from Entity class that deals damage to an Entity.
     *  @param e - Entity to be attacked by an Orc
     *  @return String of how much dmg a Orc did to the Entity.
     */
    @Override    
	public String attack(Entity e)
    {
        int dmg = (int)(Math.random() * 4);
		
		e.takeDamage(dmg);
		return " hits " + e.getName() + " for " + dmg + " damage."; 
    }
}
