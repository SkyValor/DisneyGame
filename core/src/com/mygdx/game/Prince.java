package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Prince {
    private SpriteBatch batch;
    private Texture img;
    private Rectangle player;
    private boolean isJumping;
    private boolean isFalling;
    private float jumpDistance;
    private float maxJumpHeight;



    public Prince() {
        isFalling = false;
        isJumping = false;
        jumpDistance = 5;
    }


    public void creatPrince() {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        player = new Rectangle();
        player.x = 0;
        player.y = 0;
        player.width = 50;
        player.height = 150;
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, player.x, player.y);
        batch.end();
        userInputs();
    }


    private void userInputs() {


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x -= 400 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x += 400 * Gdx.graphics.getDeltaTime();
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

    public void dispose() {
        batch.dispose();
        img.dispose();

    }


    public void jump() {
        if(!isJumping){
            return;
        }
        player.y += jumpDistance;

        if(!isFalling){
            jumpDistance -=0.1;
        } else{
            jumpDistance += 0.1;
        }

        if(!isFalling && player.y >= maxJumpHeight){
            isFalling = true;
            jumpDistance *= -1;
        }

        if(isFalling && player.y <= 0){
            System.out.println("AAA");
            player.y = 0;
            isFalling = false;
            isJumping = false;

        }

    }


}
