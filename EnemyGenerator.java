/* Creates random enemies hidden in the monster rooms. */
public class EnemyGenerator
{
	/** Randomizes an Enemy to generate.
     *  @param level Current map level.
	 *  @return Enemy object.
	 */
	public Enemy generateEnemy(int level)
	{
        // Initialize Enemy object
        Enemy enemy;
        int enemyDecoratorStack = 1;

        // Randomized enemy selector
		int enemySelection = (int)(Math.random() * 4) + 1;

        // Randomized decorator selector
        int decorator = (int)(Math.random() * 2) + 1;

        // Select random enemy
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

        // Apply EnemyDecorator classes if map level is above 1 for increased difficulty.
        if (level > 1)
        {
            // Applies decorator classes repeatedly to Enemy.
            while (enemyDecoratorStack <= level)
            {
                if (decorator == 1)
                {
                    enemy = new Warlock(enemy);
                }
                else
                {
                    enemy = new Warrior(enemy);
                }

                enemyDecoratorStack += 1;
            }
        }

        return enemy;
	}
}

