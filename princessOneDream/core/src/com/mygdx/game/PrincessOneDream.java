package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.platforms.Platform;
import com.mygdx.game.screens.PrincessDream;
import com.mygdx.game.screens.StartGame;

import java.util.ArrayList;
import java.util.List;

public class PrincessOneDream extends Game {


    public SpriteBatch batch;
    public Music music;


    @Override
    public void create() {
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
        setScreen(new PrincessDream(this));

    }

    @Override
    public void render() {
        super.render();


    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
