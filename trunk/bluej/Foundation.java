import java.util.ArrayList;
import java.util.Iterator;

/**
 * Foundation of all classes. We need a description and name.
 * The purpose of this class is to maximize reuse of common tasks.
 * 
 * @author Simon Zimmermann (inpired by Even AAby Larsen & Alexander Teinum).
 * @version 24-04-09
 */
public abstract class Foundation
{  
    private String name; // Name of an object.
    private String description; // Description of an object.
    private int duration;  // stores how long an item is supposed to live.
    protected int tick; // keeps track of how long the object has been alive.
    public final int FOREVER = 100000000; // a number used to simulate time.

    protected ArrayList<Foundation> active; // A list of all active objects.
    private ArrayList<Foundation> returnList; // temp list of items returned to this room.
    protected ArrayList<Foundation> removeList; // temp remove List.
    
    /**
     * Construct name & description on creation.
     * @param name The name of what we want to create.
     * @param description The description of what we want to create.
     * @param duration The duration of an object. Time to live.
     */
    public Foundation(String name, String description, int duration)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        
        active = new ArrayList<Foundation>();
        returnList = new ArrayList<Foundation>(); 
        removeList = new ArrayList<Foundation>();
    }
    
    /**
     * Tick all active objects.
     */
    public void tickActive()
    {
       Iterator<Foundation> a = active.iterator();
       while(a.hasNext()){
           Foundation f = a.next();
           f.tick();
           f.tickActive();
           f.removeExpired();
       }
    }
    
    /**
     * Tick an object. This will happen to all objects on 
     * each gameloop. Tick counts and is the game-clock.
     */
    protected void tick()
    {
        tick++;
    }
    
    /**
     * Add active item to the game.
     */
    protected void addActive(Foundation a)
    {
        active.add(a);
    }
    
    /**
     * Add active ArrayList
     */
    protected void addActive(ArrayList<Foundation> adds)
    {
        active.addAll(adds);
    }
    
    /**
     * Remove active from list
     */
    protected void removeActive(Foundation f)
    {
        active.remove(f);
    }
    
    /**
     * Finds an item based on name
     * @param name Name thing we want. CaseInsensetive.
     * @return  object of any type in game foundation.
     */
    protected Foundation getActive(String name)
    {
        Iterator<Foundation> a = active.iterator();
        while(a.hasNext()){
            Foundation that = a.next();
            if(that.getName().toLowerCase().equals(name.toLowerCase()))
                return that;              
        }
        return null;
    }
    
    /**
     * Returns all active items as ArrayList
     * @return ArrayList of active obj.
     */
    protected ArrayList<Foundation> getActive()
    {
        return active;
    }
    /**
     * Find out if an object is expired. Time to live is zero.
     */
    protected boolean hasExpired()
    {
        if(getTick() > getDuration())
            return true;
        return false;
    }
    
   /**
     * Remove Expired objects. 
     */
    protected void removeExpired()
    {
        Iterator<Foundation> a = active.iterator();
        Iterator<Foundation> b = removeList.iterator();
        
        while(a.hasNext()){
            Foundation that = a.next();
            if(that.hasExpired()){
                System.out.println("Removing " + that.getName());
                a.remove();
            } 
            while(b.hasNext())
            {
                if(b.next().getName().equals(that.getName()));
                    a.remove();
            }
        }
        removeList.clear();
    } 
    
    /**
     * Returns an object returned from a child object.
     * This is due to childs do not know about where they are.
     * Added this to Foundation class even though not every item
     * will need it. At least two do. Maybe more in future.
     * 
     * @param f Is the object to which may have something to return.
     * @return may return any object of type Foundation.
     */
    protected void addReturnable(Foundation f)
    {
       if(f instanceof Returnable){
            Returnable r = (Returnable) f;
            if (r.hasReturnItem()){
                returnList.add(r.getReturnItem());                
            }
        }
    } 

    /**
     * returnList is a list of objects returned to its parent
     * @return array list of objects
     */
    protected ArrayList<Foundation> getReturnList()
    {
        return returnList;
    }
    
    /**
     * Get the tick.
     * @return tick Return the tick.
     */
    public int getTick()
    {
        return tick;
    }
    
    /**
     * Set the duration/extend
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    /**
     * Get the duration.
     * @return duration Return the duration.
     */
    public int getDuration()
    {
        return duration;
    }
    
    /**
     * Get the name.
     * @return name Return the name.
     */
    public String getName()
    {
        return name;
    }
    
     /**
     * Get the description.
     * @return description Return the description.
     */
    public String getDescription()
    {
        return description;
    }
    
    // Directions
    public final static String DIR_UP = "up";
    public final static String DIR_DOWN = "down";
    public final static String DIR_EAST = "east";
    public final static String DIR_WEST = "west";
    public final static String DIR_NORTH = "north";
    public final static String DIR_SOUTH = "south";
    
    // Rooms
    public final static String ROOM_A_NAM = "Room A";
    public final static String ROOM_A_DES = "Space, dust. Contains chicken.";
    public final static String ROOM_B_NAM = "Room B";
    public final static String ROOM_B_DES = "Space, dust. Contains Even";
    public final static String ROOM_C_NAM = "Room C";
    public final static String ROOM_C_DES = "Space, dust. Contains Boss";
    public final static String ROOM_D_NAM = "Room D";
    public final static String ROOM_D_DES = "Space, dust. Contains Time-riddle.";
    public final static String ROOM_E_NAM = "Room E";
    public final static String ROOM_E_DES = "Space, dust. Contains Spacegun.";
    public final static String ROOM_F_NAM = "Room F";
    public final static String ROOM_F_DES = "Space, dust. Round walls. Center.";
    
    // Special Items
    public final static String CHICKEN_NAM = "Chicken";    
    public final static String CHICKEN_DES = "Be Nice. Maybe it gives you something ...";    
    public final static String HERO_NAM = "Hero";
    public final static String HERO_DES = "This is the Hero, you. Find your way back to earth.";     
    public final static String EGG_NAM = "Egg";
    public final static String EGG_DES = "This a magic egg. Eat it and it will make you strong.";
    public final static String PISTOL_NAM = "Spacepistol";
    public final static String PISTOL_DES = "A weapon of the future. Use this to fight the Monster";
    public final static String MONSTER_NAM = "SPACEMONSTER";
    public final static String MONSTER_DES = "Fight this beast and you'll find your way back to earth.";
    public final static String EVEN_NAM = "Even"; 
    public final static String EVEN_DES = "Even knows the riddle of time";
}