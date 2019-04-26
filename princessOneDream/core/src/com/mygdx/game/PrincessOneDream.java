package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.PrincessDream;

public class PrincessOneDream extends Game {

    public SpriteBatch batch;
    public Music music;


    @Override
    public void create() {
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("music/naturesound.mp3"));
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




