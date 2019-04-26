package com.mygdx.game.animals;

import com.badlogic.gdx.math.Rectangle;

public interface Animal {

    String getSentence();

    void setSentence(String sentence);

    Rectangle getCollider();

    float getWidth();

    float getHeight();

    boolean hasTalked();

    void setTalked();
}
