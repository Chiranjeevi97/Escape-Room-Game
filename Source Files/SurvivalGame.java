public class SurvivalGame extends BaseGame
{
    public static int secondlevel = 0;
    public static int Score = 0;
    public static int Health = 0, totalkeys = 0;
    public static float Time = 0;
    public static boolean Once = true;
    public static boolean Twice = true;
    
    public void create() 
    {        
        super.create();
        setActiveScreen( new MenuScreen() );
    }
    
    public static void setLevel(int sent)
    {
        secondlevel = sent;
    }
    
    public static int getLevel()
    {
        return secondlevel;
    }
    
    public static void setScore(int sent)
    {
        Score = sent;
    }
    
    public static int getScore()
    {
        return Score;
    }
    
    public static int getTotalkeys()
    {
        return totalkeys;
    }
    
    public static void setHealth(int sent)
    {
        Health = sent;
    }
    
    public static int getHealth()
    {
        return Health;
    }
    
    public static void setTime(float sent, int keys)
    {
        Time = sent;
        totalkeys = keys;
    }
    
    public static float getTime()
    {
        return Time;
    }
    
    public static void setOnce(boolean sent)
    {
        Once = sent;
    }
    
    public static boolean getOnce()
    {
        return Once;
    }
    
    public static void setTwice(boolean sent)
    {
        Twice = sent;
    }
    
    public static boolean getTwice()
    {
        return Twice;
    }
}