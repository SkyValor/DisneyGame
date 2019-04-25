package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Background implements Disposable {

    private Texture image;

    private int x;
    private int width;

    public Background(int x) {
        image = new Texture("forest.jpg");
        this.x = x;
        width = x + image.getWidth();
    }

    public void move(int deltaX) {
        x += deltaX;

    }

    public Texture getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return x + image.getWidth();
    }

    @Override
    public void dispose() {
        image.dispose();
    }
}
