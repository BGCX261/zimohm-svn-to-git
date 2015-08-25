
/**
 * List of Command you have to implement when being commandable.
 * 
 * @author Simon Zimmermann.
 * @version 28-04-09.
 */

public interface Commandable
{
    void processCommand(Command command);
    void go(String direction);
    void quit();
    void help();
    void look(String loc);
    void take(String item);
    void use(String item);
}
