import java.util.ArrayList;
/**
 * Magic chicken. If picked up and kept for x time
 * it will spawn a egg. 
 * 
 * @author Simon Zimmermann & Sindre Ohm
 * @version 24-04-09
 */
public class Chicken extends Item implements Returnable
{
    private int spawntime = 100;
       
    /**
     * Constructor for objects of class Egg
     */
    public Chicken(String name, String description, int duration)
    {      
       super(name, description, duration);
    }
    
    /**
     * Override Foundation Tick method. Here we spawn a Egg.
     */
    protected void tick()
    {   
        tick++;
        if(tick == 20){
            spawntime *= 2; // update spawntime.
            System.out.println("added egg " +  tick);
            addActive(new Item(EGG_NAM, EGG_DES, FOREVER)); // add egg.
        }
    }

    public void use()
    {
        addActive(new Item(EGG_NAM, EGG_DES, FOREVER));
    }
    /**
     * Find out if the chicken has eggs.
     * @return boolean
     */
    public boolean hasReturnItem()
    {
        if(active.isEmpty())
            return false;
        return true;
    }
    
    /**
     * Get egg if the chicken has one.
     * @return   Item Return egg.
     */
    public Foundation getReturnItem()
    {
        Foundation egg = getActive(EGG_NAM); 
        //getActive(EGG_NAM).setDuration(getTick());
        removeActive(egg);
        return egg;
    }
    

}
