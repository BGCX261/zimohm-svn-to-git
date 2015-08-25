
/**
 * An item in our game. Returns info about your item.
 * 
 * @author Simon Zimmermann
 * @version 31.3.2009
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private int weight;
    private String name;
    
    /**
     * Constructor creates our items and adds description and weight to them.
     * @param description The description of an item
     * @param weight the weight of a given item
     * @param name The name of the item. short.
     */
    public Item(String description, int weight, String name)
    {
        this.description = description;
        this.weight = weight;
        this.name = name;
    }
    
   /**
    * toString returns a string with all info about an item
    * @return Info about an item as String.
    */
   public String toString()
   {
        return "\nItem Info ... \nname: " + getName() + "\n" +
               "description: " + getDescription() + "\n" +
               "weight: " + getWeight() + "lb \n";
    }
   /**
    * Returns the description of an item
    * @return description The description as string
    */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * returns the weight.
     * @return weight The weight as a integer.
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Returns the name as string.
     * @return name The name as string.
     */
    public String getName()
    {
        return name;
    }

}
