package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class CollisionDetector {

    /**
     * Checks whether the player will collide with another platform with his next movement
     *
     * @param mockPlayer the mock rectangle which represents the player's next movement
     * @param smallPlatforms the list of small platforms
     * @param bigPlatforms the list of big platforms
     * @return true if the mock collides with at least one platform; false otherwise
     */
    public static boolean playerAndPlatforms(Rectangle mockPlayer, List<Rectangle> smallPlatforms, List<Rectangle> bigPlatforms) {

        for (Rectangle smallPlatform : smallPlatforms) {

            if (mockPlayer.overlaps(smallPlatform)) {
                return true;
            }
        }

        for (Rectangle bigPlatform : bigPlatforms) {

            if (mockPlayer.overlaps(bigPlatform)) {
                return true;
            }
        }

        return false;
    }

}
