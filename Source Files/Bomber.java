 import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Bomber extends BaseActor
{  
    public Bomber(float x, float y, Stage s)
    {
       super(x,y,s);
       String[] filenames = 
            {"assets/frame_1.png", "assets/frame_2.png", "assets/frame_3.png", 
                "assets/frame_4.png", "assets/frame_5.png", "assets/frame_6.png", 
                "assets/frame_7.png", "assets/frame_8.png", "assets/frame_9.png", 
                "assets/frame_10.png", "assets/frame_11.png", "assets/frame_12.png"};

       loadAnimationFromFiles(filenames, 0.1f, true);
       setSize(70,70);
       setBoundaryPolygon(8);   
       setSpeed(400);
       setMaxSpeed(400);
       setDeceleration(0);   
       setMotionAngle(90);
       addAction( Actions.delay(1) );   
       addAction( Actions.after( Actions.fadeOut(0.5f) ) );   
       addAction( Actions.after( Actions.removeActor() ) );  
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }
    
}
