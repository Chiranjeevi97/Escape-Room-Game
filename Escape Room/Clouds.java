import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Clouds extends BaseActor
{  
    public Clouds(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture( "assets/clouds.png");
       setSize(64,64);
       setBoundaryPolygon(8);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
    
}
