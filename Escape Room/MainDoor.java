import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MainDoor extends BaseActor
{  
    public MainDoor(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture("assets/maindoor.png");
       setSize(200,200);
       setBoundaryPolygon(8); 
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
    
}
