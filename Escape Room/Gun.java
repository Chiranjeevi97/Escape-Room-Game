import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Gun extends BaseActor
{
    public Gun(float x, float y, Stage s)
    { 
        super(x,y,s);
        loadTexture("assets/gun.png");
        setSize(52, 52);
        setBoundaryPolygon(8);
        
        Action pulse = Actions.sequence(
                Actions.scaleTo(1.1f, 1.1f, 0.5f),
                Actions.scaleTo(1.0f, 1.0f, 0.5f) );

        addAction( Actions.forever(pulse) );
    }    
}