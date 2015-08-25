
/**
 * Time riddle lies below
 * 
 * @author Simon Zimmermann
 * @version 29-04-09
 */
public class TimeRoom extends Room
{
    private int time;
    private int timeSince;
    private Room newExit;
    
    public TimeRoom(String name, String description, int duration, 
                    int time, Room newExit)
    {
        super(name, description, duration);
        this.time = time;
        this.newExit = newExit;
    }
    
    /**
     * check if hero is in room and how long he is here.
     * @overrides tick
     */
    protected void tick()
    {
        tick++;
        if(!active.isEmpty()){
            timeSince++;
            if(timeSince == time){
                setExit(DIR_DOWN, newExit);
                System.out.println("A hidden timelock opens a door before your feets." 
                                    + "\n" + getExitString());
            }
        }else{
            timeSince = 0;
        }
        
    }
}
