/** Interface for magical attacks */
public interface Magical
{
	/* String menu for magical attack types */
	static final String MAGIC_MENU = "1. Magic Missle\n2. Fireball\n3. Thunderclap";
	
	
	/** Method for magic missile attack. 
	 * @param e  Entity being attacked
	 */
	public String magicMissle(Entity e);

	/** Method for fireball attack. 
	 * @param e  Entity being attacked
	 */
	public String fireball(Entity e);
			
	/** Method for thunderclap attack 
	 * @param e  Entity being attacked
	 */  
	public String thunderclap(Entity e);
	
}
