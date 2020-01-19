import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;

public class Flames extends BaseActor
{  
    public Flames(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture("assets/frame_2.png");
       setSize(2300,120);
       setBoundaryPolygon(8);       
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        
        setAnimationPaused( !isMoving() );
    }  
}