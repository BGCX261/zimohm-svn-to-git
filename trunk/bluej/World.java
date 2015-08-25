import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This is the world. The game will reside in it. The world has a collection of
 * rooms.
 * 
 * @author Simon Zimmermann 
 * @version 24-04-09
 */
public class World extends Foundation
{
    //protected ArrayList<Room> active; // Active list may only contain rooms.
    
    public World(String name, String description, int duration)
    {
        super(name, description, duration);
       // active = new ArrayList<Room>();
        createWorld();
    }
    
    /**
     * OVERRIDES to move objects to a new room.
     * Tick all active objects.
     */
    public void tickActive()
    {
       Iterator<Foundation> a = active.iterator();
       while(a.hasNext()){
           Room b = (Room) a.next();
           b.tick();
           b.tickActive();
           b.removeExpired();
           move(b); // moves object's from A to new room.
        }

    }
    
    /**
     * Move's an object from one room to another.
     * @param a  The room containing the object to be moved.
     */
    protected void move(Room a)
    {
       HashMap<Room, Foundation> moveList = a.getMoveList();
       if(!moveList.isEmpty()){
            for(Foundation b : active){
                Room c = (Room) b;
                if(moveList.containsKey(c))
                    c.addActive(moveList.get(c));
            }
        }
    }
    /**
     * OVERRIDES to make it return only Room objects. 
     * Finds a Room based on name search.
     * 
     * @param name Name of our room. CaseInsensetive.
     * @return  room of with desired name.
     */
    protected Room getActive(String name)
    {
        for(Foundation a : active){
            if(a.getName().equals(name) && a instanceof Room)
                return (Room) a;
        }
        return null;
    }
    
    /**
     * Create the Game World. Add items and Players.
     */
    private void createWorld()
    {
        Room r;
        addActive(new Room(ROOM_A_NAM, ROOM_A_DES, FOREVER));
        addActive(new Room(ROOM_B_NAM, ROOM_B_DES, FOREVER));
        addActive(new Room(ROOM_C_NAM, ROOM_C_DES, FOREVER));
        addActive(new Room(ROOM_E_NAM, ROOM_E_DES, FOREVER));
        addActive(new Room(ROOM_F_NAM, ROOM_F_DES, FOREVER));
        addActive(new TimeRoom(ROOM_D_NAM, ROOM_D_DES, FOREVER, 200, getActive(ROOM_E_NAM)));

        // ROOM A
        r = getActive(ROOM_A_NAM);
        r.setExit(DIR_EAST, getActive(ROOM_F_NAM));
        r.addActive(new Chicken(CHICKEN_NAM, CHICKEN_DES, FOREVER));
        
        
        // ROOM B
        r = getActive(ROOM_B_NAM);
        r.setExit(DIR_SOUTH, getActive(ROOM_F_NAM));
        r.addActive(new Even(EVEN_NAM, EVEN_DES, FOREVER,
                    10, 2, getActive(ROOM_B_NAM)));
        
        // ROOM C
        r = getActive(ROOM_C_NAM);
        r.setExit(DIR_WEST, getActive(ROOM_F_NAM));
        r.setLocked(true);
        r.addActive(new Monster(MONSTER_NAM, MONSTER_DES, FOREVER,
                    10, 3, getActive(ROOM_C_NAM)));
        
        // ROOM D
        r = getActive(ROOM_D_NAM);
        r.setExit(DIR_NORTH, getActive(ROOM_F_NAM));        
        // Add timeroom
        
        // ROOM E
        r = getActive(ROOM_E_NAM);
        r.setExit(DIR_DOWN, getActive(ROOM_F_NAM));
        r.addActive(new Item(PISTOL_NAM, PISTOL_DES, FOREVER));
        
        // ROOM F
        r = getActive(ROOM_F_NAM);
        r.setExit(DIR_WEST, getActive(ROOM_A_NAM));
        r.setExit(DIR_NORTH, getActive(ROOM_B_NAM));
        r.setExit(DIR_EAST, getActive(ROOM_C_NAM));       
        r.setExit(DIR_SOUTH, getActive(ROOM_D_NAM));   
        r.addActive(new Chicken(CHICKEN_NAM, CHICKEN_DES, FOREVER));
        r.addActive(new Hero(HERO_NAM, HERO_DES, FOREVER,
                    10, 2, getActive(ROOM_F_NAM)));
        
    }    
    
}