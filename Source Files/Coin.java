import com.badlogic.gdx.scenes.scene2d.Stage;

public class Coin extends BaseActor
{
    public Coin(float x, float y, Stage s)
    { 
       super(x,y,s);
       setSize(10, 10);
       loadAnimationFromSheet("assets/coin.png", 1, 6, 0.1f, true);
    }    
}