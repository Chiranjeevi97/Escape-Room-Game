import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;

public class Ghost extends BaseActor
{
    Fire firebullet;
    
    public Ghost(float x, float y, Stage s)
    {
        super(x,y,s);
        loadAnimationFromSheet( "assets/enemy-flyer.png", 1, 3, 0.05f, true);
        setSize(64,64);
        setBoundaryPolygon(6);
        
        setSpeed( MathUtils.random(50,80) );
        setMotionAngle( MathUtils.random(0,360) );
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        if ( MathUtils.random(1,120) == 1 )
            setMotionAngle( MathUtils.random(0,360) );
        
        applyPhysics(dt);
        boundToWorld();
    }

    public void fire()
    {
       if ( getStage() == null)
            return;
            
       firebullet = new Fire(0,0, this.getStage());
       firebullet.centerAtActor(this);
       firebullet.setRotation(0);
       firebullet.setMotionAngle(this.getMotionAngle());     
    }
}