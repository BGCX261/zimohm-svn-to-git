import java.util.ArrayList;

/**
 * Superclass for all figures in the game, players and monsters.
 * 
 * @author Sindre Ohm
 * @version 22.04.2009
 */
public class Figures
{
    private String name;
    private String description;
    private ArrayList<Item> inventory;
    private int strength;
    
   /**
    * Creates a new figure (player or monster).
    *
    * @param name Name of the figure.
    * @param description Description of the figure.
    * @param strength The figures strength, too messure how much
    * it can carry.
    */
    public Figures(String name, String description, int strength)
    {
        this.name = name;
        this.description = description;
        this.strength = strength;
        inventory = new ArrayList<Item>();
          
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
    
    public int getStrength()
    {
        return strength;
    }
}
