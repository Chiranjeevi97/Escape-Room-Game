import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Waterdrops extends BaseActor
{  
    public Waterdrops(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture( "assets/waterdrops.png");
       setSize(70,70);
       setBoundaryPolygon(8);   
       addAction( Actions.delay(1) );   
       addAction( Actions.after( Actions.fadeOut(0.7f) ) );   
       addAction( Actions.after( Actions.removeActor() ) );  
       setSpeed(400);
       setMaxSpeed(400);
       setDeceleration(0);   
       setMotionAngle(270);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
}
