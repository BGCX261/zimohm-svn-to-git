import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is our Hero, you, the player. You will have an inventory of 
 * items and a method for fighting. 
 * 
 * @author Simon Zimmermann 
 * @version 24-04-09
 */
public class Hero extends Player implements Moveable, Commandable
{
    private String direction; // Used to set the direction a player likes to move.
    private boolean moveMe; // Used to find out if player wants to move into new room
    private boolean start; // Used the first time program executes.
    private Reader parser; // Holds an instance of our Parser.
    
    /**
     * Set the vars and inizialise the arraylist.
     * @param name The name of what we want to create.
     * @param description The description of what we want to create.
     * @param duration The duration of the player.
     * @param hitpoints The hitpoints of our character.
     * @param attack The attack of our character.
     * @param room The location of a player
     */
    public Hero(String name, String description, int duration, 
                int hitpoints, int attack, Room room)
    {
        super(name, description, duration, hitpoints, attack, room);
        
        moveMe = false;
        start = true; 
        parser = new Reader("parser");
    }
    
    /**
     * @overrides default tick function in Foundation.
     * it will start the parser first time the game loops. 
     * also it will process commands user might have.
     */
    protected void tick()
    {   
        tick++;
        moveMe = false;
        
        getReturnList().clear(); // clear return list.
        Iterator<Foundation> a = active.iterator();
        while(a.hasNext()){
            Foundation f = a.next();            
            addReturnable(f);  
        }
        addActive(getReturnList()); // add new items to player inventory.
        
        if(start){ // if first run.
            parser.start(); // Start Parser
            start = false;
        }
        
        if(parser.hasCommand()){
            processCommand(parser.getCommand());
        }
    }
    
    /// BELOW IS IMPLEMENTATION OF COMMANDABLE INTERFACE
    /**
     * This method has a simple set of if/else sentences which runs
     * through the possible command matches and thereafter calls desired
     * command.
     * @param command is a object with two var's containing commands. 
     * firstWord is fixed, while secondWord is dynamic
     */
    public void processCommand(Command command)
    {
        String firstWord = command.getFirstWord();
        String secondWord = command.getSecondWord();
        
        if(firstWord == null) {
            System.out.println("huh, what do you mean?");
        }
        else if (firstWord.equals("help"))
            help();
        else if (firstWord.equals("go"))
            go(secondWord);
        else if (firstWord.equals("quit"))
            quit();
        else if (firstWord.equals("look"))
            look(secondWord);
        else if (firstWord.equals("take"))
            take(secondWord);
        else if (firstWord.equals("use"))
            use(secondWord);
    }
    
    /**
     * If you like to go somewhere this tells the world an object needs to move.
     * @param direction is the direction you'd like to go. 
     */
    public void go(String direction){
        if(direction == null)
            System.out.println("You didnt say where to go");
        else{
            setDirection(direction);
            moveMe = true;
            System.out.println("You are trying to go to " + getDirection());
        }
    }
    
    /**
     * Used for quitting the game.
     */
    public void quit()
    {
        System.out.println("Quitting the game ..");
        System.exit(10);
    }
    
    /**
     * Displays where you are, the exits there are and possible commands.
     */
    public void help()
    {
        System.out.println("You are located in \"" + room.getName() + "\""
                            + " - " + room.getDescription() + "\n"
                            + "Commands: " + parser.getCommandWords() + "\n"
                            + room.getExitString());
    }
    
    /**
     * Look inside your inventory or inside the room you currently are in.
     * @param loc defines where to look, either <room/inventory>
     */
    public void look(String loc)
    {
        if(loc == null)
            System.out.println("look <room/inventory> are only valid commands");
        else if(loc.equals("room")){
            System.out.println("Objects and players in this room are as following: ");
            ArrayList<Foundation> list = room.getActive();
            for(Foundation f : list)
                System.out.println("* \""+ f.getName() + "\" - " + f.getDescription());
        }
        else if(loc.equals("inventory")){
            System.out.println("The following items are in your inventory.\n"
                                + "You can use the \"use\" <item> command to use them.");
            for(Foundation f : active)
                System.out.println("* \""+ f.getName() + "\" - " + f.getDescription());
        }
    }
    
    /**
     * Pick up an item and add it to your inventory.
     * @param item to be picked up must be located in current room.
     */
    public void take(String item)
    {   
        if(item != null){
            Foundation f = room.getActive(item);
            if(f instanceof Item){ // make sure only to pickup items
                addActive(f);
                room.removeList.add(f);
                System.out.println("You pickehd up " + f.getName() + " and added it to your inventory.");
            }
            else
                System.out.println("Cannot pick up \"" + item + "\"");
        }
        else
            System.out.println("Take what?");
    }    
    
    /**
     * This method is ment to make it possible to use an item. 
     * @param item to use. 
     */
    public void use(String name)
    {
        if(name == null)
            System.out.println("You have to use something. The item must be in inventory");
        else{
            Foundation item = getActive(name);
            Item myItem = (Item) item;
            if(myItem != null)
                myItem.use();
            else{
                System.out.println("\"" + name + "\" not found.");
            }
        }
    }
    
    /// BELOW IS IMPLEMENTATION OF MOVEABLE INTERFACE ///
 
    /**
     * @return   Returns true if hero wants to move.
     */
    public boolean getMoveMe()
    {
        return moveMe;
    }
    
    /**
     * @return   Returns the direction of the place it likes to get moved to.
     */
    public String getDirection(){
        return direction;
    }
    
    /**
    * set direction
    * @param direction of where you'd like to go.
    */
    public void setDirection(String direction){
        this.direction = direction;
    }
    
   /**
     * Set location.
     * @param room the location of a player.
     */
    public void setRoom(Room room)
    {
        this.room = room;
        System.out.println("You are now in \"" + room.getName() + "\" - " 
                            + room.getDescription() + "\n" + room.getExitString());
    }
    /*protected void test()
    {
        Room room = getRoom();
        System.out.println(room.getName());
        System.out.println(room.getActive(EGG_NAM).getName());
        addActive(room.getActive(EGG_NAM));
        room.removeActive(getActive(EGG_NAM));
        System.out.println(getActive(EGG_NAM).getName());
    }*/
        // Simulation of commands.
        /*if(tick == 100){
            setDirection(DIR_EAST);
            moveMe = true;
            System.out.println("Set direction " +  DIR_EAST);
        }*/
    
}