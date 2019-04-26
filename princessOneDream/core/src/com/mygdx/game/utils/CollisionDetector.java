package com.mygdx.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.platforms.AbstractPlatform;
import com.mygdx.game.platforms.Platform;

import java.util.List;

public class CollisionDetector {

    /**
     * Checks whether the player will collide with another platform with his next movement
     *
     * @param mockPlayer the mock rectangle which represents the player's next movement
     * @param platforms the list of platforms to test the collision
     * @return true if the mock collides with at least one platform; false otherwise
     */
    public static Platform playerAndPlatforms(Rectangle mockPlayer, List<Platform> platforms) {

        for (Platform platform : platforms) {

            if (mockPlayer.overlaps(platform.getCollider())) {
                return platform;
            }
        }

        return null;
    }

    /**
     * Checks whether the player will land on a platform
     *
     * @param player the player
     * @param platforms the list of platforms to test the landing
     * @return the platform which the player lands; null if none checks the condition
     */
    public static Platform playerFallsOnPlatform(Rectangle player, List<Platform> platforms) {

        //float playerCenter = (player.x + (player.x + player.width)) / 2;
        Vector2 playerX = new Vector2(player.x, player.y);
        Vector2 playerX2 = new Vector2(player.x + player.width, player.y);

        for (Platform platform : platforms) {

            if (platform.getCollider().contains(playerX) || platform.getCollider().contains(playerX2)) {
                return platform;
            }
        }

        return null;
    }

}
