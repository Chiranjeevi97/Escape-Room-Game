import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new SurvivalGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Escape Room", 800, 640 );
    }
}