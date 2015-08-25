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
 * @author  Michael Kolling and David J. Barnes, edited by Sindre Ohm and Simon Zimmerman
 * @version 29.04.2009
 */
public class Room 
{
    private String description;

    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private HashMap<String, Figure> figures;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        figures = new HashMap<String, Figure>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param
     * @param 
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);  
    }
   
   /**
    * Creating an new Item and adding it to the rooms 
    * item ArrayList.
    * @parma name The items name.
    * @param description The items description.
    * @param weight The items weight.
    */  
    public void addItem(String name, String description, int weight)
    {
        Item item = new Item(name, description, weight);
        items.add(item);
    }

    public void addCurrentItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(String name)
    {
        Iterator<Item> item = items.iterator();
            while(item.hasNext())
            {
                Item tmp = item.next();
                if(tmp.getName().equals(name))
                    item.remove();
            }              
    }
    
   /**
    *
    */
    public void addFigure(String name, String description, int strength, Room currentRoom)
    {
        Figure figure = new Figure(name, description, strength, currentRoom);
        figures.put(name, figure);
    } 

   /**
    * Lists all the figures in the room.
    */
    public String listFigures()
    {
        String returnString = "Figures: ";
        Set<String> keys = figures.keySet();
        for(String figure : keys) {
            returnString += " " + figure + ",";
        }
        return returnString;
        
    }   


   /**
    * @param currentRoom that the player spawns in.
    */
    public void addPlayer(Room currentRoom)
    {
        Player player = new Player("Sindre", "Stor og sterk", 10, currentRoom);
        figures.put("player", player);
    }  

   /**
    * Removes a player trom the room.
    * @param name The name of the player that should be removed from the room.
    */
    public void removePlayer(String name)
    {
        figures.remove(name);
    }  

   /**
    * move a player, adding it in a room without creating a new.
    * @param name key of the player.
    * @param player the player that shall be moved.
    */
    public void movePlayer(String name, Figure player)
    {
        figures.put(name, player);
    }
        
   /**
    * 
    *
    * @param direction
    * @return Returns alle the exits in the room.
    */
    public Room getExits(String direction)
    {
        return exits.get(direction);
    }

   /**
    *
    *@return all the exits in a room.
    */
    public String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

   /**
    *
    * @return  all the items in a room.
    */
    public String getItemList()
    {
        String itemList = "Items: ";
        for(Item item : items) {
            itemList += item.getName() + " " + item.getDescription() + ".\n ";
            
        }
        return itemList;
    }
    
   /**
    *
    * @name
    */
    public Item itemLookUp(String name)
    {
        Item itemFound = null;    
        for(Item item : items) 
        {
            String tmp = item.getName().toLowerCase();
            if(tmp.equals(name.toLowerCase()))
                itemFound = item;
            
        }
        return itemFound;
             
    }

   /**
    * Return a given object based on name in figure HashMap
    */
    public Figure getFigure(String name)
    {
        return figures.get(name);      
    }
   /**
    *
    */
    public ArrayList getItems()
    {
        return items;
    }
            
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

   /**
    *
    *@return The description of the room and all the exits.
    */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

}
