/**
 * Abstract structure for our Players. 
 * 
 * @author Simon Zimmermann 
 * @version 14-04-09
 */
public abstract class Player extends Foundation
{
    private int hitpoints; // A number indicating health.
    private int attack; // A number used for calculating fight.
    protected Room room; // location of the player.
    
    /**
     * Set the vars and inizialise the arraylist.
     * @param name The name of what we want to create.
     * @param description The description of what we want to create.
     * @param duration The duration of the player.
     * @param hitpoints The hitpoints of our character.
     * @param attack The attack of our character.
     * @param room The location of a player
     */
    public Player(String name, String description, int duration,
                  int hitpoints, int attack, Room room)
    {
        super(name, description, duration);
        this.hitpoints = hitpoints;
        this.attack = attack;
        this.room = room;
    }
    
    /**
     * @return Hitpoints.
     */
    public int getHitpoints()
    {
        return hitpoints;
    }
    
    /**
     * @param set hitpoints.
     */
    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }
    
    /**
     * @return attack. 
     */
    public int getAttack()
    {
        return attack;
    }
    
    /**
     * Get location.
     * @return the location of a player.
     */
    public Room getRoom()
    {
        return room;
    }
}