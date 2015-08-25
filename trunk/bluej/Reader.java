import java.util.Scanner;
import java.util.ArrayList;

/**
 * Purpose of this class is to scan the command line.
 * 
 * @author Sindre Ohm  & Simon Zimmermann
 * @version 28-04-09
 */

public class Reader implements Runnable{
    public boolean yes;
    Thread thread;
    private Scanner scanner;
    private ArrayList<String> input; // List to store input from cmd line.
    private CommandWords commandwords;
    
    public Reader(String name)
    {
       scanner = new Scanner(System.in); 
       thread = new Thread(this, name);
       input = new ArrayList<String>(); 
       commandwords = new CommandWords();
    }     
   
    /**
     * Start thread.
     */
    public void start(){
       thread.start();    
    }
    
    /**
     * Loop for new command.
     */
    public void run() {
        yes = true;
        while(yes){
            System.out.println();
            Scanner tokens = new Scanner(scanner.nextLine()); // Store the Current line in a String
            if(tokens.hasNext())
                input.add(tokens.next().toLowerCase());
                if(tokens.hasNext())
                    input.add(tokens.next().toLowerCase());
       }
    }
    
    /**
     * Find out if there is a new command.
     * @return true if there is a new command.
     */
    public boolean hasCommand()
    {
        if(!input.isEmpty())
            return true;
        return false;
    }
    
    /**
     * Return a command.
     * @return returns a string which contains a command.
     */
    public Command getCommand(){
        ArrayList<String> words = new ArrayList<String>();
        words.addAll(input);
        input.clear(); // Empty old inputs.
        
        if(commandwords.isCommand(words.get(0))){
            if(words.size() == 2)
                return new Command(words.get(0), words.get(1));
            return new Command(words.get(0), null);
        }
        return new Command(null, null);
    }
    
    /**
    * @return all the valid commands.
    */
    public String getCommandWords()
    {
        return commandwords.getCommandWords();
    }
}