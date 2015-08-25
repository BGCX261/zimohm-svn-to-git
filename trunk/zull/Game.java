import java.util.Stack;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes, edited by Sindre Ohm and Simon Zimmerman
 * @version 29.04.2009
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack roomHistory;
    private Timer timer;
    private Room sentrum, chickenRoom, strangersHome, bossRoom, teleportRoom, hiddenRoom;
    
 
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<Room>();
        timer = new Timer("timer");  
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Room sentrum, chickenRoom, strangersHome, bossRoom, teleportRoom, hiddenRoom;
      
        // create the rooms
        sentrum = new Room("in the centrum of the space station(room 1213)");
        chickenRoom = new Room("in small room (room 4231)");
        strangersHome = new Room("in great dinning room (room 2311)");
        bossRoom = new Room("at a throne (room 9453)");
        teleportRoom = new Room("in a very dark controll room (room 8200)");
        hiddenRoom = new Room("in the weapon storage (room 6543)");
        
        // initialise room exits
        sentrum.setExits("right", chickenRoom);
        sentrum.setExits("left", strangersHome);
        sentrum.setExits("up", bossRoom);
        sentrum.setExits("down", teleportRoom);
        chickenRoom.setExits("back", sentrum);
        strangersHome.setExits("back", sentrum);
        bossRoom.setExits("back", sentrum);
        teleportRoom.setExits("back", sentrum);
        //teleportRoom.setExits("wait", hiddenRoom);
        hiddenRoom.setExits("door", sentrum);
        
        chickenRoom.addItem("chicken", " An normal sized brown chicken ", 20);
        hiddenRoom.addItem("pistol", " A spacepistol that fires lazers, pew pew", 50);
        sentrum.addPlayer(sentrum);
        bossRoom.addFigure("spaceman", " A giant snail man thing", 25, bossRoom);
        strangersHome.addFigure("even", " A hungery man", 50, strangersHome);

        //createItems();
        //createFigures();
    
        // start game outside
        previousRoom = sentrum;
        currentRoom = sentrum;
    }

   /**
    * Adds all the items in the game.
    *
    *
    private void createItems()
    {
        //adding items
        chickenRoom.addItem("Chicken ", "An normal sized brown chicken ", 20);
        hiddenRoom.addItem("pistol", "A spacepistol that fires lazers, pew pew", 50);
    }

   **
    * Creates and adds all the figures in the gamer (player(s) and Monsters).
    *
    private void createFigures()
    {
        sentrum.addPlayer(sentrum);
        bossRoom.addFigure("SpaceMan", "A giant snail man thing", 25, bossRoom);
        strangersHome.addFigure("Even", "A hungery man", 15, strangersHome);
    }
    */
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("***********Welcome the the spacestation*************");
        System.out.println();
        System.out.println("            .------.        .------.    ");
        System.out.println("           /        \\      /        \\   ");
        System.out.println("          /_        _\\    /_        _\\  ");
        System.out.println("         // \\      / \\\\  // \\      / \\\\ ");
        System.out.println("         |\\__\\    /__/|  |\\__\\    /__/| ");
        System.out.println("          \\    ||    /    \\    ||    /  ");
        System.out.println("           \\        /      \\        /   ");
        System.out.println("            \\  __  /        \\  __  /    ");
        System.out.println("             '.__.'          '.__.'     ");
        System.out.println("              |  |            |  |      ");
        System.out.println("              |  |            |  |      ");
        System.out.println("Your main objective is to kill the evil SpaceMan");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
       
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
            lookAround();
        else if (commandWord.equals("back"))
            goBack();
        else if (commandWord.equals("take"))
            takeItem(command);
        else if (commandWord.equals("drop"))
            dropItem(command);
        else if (commandWord.equals("fight"))
            fight();

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("You are lost and alone on a spacestation,");
        System.out.println("you need to kill the evil SpaceMan.");
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command input that the user gives.
     */
    private void goRoom(Command command) 
    {
        System.out.println();
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
            
            //moves the player from one room to anotgher without creating a new.
            nextRoom.movePlayer("player",currentRoom.getFigure("player"));
            currentRoom.removePlayer("player");
            currentRoom = nextRoom;
            
            System.out.println(currentRoom.getLongDescription());
            
        }
    }

   /**
    * Figure goes back too the room it was in before the
    * current room.
    * !!!!! Gets "EmptyStackException" if stack is empty!!!!!
    */
    public void goBack()
    {
        System.out.println();
            
        Figure player = currentRoom.getFigure("player");
        if(!roomHistory.empty())
            currentRoom = (Room) roomHistory.pop();
            currentRoom.movePlayer("player", player);
            
            
        
        System.out.println(currentRoom.getLongDescription());
    }
   /**
    * Makes the player look around the room
    * prints out the rooms description, exits and items.
    */
    public void lookAround()
    {
        System.out.println();

        System.out.println("You look around the room and see that ");
        System.out.println(currentRoom.getLongDescription());
        
        if(!currentRoom.getItems().isEmpty())    
            System.out.println("and you notice " + currentRoom.getItemList());
        
        if(!currentRoom.listFigures().isEmpty())
            System.out.println("There are also " + currentRoom.listFigures());
        
        System.out.println("In your inventory you got: " + currentRoom.getFigure("player").checkInventory());
    }

   /**
    * Gives an item from one figure/room? to another.
    */
    public void dropItem(Command command)
    {
        System.out.println();

        if(!command.hasSecondWord())
        {
            System.out.println("Drop what?");
            return;
        }
        Figure p = currentRoom.getFigure("player"); // tha playrz
        
        if(!p.getInventory().isEmpty())
        {
            //Item itemMove = p.getItem(command.getSecondWord());
            currentRoom.addCurrentItem(p.getItem(command.getSecondWord()));
            p.removeItem(command.getSecondWord());
       
        

            System.out.println("You droped an item");
            
            /** Quest 1 */
            //checks if the player is in the room that the chicken is suppose too be droped.
            if(currentRoom.equals(strangersHome) && command.getSecondWord().equals("chicken"))
            {   
                System.out.println("Even says thanks and say: 8200 chickens I -wait- for");
                
                //creates an new "exit" option in the teleport room.
                teleportRoom.setExits("wait", hiddenRoom);
            }
           
        }
        else
        System.out.println("You dont have " + command.getSecondWord());
        

    }


   /**
    * A figure or a room takes an item form anothe figure or a room.
    */
    public void takeItem(Command command)
    {
        System.out.println();

        if(!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        Item pickedUp = currentRoom.itemLookUp(command.getSecondWord());
        if(pickedUp != null){
            currentRoom.getFigure("player").addItem(pickedUp);
            System.out.println("You picked up an item");
            currentRoom.removeItem(command.getSecondWord());
        }
        else 
            System.out.println("Cant find what you are looking for");
      
    }
    /**
     * The fight command, to win you need to fight and beat the spaceman.
     * It is based on a realtime, so you have to write fast.
     */
    public void fight()
    {
        System.out.println();

        Figure p = currentRoom.getFigure("player");
        Figure s = currentRoom.getFigure("spaceman"); 
        // checks if your in the boss room.
        

        if (currentRoom.equals(bossRoom))
        {
            // checks health of both you and spaceman, to decide who won.
            if(s.getHealth() <= 0)
            {
                System.out.println("The SpaceMan is dead : D");
                System.out.println("*******You won the game ********");
                return;
            }
            else if(p.getHealth() <= 0)
            {
                System.out.println("You died and was eaten by teh SpaceMan :,(");
                System.out.println("-------You loose, game over----");
                return;
            }  
            
            if(timer.getStartTime()  == 0)
            {
                timer.start();
                System.out.println("The fight has started!!");
                System.out.println("Type -fight- as fast as you can");
                return;
            }

            //Deceies how much damages you do to the spaceman
            // its more if you got the spacepistol.
            if(p.getInventory().contains(p.getItem("pistol")))
            {
                s.minusHealth(5);
                System.out.println("You hit spaceman for 5 hp");
            }
            else
            {
                s.minusHealth(1);
                System.out.println("You hit spaceman for 1 hp");
            }
            // calculates the spacemans damages based on how much
            // time you use.
            long damage = timer.getTime() / 1000;

            p.minusHealth(damage);
            System.out.println("Spaceman hits you for " + damage);
                
              
           System.out.println(timer.getTime());
        }
        // if you try to fight even.
        else if(currentRoom.equals(strangersHome))
        {
            System.out.println("Even gets angry and hits you for 2 hp");
            p.minusHealth(2);
        }
        // if you try to fight in anyother room.
        else
        {
            System.out.println("You fight your shadow");
            System.out.println("nothing happens");
        }
        
    }  
     
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        System.out.println();

        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
