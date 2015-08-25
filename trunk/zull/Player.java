import java.util.Stack;

/**
 * The figure the you play, controlled by you.
 * 
 * @author Sindre Ohm
 * @version 29.04.2009
 */
public class Player extends Figure
{
    private Stack roomHistory;
    
    
    public Player(String name, String description, long health, Room currentRoom)
    {
        super(name, description, health, currentRoom);
        roomHistory = new Stack<Room>();
    }
    
        /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExits(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
   /**
    * A figure or a room takes an item form anothe figure or a room.
    */
    public void takeItem(Command command)
    {
        Item item = null;
        if(!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        
        item = currentRoom.itemLookUp(command.getSecondWord());
        inventory.add(item);
        System.out.println("You picked up a " + item);
    }
   


}
