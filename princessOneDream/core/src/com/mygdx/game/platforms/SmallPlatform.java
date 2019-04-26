package com.mygdx.game.platforms;

import com.badlogic.gdx.math.Rectangle;

public class SmallPlatform extends AbstractPlatform {

    public SmallPlatform(float x, float y) {
        super(x, y, 100, 60);
    }

    @Override
    public PlatformType getType() {
        return PlatformType.SMALL;
    }


}
