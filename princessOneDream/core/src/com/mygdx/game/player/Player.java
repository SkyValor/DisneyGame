package com.mygdx.game.player;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.platforms.Platform;

public class Player {

    private Rectangle collider;
    private Direction direction;

    private boolean isJumping;
    private boolean isFalling;
    private boolean isGrounded;

    private Platform platformToIgnore;

    public Player(float x, float y, float width, float height, Direction dir) {
        collider = new Rectangle(x, y, width, height);
        direction = dir;
    }

    public float getX() {
        return collider.x;
    }

    public float getY() {
        return collider.y;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    public Platform getPlatformToIgnore() {
        return platformToIgnore;
    }

    public void setPlatformToIgnore(Platform platformToIgnore) {
        this.platformToIgnore = platformToIgnore;
    }
}
