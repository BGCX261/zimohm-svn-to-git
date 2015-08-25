import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
 *  This is a room in the world. 
 * 
 * @author Even AAby Larsen (Modified by Simon Zimmermann)
 * @version 24-04-09
 */
public class Room extends Foundation
{
    protected HashMap<String, Room> exits;    
    protected HashMap<Room, Foundation> moveList; // dest. Room, Player or obj.
    protected boolean locked;
    

    /**
     * Create room
     */
    public Room(String name, String description, int duration)
    {
        super(name, description, duration);
        exits = new HashMap<String, Room>();
        moveList = new HashMap<Room, Foundation>();
        locked = false;
    }
    
    /**
    * OVERRIDE: Tick all active objects.
    * This method will tick objects and check if given object
    * has something to return. 
    */
    public void tickActive()
    {
        moveList.clear(); // clear move list.
        getReturnList().clear(); // clear return list.
        
        Iterator<Foundation> a = active.iterator(); // iterate over active items.
        
        while(a.hasNext()){
            Foundation f = a.next();
            
            f.tick();
            f.tickActive();
            f.removeExpired();
            
            addReturnable(f);  
            
            if(move(f))
                a.remove(); // remove from active room.
        }
        addActive(getReturnList()); // add new items to room.
    } 
    
    /**
     * Makes an object ready to move. adds it to a special hashMap
     * which contains objects moving to a different room. This
     * will remove the object from the current room on success.
     * Objects can't move themself. They can only request to move.
     * This design choice may be rather hard to work with, still 
     * have to find a better way.
     * 
     * @param f Is the object which wants to move.
     * @return true if items is added to move list.
    */
    protected boolean move(Foundation f){
        if(f instanceof Moveable){
            Moveable m = (Moveable) f; // local var/cast.
            Room room = getExit(m.getDirection()); // local var.
            
            if(m.getMoveMe() && room != null){ // if move = null, dir wrong.
               if(room.getLocked()){
                   System.out.println("Door is locked. Find a way to open it");
                   return false;
               }
               else{
                    moveList.put(room, f); // put into moveList
                    m.setRoom(room); // modify location of obj.
                    return true;
               }
            }     
        }
        return false;
    }
    
    /**
     * @return List of items we'd like to move. 
     */
    public HashMap<Room, Foundation> getMoveList(){
        return moveList;
    }

       
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Get the exit directions.
     * @return the set of exit keys.
     */
    public Set<String> getExits() {
        return exits.keySet();
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "exits: ";
        for(String exit : getExits()) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }    
    
    /**
     * set locked status
     * @param true or false.
     */
    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }
    /**
     * get Locked status
     */
    public boolean getLocked()
    {
        return locked;
    }
}
