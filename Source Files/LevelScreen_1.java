import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LevelScreen_1 extends BaseScreen
{
    Hero kira;

    boolean gameOver;
    static int coins;
    float time;
    boolean messagevisible = false;

    Label coinLabel;
    Label timeLabel;
    Label lifeLabel;
    Label messageLabel;
    Table keyTable;
    int keys = 0;
    int bullets = 0;
    int lifes;
    float t = 3;
    boolean shot = true;
    private float audioVolume;
    private Music instrumental, coincollect, springboardmusic;
    private Music lost, winmusic, laser, firemusic, hit, jump;
    
    ArrayList<Color> keyList;

    public void initialize() 
    {
        String level;
        level = "assets/Level_1.tmx";
        TilemapActor tma = new TilemapActor(level, mainStage);
        
        if(SurvivalGame.getLevel() == 1){
            coins = SurvivalGame.getScore();
            lifes = SurvivalGame.getHealth();
        }
        else
        {
            coins = SurvivalGame.getScore();
            lifes = SurvivalGame.getHealth();
        }
        
        for (MapObject obj : tma.getRectangleList("land") )
        {
            MapProperties props = obj.getProperties();
            new Solid( (float)props.get("x"), (float)props.get("y"),
                (float)props.get("width"), (float)props.get("height"), 
                mainStage );
        }

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        kira = new Hero( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);

        for (MapObject obj : tma.getTileList("flag") )
        {
            MapProperties props = obj.getProperties();
            new Flag( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        for (MapObject obj : tma.getTileList("coins") )
        
        {
            MapProperties props = obj.getProperties();
            new Coin( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        for (MapObject obj : tma.getTileList("gun") )
        {
            MapProperties props = obj.getProperties();
            new Gun( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        for (MapObject obj : tma.getTileList("springboard") )
        {
            MapProperties props = obj.getProperties();
            new Springboard( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("flyer")) {
            MapProperties props = obj.getProperties();
            new Flyer((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        
        for (MapObject obj : tma.getTileList("ghost")) {
            MapProperties props = obj.getProperties();
            new Ghost((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getTileList("key") )
        {
            MapProperties props = obj.getProperties();
            Key key = new Key( (float)props.get("x"), (float)props.get("y"), mainStage );
            String color = (String)props.get("color");
            if ( color.equals("red") )
                key.setColor(Color.RED);
            else // default color
                key.setColor(Color.WHITE);
        }

        for (MapObject obj : tma.getTileList("lock") )
        {
            MapProperties props = obj.getProperties();
            Lock lock = new Lock( (float)props.get("x"), (float)props.get("y"), mainStage );
            String color = (String)props.get("color");
            if ( color.equals("red") )
                lock.setColor(Color.RED);
            else // default
                lock.setColor(Color.WHITE);
        }

        kira.toFront();

        gameOver = false;
        time = 90;

        coinLabel = new Label("Score : " + coins, BaseGame.labelStyle);
        coinLabel.setColor(Color.GOLD);
        keyTable = new Table();
        timeLabel = new Label("Time: " + (int)time, BaseGame.labelStyle);
        timeLabel.setColor(Color.LIGHT_GRAY);
        lifeLabel = new Label("Health: " + lifes, BaseGame.labelStyle);
        lifeLabel.setColor(Color.RED);
        messageLabel = new Label("Message", BaseGame.labelStyle);
        messageLabel.setVisible(false);        

        uiTable.pad(20);
        uiTable.add(coinLabel);
        uiTable.add(timeLabel).expandX();
        uiTable.add(keyTable).expandX();
        uiTable.add(lifeLabel);
        uiTable.row();
        uiTable.add(messageLabel).colspan(3).expandY();
        
        instrumental = Gdx.audio.newMusic(Gdx.files.internal("assets/secondMusic.mp3"));
        lost = Gdx.audio.newMusic(Gdx.files.internal("assets/lostmusic.wav"));
        laser = Gdx.audio.newMusic(Gdx.files.internal("assets/laser.mp3"));
        firemusic = Gdx.audio.newMusic(Gdx.files.internal("assets/fire.mp3"));
        winmusic = Gdx.audio.newMusic(Gdx.files.internal("assets/win1.mp3"));
        hit = Gdx.audio.newMusic(Gdx.files.internal("assets/hit.mp3"));
        coincollect = Gdx.audio.newMusic(Gdx.files.internal("assets/coin.mp3"));
        jump = Gdx.audio.newMusic(Gdx.files.internal("assets/jump.mp3"));
        springboardmusic = Gdx.audio.newMusic(Gdx.files.internal("assets/springboard.mp3"));
        
        audioVolume = 0.30f;
        instrumental.setVolume(audioVolume);
        instrumental.setLooping(true);
        instrumental.play();
        laser.setVolume(audioVolume);
        lost.setVolume(audioVolume);
        firemusic.setVolume(audioVolume);
        hit.setVolume(audioVolume);
        coincollect.setVolume(audioVolume);
        jump.setVolume(audioVolume);
        springboardmusic.setVolume(audioVolume);

        keyList = new ArrayList<Color>();
    }

    public void update(float dt)
    {
        if ( gameOver )
            return;
        
        for (BaseActor flyer : BaseActor.getList(mainStage, "Flyer")) {
            if (kira.overlaps(flyer)) {
                Blast boom = new Blast(0,0, mainStage);
                boom.centerAtActor(flyer);
                lifes--;
                hit.play();
                lifeLabel.setText("Health: " + lifes);
                flyer.remove();
                if(lifes <= 0){
                    boom.centerAtActor(kira);
                    messageLabel.setText("You DIED ! - Game Over");
                    messageLabel.setColor(Color.RED);
                    messageLabel.setVisible(true);
                    messagevisible = true;
                    kira.remove();
                    lifes = 0;
                    lifeLabel.setText("Health: " + lifes);
                    gameOver = true;
                }
            }
        }
        
        for (BaseActor ghost : BaseActor.getList(mainStage, "Ghost")) {
            if (kira.overlaps(ghost)) {
                Blast boom = new Blast(0,0, mainStage);
                boom.centerAtActor(ghost);
                lifes--;
                hit.play();
                lifeLabel.setText("Health: " + lifes);
                ghost.remove();
                if(lifes <= 0){
                    boom.centerAtActor(kira);
                    messageLabel.setText("You DIED ! - Game Over");
                    messageLabel.setColor(Color.RED);
                    messageLabel.setVisible(true);
                    messagevisible = true;
                    kira.remove();
                    SurvivalGame.setActiveScreen(new ExitScreen());
                    gameOver = true;
                }
            }
        }
        
        for (BaseActor fire : BaseActor.getList(mainStage, "Fire")) {
            if (kira.overlaps(fire)) {
                Blast boom = new Blast(0,0, mainStage);
                boom.centerAtActor(fire);
                lifes--;
                hit.play();
                lifeLabel.setText("Health: " + lifes);
                fire.remove();
                if(lifes <= 0){
                    boom.centerAtActor(kira);
                    messageLabel.setText("You DIED ! - Game Over");
                    lost.play();
                    messageLabel.setColor(Color.RED);
                    messageLabel.setVisible(true);
                    messagevisible = true;
                    kira.remove();
                    SurvivalGame.setActiveScreen(new ExitScreen());
                    gameOver = true;
                }
            }
        }
        
        for (BaseActor flag : BaseActor.getList(mainStage, "Flag"))
        {
            if (kira.overlaps(flag))
            {  
                if(coins >= 30)
                {
                    winmusic.play();
                    SurvivalGame.setLevel(3);
                    SurvivalGame.setScore(coins);
                    SurvivalGame.setHealth(lifes);
                    instrumental.stop();
                    Skin uiSkin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassy-ui.json"));
                                Dialog dialog = new Dialog("Secret Code", uiSkin){
                                    public void result(Object obj) {
                                        System.out.println("result "+obj);
                                        SurvivalGame.setActiveScreen(new LevelScreen());
                                    }
                                };
                                Label message;
                                message = new Label("What is 3/7 of Chicken?", BaseGame.labelStyle);
                                dialog.getBackground().setMinWidth(300);
                                dialog.getBackground().setMinHeight(300);
                                dialog.padBottom(60); // set padding on top of the dialog title
                                dialog.text(message);
                                dialog.button("Okay", true); //sends "true" as the result
                                dialog.show(mainStage);
                }
                else
                {
                    int remain = 30 - coins;
                    messageLabel.setText(String.format("Sorry! You Need %d More Points", remain));
                    messageLabel.setColor(Color.RED);
                    messageLabel.setVisible(true);
                    messagevisible = true;
                    gameOver = false;
                }
            }

        }
        
        for (BaseActor enemy : BaseActor.getList(mainStage, "Flyer")){
            
            for (BaseActor bullet : BaseActor.getList(mainStage, "Bullet"))
            {
                if (bullet.overlaps(enemy) )
                {
                    coins++;
                    hit.play();
                    coinLabel.setText("Score : " + coins);Blast boom = new Blast(0,0, mainStage);
                    boom.centerAtActor(enemy);
                    bullet.remove();
                    enemy.remove();
                }
            }
        }
        
        for (BaseActor enemy : BaseActor.getList(mainStage, "Ghost")){
            
            for (BaseActor bullet : BaseActor.getList(mainStage, "Bullet"))
            {
                if (bullet.overlaps(enemy) )
                {   
                    coins++;
                    hit.play();
                    coinLabel.setText("Score : " + coins);
                    Blast boom = new Blast(0,0, mainStage);
                    boom.centerAtActor(enemy);
                    bullet.remove();
                    enemy.remove();
                }
            }
        }
        
        for (BaseActor coin : BaseActor.getList(mainStage, "Coin"))
        {
            if ( kira.overlaps(coin) )
            {
                coins = coins + 2;
                coincollect.play();
                coinLabel.setText("Score : " + coins);
                coin.remove();
            }
        }

        time -= dt;
        timeLabel.setText("Time: " + (int)time);
        if(messagevisible)
        {
            if(t <= 0)
            {
             messageLabel.setVisible(false);
             t  = 3;
             messagevisible = false;
            }
            t -= dt;
            
        }

        if((int)time % 5 == 0 && shot){
            for (BaseActor enemy : BaseActor.getList(mainStage, "Ghost")) 
            {
                enemy.fire();
                firemusic.play();
                shot = false;
            }
        }
        
        if((int)time % 3 == 0 && (int)time % 2 == 0 && (int)time % 1 == 0){
            shot = true;
        }
        
        if((int)time <= 10 && !gameOver)
        {
            messageLabel.setText("Hurry Up!!");
            messageLabel.setColor(Color.RED);
            messageLabel.setVisible(true);
            messagevisible = true;
        }

        for (BaseActor bullet : BaseActor.getList(mainStage, "Gun"))
        {
            if ( kira.overlaps(bullet) )
            {   
                bullets += 50;
                bullet.remove();
            }
        }

        if (time <= 0)
        {
            messageLabel.setText("Time Up - Game Over");
            lost.play();
            messageLabel.setColor(Color.RED);
            messageLabel.setVisible(true);
            messagevisible = true;
            kira.remove();
            gameOver = true;
        }

        for (BaseActor springboard : BaseActor.getList(mainStage, "Springboard"))
        {
            if ( kira.belowOverlaps(springboard) && kira.isFalling() )
            {
                springboardmusic.play();
                kira.spring();
            }
        }

        for (BaseActor actor : BaseActor.getList(mainStage, "Solid"))
        {
            Solid solid = (Solid)actor;

            if ( solid instanceof Lock && kira.overlaps(solid) )
            {
                Color lockColor = solid.getColor();
                if ( keyList.contains(lockColor))
                {
                    if ( keys == 3)
                    {
                        solid.setEnabled(false);
                        solid.addAction( Actions.fadeOut(0.5f) );
                        solid.addAction( Actions.after( Actions.removeActor() ) );
                    }
                    else
                    {
                        int keysleft = 3 - keys;
                        messageLabel.setText(String.format("Sorry! You Need %d More Keys", keysleft));
                        messageLabel.setColor(Color.RED);
                        messageLabel.setVisible(true);
                        messagevisible = true;
                    }
                }
            }

            if ( kira.overlaps(solid) && solid.isEnabled() )
            {
                Vector2 offset = kira.preventOverlap(solid);

                if (offset != null)
                {
                    // collided in X direction
                    if ( Math.abs(offset.x) > Math.abs(offset.y) )
                        kira.velocityVec.x = 0;
                    else // collided in Y direction
                        kira.velocityVec.y = 0;
                }
            }
            
            for (BaseActor enemy : BaseActor.getList(mainStage, "Flyer")){
                if (enemy.overlaps(solid))
                {
                    Vector2 offset = enemy.preventOverlap(solid);
                }
            }
            
            // for (BaseActor enemy : BaseActor.getList(mainStage, "Fire")){
                // if (enemy.overlaps(solid))
                // {
                    // enemy.remove();
                // }
            // }
            
            for (BaseActor bullet : BaseActor.getList(mainStage, "Bullet")){
                
                if ( bullet.overlaps(solid))
                {
                    bullet.remove();
                }
            }
        }

        for (BaseActor key : BaseActor.getList(mainStage, "Key"))
        {
            if ( kira.overlaps(key) )
            {
                coincollect.play();
                Color keyColor = key.getColor();
                key.remove();

                BaseActor keyIcon =  new BaseActor(0,0,uiStage);
                keyIcon.loadTexture("assets/key-icon.png");
                keyIcon.setColor(keyColor);
                keyTable.add(keyIcon);
                keys++;
                keyList.add(keyColor);         
            }
        }

    }

    public boolean keyDown(int keyCode)
    {
        if (gameOver)
            return false;

        
        if ( keyCode == Keys.S )
        {
            if(bullets > 0 && !gameOver)
            {   
                kira.shoot();
                laser.play();
                bullets--;
            }
        }
        
        if (keyCode == Keys.SPACE)
        {
            if ( kira.isOnSolid() ) {
                jump.play();
                kira.jump();
            }
        }
        return false;
    }
}