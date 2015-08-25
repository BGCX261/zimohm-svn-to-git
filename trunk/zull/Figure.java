import java.util.ArrayList;
import java.util.Iterator;
/**
 * Superclass for all figures in the game, players and monsters.
 * 
 * @author Sindre Ohm
 * @version 29.04.2009
 */
public class Figure
{
    private String name;
    private String description;
    protected ArrayList<Item> inventory;
    private long health;
    protected Room currentRoom;
    
   /**
    * Creates a new figure (player or monster).
    *
    * @param name Name of the figure.
    * @param description Description of the figure.
    * @param strength The figures strength, too messure how much
    * it can carry.
    */
    public Figure(String name, String description, long health, Room currentRoom)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        inventory = new ArrayList<Item>();
        this.currentRoom = currentRoom;
        
    }     

   /**
    * @return The name of the figure.
    */
    public String getName()
    {
        return name;
    }   
    
   /**
    * @return Description of the figure.
    */
    public String getDescription()
    {
        return description;
    }
    
   /**
    * @return health of the player.
    */
    public long getHealth()
    {
        return health;
    }

   /**
    *
    * @param dmg how much healtloss you suffer.
    */
    public void minusHealth(long dmg)
    {
        health = health - dmg;
    }

   /**
    * @return the room the player currently is in.
    */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

   /**
    * adds an item to the players inventory.
    */
    public void addItem(Item item)
    {
        inventory.add(item);
    }
    

   /**
    * removes an item form the players inventory.
    * @param the name of the item that is to be removed.
    */
    public void removeItem(String name)
    {
        Iterator<Item> item = inventory.iterator();
        while(item.hasNext()){
            Item tmp = item.next();
            if(tmp.getName().equals(name))
                item.remove();
            }              
    }

   /**
    * looks up an iten in the inventory.
    * @param name of the item.
    * @return returns the item.
    */
    public Item getItem(String name)
    {
        Item gotItem = null; 
            for (Item item : inventory)
            {
                if(item.getName().equals(name))
                    gotItem = item;
                else 
                    return null;
            
            }

            return gotItem;

    }
    

   /**
    * @return return the inventorylist.
    */
    public ArrayList getInventory()
    {
        return inventory;
    }

    public boolean pistolCheck()
    {
        if(inventory.contains("pistol"))
            return true;
        else
            return false;
    }

   /**
    * Checking you inventory.
    *
    */
    public String checkInventory()
    {
        String inv = "";
        for(Item item : inventory)
            inv += item.getName();
        
        return inv;
    }

  

}
