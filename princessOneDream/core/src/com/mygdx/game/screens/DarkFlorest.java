package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.PrincessOneDream;

import java.util.Iterator;

public class DarkFlorest implements Screen {

    private final float windowWidth = 1200;
    private final float groundHeight = 100;
    private SpriteBatch batch;

    private Texture thunder;
    private Texture img;
    private Rectangle player;
    private boolean isJumping;
    private boolean isFalling;
    private boolean isGrounded;

    private float jumpDistance;
    private final float defaultJumpDistance = 7;
    private float maxJumpHeight;
    private long thunderRate;


    private Texture background;
    private int backgroundX;
    private Array<Rectangle> thunders;


    private PrincessOneDream princessOneDream;

    public DarkFlorest(PrincessOneDream princessOneDream){
        this.princessOneDream = princessOneDream;
        batch = princessOneDream.batch;
        isFalling = false;
        isJumping = false;
        isGrounded = false;
        jumpDistance = defaultJumpDistance;
        create();
    }

    public void create() {

        img = new Texture("walking3.png");
        thunder = new Texture("bunny2.png");
        player = new Rectangle();
        player.x = 10;
        player.y = groundHeight;
        player.width = img.getWidth();
        player.height = img.getHeight();

        thunders = new Array<>();

        background = new Texture("forestBuilt_dark.jpeg");
        backgroundX = 0;

        spawn();


	}

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, backgroundX, 0);
        batch.draw(img, player.x, player.y);
        for(Rectangle thunder: thunders){
            batch.draw(this.thunder,thunder.x,thunder.y);
        }


        userInputs();
        jump();
        moveDrops();
        batch.end();
    }
    private void spawn() {
        Rectangle thunder = new Rectangle();
        thunder.x = MathUtils.random(0,900);
        thunder.y = 600;
        thunder.width = 30;
        thunder.height = 30;
        thunders.add(thunder);
        thunderRate = TimeUtils.nanoTime();
    }
    private void moveDrops() {

        Iterator<Rectangle> iterator = thunders.iterator();

        while (iterator.hasNext()) {

            Rectangle thunder = iterator.next();
            thunder.y -= 200 * Gdx.graphics.getDeltaTime();

            if (thunder.y + 30 < 0) {
                iterator.remove();
            }

            if (thunder.overlaps(player)) {
                iterator.remove();
            }

        }
        if (TimeUtils.nanoTime() - thunderRate > 1000000000) {
            spawn();
        }
    }



    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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
     * Receives input from keyboard for player and
     * other assets' movement
     */
    private void userInputs() {

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            System.exit(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            Rectangle mockRectangle = new Rectangle(player.x - 400 * Gdx.graphics.getDeltaTime(), player.y, player.width, player.height);


            if (!playerIsAtCenter()) {
                player.x -= 400 * Gdx.graphics.getDeltaTime();

            } else if (backgroundX + 5 <= 0) {
                backgroundX += 5;


            } else {
                player.x -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            Rectangle mockRectangle = new Rectangle(player.x + 400 * Gdx.graphics.getDeltaTime(), player.y, player.width, player.height);


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
                isGrounded = false;
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



}
