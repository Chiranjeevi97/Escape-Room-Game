import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class MenuScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor grass = new BaseActor(0,0, mainStage);
        grass.loadTexture( "assets/MainScreen.jpg" );
        grass.setSize(800,600);

        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle);
        startButton.setPosition(160, 90);
        startButton.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;
                SurvivalGame.setActiveScreen( new LevelScreen() );
                return true;
            }
        );

        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );
        quitButton.setPosition(500,90);
        // uiStage.addActor(quitButton);

        quitButton.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;

                Gdx.app.exit();
                return true;
            }
        );

        uiStage.addActor(startButton);
        uiStage.addActor(quitButton);

    }

    public void update(float dt)
    {

    }

    public boolean keyDown(int keyCode)
    {
        if (Gdx.input.isKeyPressed(Keys.ENTER)) 
            SurvivalGame.setActiveScreen( new LevelScreen() );

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) 
            Gdx.app.exit();
        return false;
    }
}