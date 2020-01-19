import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LevelScreen_2 extends BaseScreen
{
    Label timeLabel;
    float time = 30;
    static int rightanswer = 0;
    private Music instrumental, winmusic, lost;
    private float audioVolume;
    
    public void initialize()
    {
        BaseActor grass = new BaseActor(0,0, mainStage);
        if(SurvivalGame.getLevel() == 50)
        {
            grass.loadTexture( "assets/Questions/Hints.JPG" );
            grass.setSize(800,500);
            rightanswer = 5;
        }
        else if(SurvivalGame.getLevel() > 2){
            grass.loadTexture( "assets/Questions/Question_1.JPG" );
            grass.setSize(800,500);
            rightanswer = 1;
        }
        else
        {
            grass.loadTexture( "assets/Questions/Question_2.png" );
            grass.setSize(800,500);
            rightanswer = 0;
        }
        
        instrumental = Gdx.audio.newMusic(Gdx.files.internal("assets/QuizMusic.mp3"));
        lost = Gdx.audio.newMusic(Gdx.files.internal("assets/lostmusic.wav"));
        winmusic = Gdx.audio.newMusic(Gdx.files.internal("assets/win1.mp3"));
        audioVolume = 0.30f;
        instrumental.setVolume(audioVolume);
        instrumental.setLooping(true);
        instrumental.play();
        lost.setVolume(audioVolume);
        winmusic.setVolume(audioVolume);
        TextButton A = new TextButton( "A", BaseGame.textButtonStyle);
        A.setPosition(200, 530);
        A.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;
                
                    if(rightanswer == 1){
                        instrumental.stop();
                        lost.play();
                        SurvivalGame.setActiveScreen(new ExitScreen());
                    }
                    else{
                        SurvivalGame.setLevel(3);
                        winmusic.play();
                        Skin uiSkin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassy-ui.json"));
                                Dialog dialog = new Dialog("Secret Code", uiSkin){
                                    public void result(Object obj) {
                                        System.out.println("result "+obj);
                                        instrumental.stop();
                                        SurvivalGame.setActiveScreen(new LevelScreen());
                                    }
                                };
                                Label message;
                                message = new Label("What is half of Goat?", BaseGame.labelStyle);
                                dialog.getBackground().setMinWidth(300);
                                dialog.getBackground().setMinHeight(300);
                                dialog.padBottom(60); // set padding on top of the dialog title
                                dialog.text(message);
                                dialog.button("Okay", true); //sends "true" as the result
                                dialog.show(mainStage);
                    } 
                return true;
            }
        );

        TextButton B = new TextButton( "B", BaseGame.textButtonStyle );
        B.setPosition(350,530);
        // uiStage.addActor(B);

        B.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;
                instrumental.stop();
                lost.play();
                SurvivalGame.setActiveScreen(new ExitScreen());
                return true;
            }
        );
        
        TextButton C = new TextButton( "C", BaseGame.textButtonStyle );
        C.setPosition(500,530);
        // uiStage.addActor(B);

        C.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;
                    
                    if ( !isTouchDownEvent(e) )
                    return false;
                
                    if(rightanswer == 5){
                        SurvivalGame.setLevel(30);
                        winmusic.play();
                        SurvivalGame.setActiveScreen(new ExitScreen());
                    }
                    else{
                        instrumental.stop();
                        lost.play();
                        SurvivalGame.setActiveScreen(new ExitScreen());
                    } 
                return true;
            }
        );
        
        TextButton D = new TextButton( "D", BaseGame.textButtonStyle );
        D.setPosition(650,530);
        // uiStage.addActor(B);

        D.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;
                
                    if(rightanswer == 1){
                        SurvivalGame.setLevel(2);
                        winmusic.play();
                        Skin uiSkin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassy-ui.json"));
                                Dialog dialog = new Dialog("Secret Code", uiSkin){
                                    public void result(Object obj) {
                                        System.out.println("result "+obj);
                                        instrumental.stop();
                                        
                                        SurvivalGame.setActiveScreen(new LevelScreen());
                                    }
                                };
                                Label message;
                                message = new Label("What is 2/3 of Cat?", BaseGame.labelStyle);
                                dialog.getBackground().setMinWidth(300);
                                dialog.getBackground().setMinHeight(300);
                                dialog.padBottom(60); // set padding on top of the dialog title
                                dialog.text(message);
                                dialog.button("Okay", true); //sends "true" as the result
                                dialog.show(mainStage);
                    }
                    else{
                        instrumental.stop();
                        lost.play();
                        SurvivalGame.setActiveScreen(new ExitScreen());
                    }
                return true;
            }
        );
        
        timeLabel = new Label("Time: " + (int)time, BaseGame.labelStyle);
        timeLabel.setColor(Color.LIGHT_GRAY);
        timeLabel.setPosition(20, 530);

        uiStage.addActor(A);
        uiStage.addActor(B);
        uiStage.addActor(C);
        uiStage.addActor(D);
        uiStage.addActor(timeLabel);

    }

    public void update(float dt)
    {
        time -= dt;
        timeLabel.setText("Time: " + (int)time);
        if (time <= 0)
        {
            time = 0;
            SurvivalGame.setActiveScreen(new ExitScreen());
        }
    }

    public boolean keyDown(int keyCode)
    {

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) 
            SurvivalGame.setActiveScreen(new ExitScreen());
        return false;
    }
}