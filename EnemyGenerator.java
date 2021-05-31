
/* EnemyGenerator.java - Creates random enemies to encounter in the maze. */
public class EnemyGenerator
{
	
	/** Uses RNG to determine what type of enemy is being encountered in the monsterRoom method in Main.java and the decoration it has on the monster only if the current level of the map is above 1.
	 *  @return Generated enemy
	 */
	public Enemy generateEnemy(int level)
	{
        Enemy enemy;
        int enemyDecoratorStack = 1;
		int enemySelection = (int)(Math.random() * 4) + 1;
        int decorator = (int)(Math.random() * 2) + 1;

        if (enemySelection == 1)
        {
            enemy = new Orc();
        }
        else if (enemySelection == 2)
        {
            enemy = new Froglok();
        }
        else if (enemySelection == 3)
        {
            enemy = new Troll();
        }   
        else
        {
            enemy = new Goblin();
        }

        if (level > 1)
        {
            if (decorator == 1)
            {
                while (enemyDecoratorStack < level)
                {
                    enemy = new Warlock(enemy);
                    
                    enemyDecoratorStack += 1;
                }
            }
            else
            {
                while (enemyDecoratorStack < level)
                {
                    enemy = new Warrior(enemy);
                    
                    enemyDecoratorStack += 1;
                }
            }

        }

        return enemy;
	}
}

