package com.mygdx.game.characters;

import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Vector2 size = new Vector2();
    public  float stateTime;


    public Player(){
        position.x = 100;
        position.y = 20;
        velocity.x = 5;
        size.x = 64;
        size.y = 64;
    }
}
