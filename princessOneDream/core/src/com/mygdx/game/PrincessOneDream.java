package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.platforms.Platform;

import java.util.ArrayList;
import java.util.List;

public class PrincessOneDream extends ApplicationAdapter {

    private final float windowWidth = 1200;
    private final float groundHeight = 100;
    private SpriteBatch batch;

    private Texture img;
    private Rectangle player;
    private boolean isJumping;
    private boolean isFalling;
    private boolean isGrounded;

    private float jumpDistance;
    private final float defaultJumpDistance = 7;
    private float maxJumpHeight;

    private String text;
    private BitmapFontCache cache;
    private BitmapFont font;

    private Rectangle animalRec;
    private Texture animalTex;

    private Texture background;
    private int backgroundX;

    private Texture smallPlatform;
    private Texture bigPlatform;
    private List<Rectangle> smallPlatforms;
    private List<Rectangle> bigPlatforms;

    public PrincessOneDream(){
        isFalling = false;
        isJumping = false;
        isGrounded = false;
        jumpDistance = defaultJumpDistance;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("walking3.png");
        animalTex = new Texture("cow2.png");

        font = new BitmapFont();
        text = "";
        cache = font.newFontCache();

        animalRec = new Rectangle();
        animalRec.x = 400;
        animalRec.y = groundHeight;
        animalRec.width = animalTex.getWidth();
        animalRec.height = animalTex.getHeight();

        player = new Rectangle();
        player.x = 10;
        player.y = groundHeight;
        player.width = img.getWidth();
        player.height = img.getHeight();

        background = new Texture("forestBuilt.jpeg");
        backgroundX = 0;

        smallPlatform = new Texture("asset_small.png");
        bigPlatform = new Texture("asset_big.png");
        smallPlatforms = new ArrayList<>();
        bigPlatforms = new ArrayList<>();

        smallPlatforms.add(new Rectangle(300, 100, 110, 60));
        smallPlatforms.add(new Rectangle(600, 350, 110, 60));
	}

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, backgroundX, 0);
        batch.draw(img, player.x, player.y);
        batch.draw(animalTex, animalRec.x, animalRec.y);

        for (Rectangle platform : smallPlatforms) {
            batch.draw(smallPlatform, platform.x, platform.y, 110, 60);
        }

        userInputs();
        jump();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        background.dispose();
        animalTex.dispose();
        smallPlatform.dispose();
        bigPlatform.dispose();
    }

    /**
     * Checks if player is at the middle of window
     *
     * @return true if player is at the middle; false otherwise
     */
    private boolean playerIsAtCenter() {

        float playerCenter = (player.x + (player.x + player.width)) / 2;
        float windowCenter = windowWidth / 2;
        int toleranceMargin = 30;

        if (playerCenter > windowCenter - toleranceMargin && playerCenter < windowCenter + toleranceMargin) {
            return true;
        }

        return false;
    }

    /**
     * Receives input from keyboard for player and
     * other assets' movement
     */
    private void userInputs() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            Rectangle mockRectangle = new Rectangle(player.x - 400 * Gdx.graphics.getDeltaTime(), player.y, player.width, player.height);

            if (CollisionDetector.playerAndPlatforms(mockRectangle, smallPlatforms, bigPlatforms)) {
                return;
            }

            if (!playerIsAtCenter()) {
                player.x -= 400 * Gdx.graphics.getDeltaTime();

            } else if (backgroundX + 5 <= 0) {
                backgroundX += 5;
                animalRec.x += 5;
                movePlatforms(5);

            } else {
                player.x -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            Rectangle mockRectangle = new Rectangle(player.x + 400 * Gdx.graphics.getDeltaTime(), player.y, player.width, player.height);

            if (CollisionDetector.playerAndPlatforms(mockRectangle, smallPlatforms, bigPlatforms)) {
                return;
            }

            if (!playerIsAtCenter()) {
                player.x += 400 * Gdx.graphics.getDeltaTime();

            } else if ((backgroundX + background.getWidth()) - 5 >= windowWidth) {
                backgroundX -= 5;
                animalRec.x -= 5;
                movePlatforms(-5);

            } else {
                player.x += 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {

            if (!isJumping) {
                isJumping = true;
                isGrounded = false;
                maxJumpHeight = player.y + 200;
            }
        }

        if (player.overlaps(animalRec) ) {

            if (player.x < animalRec.x){
                player.x = animalRec.getX() - player.width;

            } else {
                player.x = animalRec.getX() + animalRec.width;
            }

            renderSingleLine();
        }

        if (player.x < 0) {
            player.x = 0;
        }

        if (player.x > 940) {
            player.x = 940;
        }
    }

    /**
     * Moves all platforms according to player input, when necessary
     *
     * @param deltaX the distance to move in the X-axis
     */
    private void movePlatforms(float deltaX) {

        for (Rectangle platform : smallPlatforms) {
            platform.x += deltaX;
        }

        for (Rectangle platform : bigPlatforms) {
            platform.x += deltaX;
        }
    }

    /**
     * Method for ascending and descending the player
     * Calls collision with Blocks
     */
    private void jump() {

        // if player is not in mid-air, ignore this method
        if(!isJumping){
            return;
        }

        player.y += jumpDistance;

        // if player is going up, decrement the amount of travel distance for each tick
        // until a maximum value
        if(!isFalling && jumpDistance >= 0.5){
            jumpDistance -=0.1;

        }

        // if the player is going down, increment the amount of travel distance for each tick
        // until a maximum value
        if (isFalling && jumpDistance <= -0.5){
            jumpDistance -= 0.1;
        }

        // if player reaches its target Y while going up, invert the travel direction
        if(!isFalling && player.y >= maxJumpHeight){
            isFalling = true;
            jumpDistance *= -1;
        }

        Platform targetPlatform = CollisionDetector.playerFallsOnPlatform(player, smallPlatforms, bigPlatforms);
        // if player is falling and the feet collide with a platform, return to idle state
        if (isFalling && targetPlatform != null) {
            returnToIdleState(targetPlatform.getY());
        }

        // if player is falling and reaches ground level, return to idle state
        if(isFalling && player.y <= groundHeight){
            returnToIdleState(groundHeight);
        }
    }

    private void returnToIdleState(float targetY) {

        player.y = targetY;
        jumpDistance = defaultJumpDistance;
        isFalling = false;
        isJumping = false;
        isGrounded = true;
    }

    private void renderSingleLine() {
        text = "gay";
        cache.setText(Messages.HELLO, animalRec.getX() + 10, animalRec.getY() + animalRec.height + 35);
        cache.setColors(Color.WHITE);

        cache.draw(batch);
    }

}
