package com.mygdx.game.platforms;

import com.badlogic.gdx.math.Rectangle;

public interface Platform {

    Rectangle getCollider();

    PlatformType getType();

    float getWidth();

    float getHeight();

}
