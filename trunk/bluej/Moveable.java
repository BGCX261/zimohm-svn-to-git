
/**
 * This is a interface for moveable objects 
 * 
 * @author Simon Zimmermann 
 * @version 23-04-09
 */

public interface Moveable
{
   /**
    * If moveMe() is true the object has a location it wishes to move to.
    */
   boolean getMoveMe();
   
    /**
     * @return   Returns the direction of the place it likes to get moved to.
     */
    String getDirection();
    
    /**
     * Set direction
     */
    void setDirection(String direction);
    
    /**
     * Set location
     */
    public void setRoom(Room room);
}
