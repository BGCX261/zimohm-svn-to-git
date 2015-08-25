
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes, edited by Sindre Ohm
 * @version 23.04.2009
 */
public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "take", "use"
    };

    /**
     * Constructor for objects of class CommandWords
     */
    public CommandWords()
    {
        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
    * Makes a list of all the valid commands and return them.
    * @return all the valid commands
    */
    public String getCommandWords()
    {
        String s = "";
        for(String command : validCommands)
            s += command + ", ";    
        return s;
    }
}
