package com.mygdx.game.animals;

import com.badlogic.gdx.math.Rectangle;

public class AnimalImpl implements Animal {

    private Rectangle collider;
    private String sentence;
    private boolean hasTalked;

    public AnimalImpl(float x, float y, float width, float height, String sentence) {
        collider = new Rectangle(x, y, width, height);
    }

    @Override
    public String getSentence() {
        return sentence;
    }

    @Override
    public void setSentence(String sentence) {
        this.sentence = sentence;
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
