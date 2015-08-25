
/**
 * Write a description of class Parent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parent
{
    // instance variables - replace the example below with your own
    protected int duration;
    protected int tick;
    private String sirname;

    /**
     * Constructor for objects of class Parent
     */
    public Parent(int duration, String sirname)
    {
        this.duration = duration;
        this.sirname = sirname;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @return     the age of a person
     */
    private int getDuration()
    {
        return duration;
    }
    
    /**
     * return the sirname
     */
    protected String getSirname()
    {
        return sirname;
    }
    
    /**
     * Tick the object
     */
    public void tick()
    {
        tick++;
    }
    
    /**
     * Ended its life.
     */
    public boolean hasExpired()
    {
        if(tick > duration)
            return true;
        return false;
    }
}
