package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PrincessOneDream extends ApplicationAdapter {

    private final float windowWidth = 1200;
    private SpriteBatch batch;

    private Texture img;
    private Rectangle player;
    private boolean isJumping;
    private boolean isFalling;

    private float jumpDistance;
    private final float defaultJumpDistance = 7;
    private float maxJumpHeight;

    private Texture background;
    private int backgroundX;

    public PrincessOneDream(){
        isFalling = false;
        isJumping = false;
        jumpDistance = defaultJumpDistance;
    }

    @Override

    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        player = new Rectangle();
        player.x = 0;
        player.y = 0;
        player.width = 50;
        player.height = 150;

        background = new Texture("forestBuilt.jpeg");
        backgroundX = 0;
	}

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,backgroundX,0);
        batch.draw(img, player.x, player.y);
        batch.end();

        jump();
        userInputs();
    }

    @Override
    public void dispose() {

        batch.dispose();
        img.dispose();
        background.dispose();
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
     * Receives input from keyboard for player or
     * other assets' movement
     */
    private void userInputs() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (!playerIsAtCenter()) {
                player.x -= 400 * Gdx.graphics.getDeltaTime();

            } else if (backgroundX + 5 <= 0) {
                backgroundX += 5;

            } else {
                player.x -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (!playerIsAtCenter()) {
                player.x += 400 * Gdx.graphics.getDeltaTime();

            } else if ((backgroundX + background.getWidth()) - 5 >= windowWidth) {
                backgroundX -= 5;

            } else {
                player.x += 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {

            if (!isJumping) {
                isJumping = true;
                maxJumpHeight = player.y + 200;
            }
        }

        if (player.x < 0) {
            player.x = 0;
        }

        if (player.x > 940) {
            player.x = 940;
        }

    }

    /**
     * Method for ascending and descending the player
     * Calls collision with Blocks
     */
    private void jump() {

        if(!isJumping){
            return;
        }

        player.y += jumpDistance;

        if(!isFalling && jumpDistance >= 0.5){
            jumpDistance -=0.1;

        }

        if (isFalling && jumpDistance <= -0.5){
            jumpDistance -= 0.1;
        }

        if(!isFalling && player.y >= maxJumpHeight){
            isFalling = true;
            jumpDistance *= -1;
        }

        if(isFalling && player.y <= 0){

            System.out.println("AAA");
            player.y = 0;
            jumpDistance = defaultJumpDistance;
            isFalling = false;
            isJumping = false;

        }

    }

}
