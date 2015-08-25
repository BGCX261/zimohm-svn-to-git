import java.util.ArrayList;
import java.util.Iterator;
/** 
 * Write a description of class bug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bug
{
    private ArrayList<Parent> active;
    private int time;
    private int ticks;
    
    public bug()
    {
         active = new ArrayList<Parent>();
    }

    /**
     * An example of a method - replace this comment with your own
    */
    public void run()
    {
          active.add(new Parent(160, "Parent"));
          active.add(new Child(200,"Child", 1985));
          active.add(new ChildChild(300, "ChildChild", 2005));
         boolean finish = false;
         
         while(!finish){ // run looop.
            tickActive();
            removeExpired();
            
            if(active.isEmpty()){
                finish = true;
                System.out.println("All Objects expired. Game over");
            }
            try { Thread.sleep(40); } catch (Exception e) {} // 25 times per sec.
            
            // JUST MESSING WITH TIME.
            ticks++;
            if(getTime() > time)
                System.out.println(getTime() + " sec");
            time = getTime();
         }
    }
    
    /**
     * View Active Objects;
     */
    private void tickActive()
    {
       // ArrayList expired = new ArrayList<Parent>();
       for(Parent p : active)
           p.tick();
    }
    
    /**
     * Remove Expired objects
     */
    private void removeExpired()
    {
        Iterator<Parent> p = active.iterator();
        while(p.hasNext()){
            Parent that = p.next();
            if(that.hasExpired()){
                System.out.println("Removing " + that.getSirname());
                p.remove();
            }              
        }
    }
    
    /**
     * calculate run time
     */
    private int getTime()
    {
        return ticks / 25;
    }
}
