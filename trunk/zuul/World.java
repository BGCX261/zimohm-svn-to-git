import java.util.HashMap;
/**
 * The game world. Contains a hashMap of all rooms.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World
{
    private HashMap<String,Room> rooms;
    /**
     * Constructor for objects of class World
     */
    public World()
    {
        rooms = new HashMap<String,Room>(); 
        rooms = createRooms();
    }
    /**
     * get room
     */
    public Room getRoom(String name)
    {
        return rooms.get(name);
    }
    /**
     * Add Location
     */
    public void addRoom(String name)
    {
        rooms.put(name, new Room(name));
    }
    /** Create all the rooms and link their exits together.
     */
    private HashMap<String,Room> createRooms()
    {   
        Room room;
        // create the rooms
        addRoom("outside");
        addRoom("kitchen");
        addRoom("basement");
        addRoom("trap");
        addRoom("bedroom");
        addRoom("garden");
        
        room = rooms.get("outside");
        room.setExits("west", rooms.get("kitchen"));
        
        room = rooms.get("kitchen");
        room.setExits("north", rooms.get("garden"));
        room.setExits("east", rooms.get("outside"));
        room.setExits("south", rooms.get("basement"));
        room.setExits("west", rooms.get("bedroom"));
        room.addItem(new Item("Coffee cup, half-full.", 2, "cup"));
        room.addItem(new Item("Old Bread", 5, "bread"));
        
        room = rooms.get("basement");
        room.setExits("north", rooms.get("kitchen"));
        room.setExits("down", rooms.get("trap"));
        
        room = rooms.get("trap");
        room.setExits("up", rooms.get("bedroom"));
        
        room = rooms.get("bedroom");
        room.setExits("east", rooms.get("kitchen"));
        room.addItem(new Item("The bed, blue", 100, "bed"));

        room = rooms.get("garden");
        room.setExits("south", rooms.get("kitchen"));
        return rooms;
    }
}