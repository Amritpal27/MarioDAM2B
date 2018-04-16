package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Controls;
import com.mygdx.game.characters.Ball;
import com.mygdx.game.characters.Player;

import java.util.Random;

public class GameScreen extends MyGameScreen {
    SpriteBatch batch;
    TextureRegion imgBackground;
    TextureRegion[] imgBalls = new TextureRegion[5];
    TextureRegion imgPlayer;

    float gravity = -.2f;

    Random random = new Random();

    Array<Ball> balls = new Array<Ball>();
    Player player;

    float newBallTimer;

    GameScreen(Game game){
        super(game);
    }

    @Override
    public void show () {
        batch = new SpriteBatch();
        TextureAtlas textureAtlas = new TextureAtlas("superpang.txt");
        imgPlayer = textureAtlas.findRegion("shoot");
        imgBalls = new TextureRegion[]{textureAtlas.findRegion("ball0"),textureAtlas.findRegion("ball1"),textureAtlas.findRegion("ball2"),textureAtlas.findRegion("ball3")};
        imgBackground = textureAtlas.findRegion("background");

        player = new Player();

        balls.add(new Ball(10,10,1,12,random.nextInt(5)));
        balls.add(new Ball(100,10,2,6,random.nextInt(5)));
        balls.add(new Ball(10,100,3,14,random.nextInt(5)));
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.begin();
        batch.draw(imgBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Ball ball: balls) {
            batch.draw(imgBalls[ball.type], ball.position.x, ball.position.y);
        }
        batch.draw(imgPlayer, player.position.x, player.position.y);
        batch.end();
    }

    void update(){
        updateTimers();
        addNewBall();
        updateBalls();
        updatePlayer();
    }

    void updateTimers(){
        newBallTimer += Gdx.graphics.getDeltaTime();
    }

    void addNewBall(){
        if(newBallTimer > 1f){
            balls.add(new Ball(10, 100, 3, 14, new Random().nextInt(5)));
            newBallTimer = 0;
        }
    }

    void updateBalls(){
        for(Ball ball: balls) {
            ball.velocity.y += gravity;

            ball.position.y += ball.velocity.y;
            ball.position.x += ball.velocity.x;

            if (ball.position.y < 0) {
                ball.velocity.y = ball.reboundSpeed;
            }
            if (ball.position.x < 0 || ball.position.x > Gdx.graphics.getWidth() - imgBalls[ball.type].getRegionWidth()) {
                ball.velocity.x *= -1;
            }
        }
    }

    void updatePlayer(){
        if(Controls.isLeftPressed()){
            player.position.x -= player.velocity.x;
        }

        if(Controls.isRightPressed()){
            player.position.x += player.velocity.x;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}