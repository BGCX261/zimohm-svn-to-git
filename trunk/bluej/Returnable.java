
/**
 * Purpose of this class is to keep track of items going backwards in 
 * the hiarchy. Didnt find a better solution to doing it.
 * 
 * This class is supposed to be used when a objects has a object it likes
 * to get rid of. 
 * 
 * @author Simon. 
 * @version 23-04-09
 */

public interface Returnable
{
    /**
     * Get the item we want.
     * @return        the item going back. will be of type F.
     */
     Foundation getReturnItem();
     
     /**
      * Find out if there is a returnItem.
      */
     boolean hasReturnItem();
}
