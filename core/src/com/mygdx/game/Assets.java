package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Assets {
    public static TextureRegion imgBackground;
    public static com.badlogic.gdx.graphics.g2d.TextureRegion[] imgBalls = new TextureRegion[5];
    public static TextureRegion imgPlayer;
    public static Animation<TextureRegion> walkAnimation;


    public static void load(){
        TextureAtlas textureAtlas = new TextureAtlas("superpang.txt");
        imgPlayer = textureAtlas.findRegion("shoot");
        walkAnimation = new Animation<TextureRegion>(0.1f,textureAtlas.findRegions("move"));
        imgBalls = new TextureRegion[]{textureAtlas.findRegion("ball0"),textureAtlas.findRegion("ball1"),textureAtlas.findRegion("ball2"),textureAtlas.findRegion("ball3")};
        imgBackground = textureAtlas.findRegion("background");
    }

}
