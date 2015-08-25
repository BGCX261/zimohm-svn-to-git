
/**
 * Write a description of class Parent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Child extends Parent
{
    // instance variables - replace the example below with your own
    //private int age;
    private int birth;

    /**
     * Constructor for objects of class Parent
     */
    public Child(int duration, String sirname, int birth)
    {
        super(duration, sirname);
        this.birth = birth;
    }
    
   /**
    * Return birth.
    */
   public int getBirth()
   {
        return birth;
    }
}
