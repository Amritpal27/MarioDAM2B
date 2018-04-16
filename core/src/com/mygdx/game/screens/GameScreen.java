package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;
import com.mygdx.game.characters.Ball;
import com.mygdx.game.characters.Player;

import java.util.Random;

public class GameScreen extends MyGameScreen {
    SpriteBatch batch;
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

        Assets.load();
        player = new Player();

        balls.add(new Ball(10,10,1,12,random.nextInt(5)));
        balls.add(new Ball(100,10,2,6,random.nextInt(5)));
        balls.add(new Ball(10,100,3,14,random.nextInt(5)));
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        batch.begin();
        batch.draw(Assets.imgBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Ball ball: balls) {
            batch.draw(Assets.imgBalls[ball.type], ball.position.x, ball.position.y);
        }
        batch.draw(Assets.walkAnimation.getKeyFrame(player.stateTime,true), player.position.x, player.position.y);
        batch.end();
    }

    void update(float delta){
        updateTimers(delta);
        addNewBall();
        updateBalls();
        updatePlayer(delta);
    }

    void updateTimers(float delta){
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
                ball.velocity.y = ball.reboundSpeeds[ball.type];
            }
            if (ball.position.x < 0 || ball.position.x > Gdx.graphics.getWidth() - Assets.imgBalls[ball.type].getRegionWidth()) {
                ball.velocity.x *= -1;
            }
        }
    }

    void updatePlayer(float delta){
        player.stateTime += delta;
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