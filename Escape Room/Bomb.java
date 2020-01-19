import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Bomb extends BaseActor
{  
    Bomber firebomb;
    
    public Bomb(float x, float y, Stage s)
    {
       super(x,y,s);
       loadTexture("assets/frame_2.png");
       setSize(50,50);
       setBoundaryPolygon(8); 
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }  
    
        public void bomb()
    {
        if ( getStage() == null)
            return;
     
        firebomb = new Bomber(0,0, this.getStage());
        firebomb.bomb();
        firebomb.centerAtActor(this);
        firebomb.setMotionAngle(90);  
    }
    
}
