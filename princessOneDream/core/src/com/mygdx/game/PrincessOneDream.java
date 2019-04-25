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

public class PrincessOneDream extends ApplicationAdapter {

    private final float windowWidth = 1200;
    private SpriteBatch batch;
    private Texture img;
    private Rectangle player;
    private boolean isJumping;
    private boolean isFalling;
    private float jumpDistance;
    private float maxJumpHeight;
    private String text;
    private BitmapFontCache cache;
    private BitmapFont font;

    private Rectangle animalRec;
    private Texture animalTex;

    private Texture background;
    private int backgroundX;

    public PrincessOneDream() {
        isFalling = false;
        isJumping = false;
        jumpDistance = 5;
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
        animalRec.y = 0;
        animalRec.width = animalTex.getWidth();
        animalRec.height = animalTex.getHeight();

        player = new Rectangle();
        player.x = 0;
        player.y = 0;
        player.width = img.getWidth();
        player.height = img.getHeight();

        background = new Texture("forestBuilt.jpeg");
        backgroundX = 0;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, backgroundX, 0);
        batch.draw(img, player.x, player.y);
        batch.draw(animalTex, animalRec.x, animalRec.y);
        userInputs();
        jump();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        background.dispose();
    }

    private boolean playerIsAtCenter() {

        float playerCenter = (player.x + (player.x + player.width)) / 2;
        float windowCenter = windowWidth / 2;
        int toleranceMargin = 30;

        if (playerCenter > windowCenter - toleranceMargin && playerCenter < windowCenter + toleranceMargin) {
            return true;
        }

        return false;
    }

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

    public void jump() {
        if (!isJumping) {
            return;
        }
        player.y += jumpDistance;

        if (!isFalling) {
            jumpDistance -= 0.1;
        } else {
            jumpDistance += 0.1;
        }

        if (!isFalling && player.y >= maxJumpHeight) {
            isFalling = true;
            jumpDistance *= -1;
        }
        if (player.y < 1 && isFalling) {
            player.y = 0;
            isFalling = false;
            isJumping = false;

        }

    }

    private void renderSingleLine() {
        text = "Daniel é gay";
        cache.setText(text, animalRec.getX() + 10, animalRec.getY() + animalRec.height + 35);
        cache.setColors(Color.BLACK);

        cache.draw(batch);
    }

}
