import java.awt.Point;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("What is your name? ");
		
		String name = input.nextLine();
		
		Hero h1 = new Hero(name);
		Map map = Map.getInstance();
		EnemyGenerator e1 = new EnemyGenerator();

		boolean alive = true;
		int direction;
		char direct = ' '; 
       
		while (alive)
		{
            System.out.println(h1);
			System.out.println("1. Go North (up)");
			System.out.println("2. Go South (down)");
			System.out.println("3. Go East (right)");
			System.out.println("4. Go West (left)");
			System.out.println("5. Quit");
			
			// Test for valid input.
			while (true)
			{
				if (input.hasNextInt())
				{
					direction = input.nextInt();
					
					if (direction > 0 && direction < 6)
					{
						break;
					}
				}
				else
				{
					input.nextLine();
				}
			}
			
			if (direction == 1)
			{
                
				direct = h1.goNorth();
				
				
			}
			else if (direction == 2)
			{
				direct = h1.goSouth();

			}
			else if (direction == 3)
			{
				
				direct = h1.goEast();
				
			}
			else if(direction == 4)
			{
				direct = h1.goWest();
			
			}
			else if (direction == 5)
			{
				System.out.println("Game over");
				break;
				
			}
            
			// Reveal the current location on what character it is.
            map.reveal(h1.getLoc());

			if (direct == 'm')
			{
				alive = monsterRoom(h1, e1.generateEnemy(h1.getLevel()));
			}
			else if (direct == 'i')
			{
				int itemDrop = (int)(Math.random() * 10) + 1;
				
				if (itemDrop == 46)
				{
					System.out.println("You found a key!");
					h1.pickUpKey();
				}
				else
				{
					System.out.println("You found a Health Potion! You drink it to restore your health.");
					h1.heal(25);
				}

				// After an item has been used, it removes the character, turns it to "n", meaning nothing is there anymore to prevent running into the same item space twice.
                map.removeCharAtLoc(h1.getLoc());
                map.reveal(h1.getLoc());
                
			}
			else if (direct == 's')
			{
                System.out.println("You are back at the start. ");
				Store(h1);
				
			}
			else if (direct == 'n')
			{
				
				System.out.println("There was nothing here.");
			}
			else if(direct == 'f')
			{
				if (h1.hasKey() == true)
				{
					System.out.println("You found the exit! Proceeding to the next level.");
                    
                    h1.useKey();
					h1.levelUp();
                    
					// Reveal the new location on the new map.
                    map.reveal(h1.getLoc());
				}
				else
				{
					System.out.println("You don't have a key! Go to a store to buy one or find one throughout the map!");
				}
			}
            else 
            {
                System.out.println("Out of bounds! Try again.");
            }
		}
	}

	/** Repeats until monster is dead or hero runs away 
	 * Gives the option of fighting or running away
	 * @param h User/Hero
	 * @param e Monster enemy
	 * @return True if hero is still alive or false if the hero died.
	 */
	public static boolean monsterRoom(Hero h, Enemy el)
	{
		Scanner input = new Scanner(System.in);
		Map map = Map.getInstance();
	
		System.out.println("You've encountered a " + el.getName());
		
		boolean alive = true;
		
		while (alive)
		{
			System.out.println(el);
			
			System.out.println("1. Fight\n2. Run Away");
			
			int decision;

			while (true)
			{
				if (input.hasNextInt())
				{
					decision = input.nextInt();
					
					if (decision > 0 && decision < 3)
					{
						break;
					}
				}
				else
				{
					input.nextLine();
				}
			}
			
			if (decision == 1)
			{
				// call fight method if the decision to fight
				// return true if alive, false if dead
				alive = fight(h, el);
				
				// if statement for if the hero lives the encounter 
				if (el.getHP() == 0)
				{
					map.removeCharAtLoc(h.getLoc());
					break;
				
				}	
			}
			else 
			{
				System.out.println("You ran away to a random location!");
				
				int direction = (int)(Math.random() * 4) + 1;
				char path = ' ';
				
				while (true)
				{
					if (direction == 1)
					{
						
						path = h.goNorth();
						
					}
					else if (direction == 2)
					{
						
						path = h.goSouth();
						
					}
					else if (direction == 3)
					{
						
						path = h.goEast();
						
					}
					else if (direction == 4)
					{
						
						path = h.goWest();
						
					}

					// if the hero goes out of bounds, it randomizes another direction to go to.
					if (path == 'x')
					{
						direction = (int)(Math.random() * 4) + 1;
                        
					}
					else
					{
						break;
					}

				}
				
				// if a valid direction is found, if statements to see what is there
				if (path == 'm')
				{	
					EnemyGenerator e = new EnemyGenerator();
					alive = monsterRoom(h, e.generateEnemy(h.getLevel()));
					break;
				}
				else if (path == 'i')
				{
					
                    int itemDrop = (int)(Math.random() * 10) + 1;
				
                    if (itemDrop == 46)
                    {
                        System.out.println("You found a key!");
                        h.pickUpKey();
                    }
                    else
                    {
                        System.out.println("You found a Health Potion! You drink it to restore your health.");
                        h.heal(25);
                    }
                    
                    map.removeCharAtLoc(h.getLoc());
                    map.reveal(h.getLoc());
                    break;
				}
				
				else if (path == 'n')
				{
					
					System.out.println("There was nothing here");
					break;
				}
				else if(path == 'f')
				{
					if (h.hasKey() == true)
                    {
                        System.out.println("You found the exit! Proceeding to the next level.");
                        
                        h.levelUp();
                        map.loadMap(h.getLevel());
                        
                        map.reveal(h.getLoc());

                    }
                    else
                    {
                        System.out.println("You don't have a key! Go to a store to buy one or find one throughout the map!");
                    }
				}
				else
				{
					System.out.println("Back at start!");
                    Store(h);
					break;
				}
					
			}
		
		}
		
		return alive;
	}
	
	/** Fight method that has the hero and enemy fight in a duel.
	 * @param h   The hero 
	 * @param e   The enemy
	 * @return true if the hero is alive or won the battle or false if the hero is dead.
	 */
	public static boolean fight(Hero h, Enemy e)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("1. Physical Attack \n2. Magical Attack");
		
		int attack;

		while (true)
		{
			if (input.hasNextInt())
			{
				attack = input.nextInt();
				
				if (attack > 0 && attack < 3)
				{
					break;
				}
			}
			else
			{
				input.nextLine();
			}
		}
		
		
		if (attack == 1)
		{
			// hero attacks normally
			System.out.println(h.attack(e));
		}
		else
		{
			System.out.println(h.MAGIC_MENU);
			
			while (true)
			{
				if (input.hasNextInt())
				{
					attack = input.nextInt();
					
					if (attack > 0 && attack < 4)
					{
						break;
					}
				}
				else
				{
					input.nextLine();
				}
			}
			
			// Spells depending on input.
			if (attack == 1)
			{
				System.out.println(h.magicMissle(e));
			}
			else if(attack == 2)
			{
				System.out.println(h.fireball(e));
				
			}
			else
			{
				System.out.println(h.thunderclap(e));
			}
		}
		
		// If enemy hp is zero, you win and it returns true.
		if (e.getHP() == 0)
		{
			System.out.println("You defeated " + e.getName() + "!");
			int goldDrop = (int)(Math.random() * 8) + 5;

			System.out.println("You collected " + goldDrop + " gold!");

			h.collectGold(goldDrop);

			return true;
		}
		
		System.out.println(e.getName() + e.attack(h));
		
		// if hero's hp is zero, you lose and it returns false to show you are dead
		if (h.getHP() == 0)
		{
			System.out.println("You were beaten by the " + e.getName() + ". Game Over.");
			return false;
		}
		
		// return true if no one has died yet and that the fight is still ongoing
		return true;
		
	}
	
	/** Store method that the hero can access to buy items with their gold.
	 * @param h   The hero 
	 */
	public static void Store(Hero h)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the store! What would you like to do?");
		System.out.println("1. Buy\n2. Exit");
		
		
		int selection;

		
		while (true)
		{
			if (input.hasNextInt())
			{
				selection = input.nextInt();
				
				if (selection > 0 && selection < 3)
				{
					break;
				}
			}
			else
			{
				input.nextLine();
			}
		}
		
		while (selection == 1)
		{
			System.out.println("What would you like to buy?");
			System.out.println("1. Health Potions: 25 Gold\n2. Key: 50 Gold");
			System.out.println("Amount: " + h.getGold());
			
			
			while (true)
			{
				if (input.hasNextInt())
				{
					selection = input.nextInt();
					
					if (selection > 0 && selection < 3)
					{
						break;
					}
				}
				else
				{
					input.nextLine();
				}
			}

			// Selection for potions.
			if (selection == 1)
			{
				if (h.getGold() >= 25)
				{
					h.spendGold(25);
					System.out.println("You got a potion! Using it now...");

					// Potions are used immediately.
					h.heal(25);
				}
				else
				{
					// Else statement happens when the hero doesn't have enough gold to spend and it immediately exits them out of the store.
					System.out.println("Not enough gold! Come back when you have gold.");
					break;
				}
				
			}

			// Selection for exit keys.
			else if (selection == 2)
			{
				
				if (h.getGold() >= 50)
				{
					h.spendGold(50);
					System.out.println("You got a key!");
					h.pickUpKey();
				}
				else
				{
					System.out.println("Not enough gold! Come back when you have gold.");
					break;
				}
				
			}

			
			System.out.println("Amount: " + h.getGold());
			System.out.println("1. Buy again\n2. Quit");
			
			while (true)
			{
				if (input.hasNextInt())
				{
					selection = input.nextInt();
					
					if (selection > 0 && selection < 3)
					{
						break;
					}
				}
				else
				{
					input.nextLine();
				}
			}
			
			
		}
		
	}

	

}
