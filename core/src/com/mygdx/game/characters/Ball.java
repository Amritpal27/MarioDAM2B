package com.mygdx.game.characters;

import com.badlogic.gdx.math.Vector2;

public class Ball {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public float reboundSpeed = 12;
    public int type;

    Ball(){
        position.x = 0;
        position.y = 0;
        velocity.x = 1;
        velocity.y = 1;
    }

    public Ball(float px, float py, float vx, float vy,int type){
        position.x = px;
        position.y = py;
        velocity.x = vx;
        velocity.y = vy;
        this.type = type;
    }

    Ball(Vector2 position, Vector2 velocity){
        this.position = position;
        this.velocity = velocity;
    }
}
