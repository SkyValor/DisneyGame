package com.mygdx.game.platforms;

import com.badlogic.gdx.math.Rectangle;

public abstract class Platform {

    protected float x;
    protected float y;
    protected Rectangle collider;
    protected PlatformType type;

    public Platform(float x, float y, PlatformType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public float getY() {
        return y;
    }
}
