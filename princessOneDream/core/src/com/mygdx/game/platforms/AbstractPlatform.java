package com.mygdx.game.platforms;

import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractPlatform implements Platform {

    protected Rectangle collider;

    public AbstractPlatform(float x, float y, float width, float height) {
        collider = new Rectangle(x, y, width, height);
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
}
