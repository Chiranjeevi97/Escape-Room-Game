import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Fire extends BaseActor
{  
    public Fire(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture("assets/fire.png");
       addAction( Actions.delay(3) );   
       addAction( Actions.after( Actions.fadeOut(1.0f) ) );   
       addAction( Actions.after( Actions.removeActor() ) );  
       setSpeed(400);
       setMaxSpeed(400);
       setDeceleration(0); 
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
    

}