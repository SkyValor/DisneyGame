package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PrincessOneDream;

public class endGame implements Screen {

    private PrincessOneDream princessOneDream;
    private Texture img;


    public endGame(PrincessOneDream princessOneDream) {
        this.princessOneDream = princessOneDream;
        img = new Texture("kiss.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        princessOneDream.batch.begin();
        princessOneDream.batch.draw(img, 0, 0);
        princessOneDream.batch.end();
        userInputs();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void userInputs() {

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            System.exit(0);

        }

    }
}

