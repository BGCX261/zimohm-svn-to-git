import java.util.ArrayList;
/**
 * This contains a player playing the game, and the attributes that player has.
 * 
 * @author Simon Zimmermann
 * @version 31.3.2009
 */
public class Player
{
    private String name;
    private int maxWeight;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    //private Parser parser;
    //private Command command;
    

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room currentRoom)
    {
        this.name = name;
        this.maxWeight = 300;
        this.currentRoom = currentRoom;
        //parser = new Parser();
    }

    /**
     * Move
     */
    public void move()
    {
        
    }
    
    /**
     * Set a new currentRoom.
     * @param newRoom The new room a player is going to.
     */
    public void setLocation(Room newRoom){
        currentRoom = newRoom;
    }
    
    /**
     * Returns the location of a player.
     * @return location
     */
    public Room getLocation(){
        return currentRoom;
    }
    
    /**
     * Return the name of a player
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the max weight a player can carry.
     * @return maxWeight 
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
}
