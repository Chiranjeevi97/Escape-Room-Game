import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Door extends BaseActor
{  
    public Door(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture("assets/door.png");
       setSize(180,180);
       setBoundaryPolygon(8); 
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
    
}
