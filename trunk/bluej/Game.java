
/**
 * This is our Game class. It will have the Gameloop which controls
 * the internal speed of our game. 
 * 
 * @author Simon Zimmermann 
 * @version 16-04-09
 */
public class Game
{
    private World world;
    private Quest quest;
    
    public Game()
    {
        world = new World("The Space Game", "A textbased adventure game", 100000000);
        quest = new Quest();
    }

    public void play()
    {
        boolean finish = false;
        System.out.println("Welcome to " + world.getName() + " - " + world.getDescription() + "\n\n");
        
        while(!finish)
        {
            world.tick();
            world.tickActive();
            world.removeExpired();
            quest.check(world);
            
            try { Thread.sleep(40); } catch (Exception e) {}
        }
    }
}