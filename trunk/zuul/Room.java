import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room 
{
    private String name;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Player> players;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name) 
    {
        this.name = name;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        players = new ArrayList<Player>();
    }
    
    /**
     * Get exits for the current room a player is in. 
     * @param direction the direction as a String.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Returns a string of exits for the user to see.
     * @return A description of exit locations as string.
     */
    public String getExitString()
    {
        String s = "Exits:";
        Set<String> keys = exits.keySet();
        for(String key : keys){
            s += " " + key;
        }
        return s;
        
    }
    
    /**
     * Define the exits of this room.  
     * @param direction The direction of the exit.
     * @param neighbor The room in the direction.
     */
    public void setExits(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
    * Add item. Adds an item to this room
    * @param item An Item object.
    */
    public void addItem(Item item)
    {
        if(item != null)
            items.add(item);
    }
    
    /**
     * Show items returns a string with all items in the current room
     */
    private String getItems()
    {
        String s = "";
        for(Item item : items)
            s += item.toString() + " ";
            
        return s;
    }
    
    /**
    * @return The Long description of the room.
    */
    /*public String getLongDescription()
    {
        return "You are " + description + "\n" + getItems() + "\n" + getExitString();
    }*/
    
    /**
     * @return The description of the room.
     */
    public String getName()
    {
        return name;
    }

}
