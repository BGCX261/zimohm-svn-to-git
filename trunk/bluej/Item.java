/**
 * Abstract structure for our items in game. 
 * 
 * @author Simon Zimmermann 
 * @version 14-04-09
 */
public class Item extends Foundation
{

    public Item(String name, String description, int duration)
    {
        super(name, description, duration);
    }
    
    public void use(){ 
       System.out.println("using " + getName());
    }

}
