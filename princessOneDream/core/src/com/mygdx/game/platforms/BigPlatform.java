package com.mygdx.game.platforms;

public class BigPlatform extends AbstractPlatform {

    public BigPlatform(float x, float y) {
        super(x, y, 320, 60);
    }

    @Override
    public PlatformType getType() {
        return PlatformType.BIG;
    }
}
