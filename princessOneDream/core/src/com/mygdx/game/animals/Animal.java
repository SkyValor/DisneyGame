package com.mygdx.game.animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface Animal {

    void dispose();

    Texture getImage();

    String getSentence();

    Rectangle getCollider();

    float getWidth();

    float getHeight();

    boolean hasTalked();

    void setTalked();
}
