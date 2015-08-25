
/**
 * Write a description of class Quest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quest
{
    private boolean hasPistol;
    private boolean hasChicken;
    private World world;
    
    public Quest()
    {
         hasPistol = false;
         hasChicken = false;
    }
    
    /**
     * Check status of the current quest.
     */
    public boolean check(World world)
    {
        setWorld(world);
        Hero hero = (Hero) getPlayer("Hero");
        
        if(hero.getActive("Chicken") != null && !hasChicken){
            hasChicken = true;
            System.out.println("You got chicken.");
        }
        if(hero.getActive("Spacepistol") != null)
            hasPistol = true;
        
        return false;
    }
    /**
     * Find Player
     * @param name of player
     * @return player
     */
    private Player getPlayer(String name){
        for(Foundation room : world.getActive())
        {
            if(room.getActive(name) != null){
                Foundation f = room.getActive(name);
                return (Player) f;                 
            }
        }
        return null; 
    }
    /**
     * set world
     * @param world
     * */
     public void setWorld(World world)
     {
        this.world = world; 
      }
}
