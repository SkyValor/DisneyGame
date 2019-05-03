package com.mygdx.game.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class AnimalImpl implements Animal {


    private int id;
    private Texture image;
    private Rectangle collider;
    private String sentence;
    private boolean hasTalked;

    public AnimalImpl(float x, float y, Texture image, String sentence) {
        this.image = image;
        this.sentence = sentence;
        collider = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void dispose() {
        image.dispose();
    }

    @Override
    public Texture getImage() {
        return image;
    }

    @Override
    public String getSentence() {
        return sentence;
    }

    @Override
    public Rectangle getCollider() {
        return collider;
    }

    @Override
    public float getWidth() {
        return collider.width;
    }

    @Override
    public float getHeight() {
        return collider.height;
    }

    @Override
    public boolean hasTalked() {
        return hasTalked;
    }

    @Override
    public void setTalked() {
        hasTalked = true;
    }
}
