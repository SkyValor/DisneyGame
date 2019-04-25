package com.mygdx.game;

import com.badlogic.gdx.Game;

public class PrincessDream extends Game {

    private Prince prince;

    @Override
    public void create() {
        prince = new Prince();
        prince.creatPrince();

    }

    @Override
    public void render() {
        prince.draw();
        prince.jump();

    }

    @Override
    public void dispose() {
        prince.dispose();
    }


}

