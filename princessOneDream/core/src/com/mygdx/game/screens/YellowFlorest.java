package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.PrincessOneDream;
import com.mygdx.game.animals.Animal;
import com.mygdx.game.animals.AnimalIcon;
import com.mygdx.game.animals.AnimalImpl;
import com.mygdx.game.platforms.BigPlatform;
import com.mygdx.game.platforms.Platform;
import com.mygdx.game.platforms.PlatformType;
import com.mygdx.game.platforms.SmallPlatform;
import com.mygdx.game.player.Direction;
import com.mygdx.game.player.Player;
import com.mygdx.game.utils.CollisionDetector;
import com.mygdx.game.utils.Messages;

import java.util.ArrayList;
import java.util.List;

public class YellowFlorest implements Screen {
    private final float windowWidth = 1200;
    private final float groundHeight = 100;
    private final float defaultJumpDistance = 7;

    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;

    float elapsedTime;
    private Player player;
    private SpriteBatch batch;
    private Texture img;
    private float jumpDistance;
    private float maxJumpHeight;

    private BitmapFontCache cache;
    private BitmapFont font;

    private Animal squirrel;
    private Animal bird;
    private Animal owl;
    private Animal bunny2;
    private Animal bird3;
    private Animal bunny;
    private AnimalIcon animalIcon;
    private AnimalIcon[] animalIcons;




    private Texture background;
    private int backgroundX;

    private Texture smallPlatform;
    private Texture bigPlatform;

    private List<Platform> platforms;
    private List<Animal> animals;
    private PrincessOneDream princessOneDream;

    public YellowFlorest(PrincessOneDream princessOneDream) {
        this.princessOneDream = princessOneDream;
        jumpDistance = defaultJumpDistance;
        batch = princessOneDream.batch;
        jumpDistance = defaultJumpDistance;
        create();
    }

    public void create() {

        img = new Texture("prince.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(img, 103, 190);

        animationFrames = new TextureRegion[4];
        int index = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        animation = new Animation<>(1f / 4f, animationFrames);


        font = new BitmapFont();
        cache = font.newFontCache();


        player = new Player(10, groundHeight, 103, 190, Direction.RIGHT);

        background = new Texture("forestBuilt_golden.jpeg");
        backgroundX = 0;


        smallPlatform = new Texture("asset_small.png");
        bigPlatform = new Texture("asset_big.png");
        animalIcon = new AnimalIcon();
        animalIcons = animalIcon.getAnimalList();


        player.setFalling(false);
        player.setGrounded(false);
        player.setPlatformToIgnore(null);

        //
        // ANIMALS

        squirrel = new AnimalImpl(1000, groundHeight, new Texture("squirrel.png"), Messages.GO_FORWARD);
        squirrel.setId(0);
        bird = new AnimalImpl(4000, 500, new Texture("bird.png"), Messages.PIUPIU);
        bird.setId(1);
        owl = new AnimalImpl(600, 500, new Texture("owl3.png"), Messages.SHE);
        owl.setId(2);
        bunny2 = new AnimalImpl(2500, groundHeight, new Texture("bunny2.png"), Messages.SING);
        bunny2.setId(7);
        bird3 = new AnimalImpl(250, 500, new Texture("bird3.png"), Messages.thunder);
        bird3.setId(6);
        bunny = new AnimalImpl(5000, groundHeight, new Texture("bunny.png"), Messages.thunderKill);
        bunny.setId(5);

        animals = new ArrayList<>();
        animals.add(squirrel);
        animals.add(bird);
        animals.add(owl);
        animals.add(bunny2);
        animals.add(bird3);
        animals.add(bunny);

        // PLATFORMS

        smallPlatform = new Texture("asset_small.png");
        bigPlatform = new Texture("asset_big.png");

        platforms = new ArrayList<>();
        platforms.add(new BigPlatform(200, 150));
        platforms.add(new SmallPlatform(550, 215));
        platforms.add(new SmallPlatform(880, 350));
        platforms.add(new SmallPlatform(1200, 400));
        platforms.add(new BigPlatform(1500, 450));
        platforms.add(new BigPlatform(1800, 150));
        platforms.add(new BigPlatform(2080, 300));
        platforms.add(new SmallPlatform(2380, 450));
        platforms.add(new SmallPlatform(2530, 450));
        platforms.add(new SmallPlatform(2800, 200));
        platforms.add(new SmallPlatform(3170, 350));
        platforms.add(new BigPlatform(3400, 450));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        batch.draw(background, backgroundX, 0);
        batch.draw(animation.getKeyFrame(elapsedTime, true), player.getX(), player.getY());

        for (Platform platform : platforms) {

            if (platform.getType().equals(PlatformType.SMALL)) {
                batch.draw(smallPlatform, platform.getCollider().x, platform.getCollider().y, platform.getWidth(), platform.getHeight());
            }

            if (platform.getType().equals(PlatformType.BIG)) {
                batch.draw(bigPlatform, platform.getCollider().x, platform.getCollider().y, platform.getWidth(), platform.getHeight());
            }
        }

        for (Animal animal : animals) {
            batch.draw(animal.getImage(), animal.getCollider().x, animal.getCollider().y);
        }

        draw();
        userInputs();
        jump();

        batch.end();
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


        for (Animal animal : animals) {
            animal.dispose();
        }

        smallPlatform.dispose();
        bigPlatform.dispose();


    }

    /**
     * Checks if player is at the middle of window
     *
     * @return true if player is at the middle; false otherwise
     */
    private boolean playerIsAtCenter() {

        float playerCenter = (player.getX() + (player.getX() + player.getCollider().getWidth())) / 2;
        float windowCenter = windowWidth / 2;
        int toleranceMargin = 80;

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
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            System.exit(0);
        }



        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            // Check if player will collide with something on this movement

            Rectangle mockRectangle = new Rectangle(player.getX() - 400 * Gdx.graphics.getDeltaTime(),
                    player.getY(), player.getCollider().width, player.getCollider().height);

            Platform collided = CollisionDetector.playerAndPlatforms(mockRectangle, platforms);
            if (collided != null) {
                return;
            }

            // Move the player if it's not on the center of window

            if (!playerIsAtCenter()) {
                player.getCollider().x -= 400 * Gdx.graphics.getDeltaTime();

                // Move the background if there is some of it out-of-bounds
                // Move animals and platforms as well

            } else if (backgroundX + 5 <= 0) {
                backgroundX += 5;
                moveAnimals(5);
                movePlatforms(5);

                // Otherwise, just keep moving the player

            } else {
                player.getCollider().x -= 400 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            // Check if player will collide with something on this movement

            Rectangle mockRectangle = new Rectangle(player.getX() + 400 * Gdx.graphics.getDeltaTime(),
                    player.getY(), player.getCollider().width, player.getCollider().height);

            Platform collided = CollisionDetector.playerAndPlatforms(mockRectangle, platforms);
            if (collided != null) {
                return;
            }

            // Move the player if it's not on the center of window

            if (!playerIsAtCenter()) {
                player.getCollider().x += 400 * Gdx.graphics.getDeltaTime();

                // Move the background if there is some of it out-of-bounds
                // Move animals and platforms as well

            } else if ((backgroundX + background.getWidth()) - 5 >= windowWidth) {
                backgroundX -= 5;
                moveAnimals(-5);
                movePlatforms(-5);

                // Otherwise, just keep moving the player

            } else {
                player.getCollider().x += 400 * Gdx.graphics.getDeltaTime();
            }
        }

        // AFTER ALL MOVEMENTS ON LEFT or RIGHT

        checkGrounding();

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {

            // Initiate a jumping sequence

            if (!player.isJumping()) {
                player.setJumping(true);
                player.setGrounded(false);
                maxJumpHeight = player.getCollider().y + 200;
            }
        }

        checkInteractionWithAnimals();

        if (player.getX() < 0) {
            player.getCollider().x = 0;
        }

        if (player.getX() > 940) {
            player.getCollider().x = 940;
        }
    }

    private void checkGrounding() {

        if (!player.isGrounded()) {
            return;
        }

        Rectangle mockCollider = new Rectangle(player.getX(), player.getY() - 10,
                player.getCollider().width, player.getCollider().height);

        player.setPlatformToIgnore(CollisionDetector.playerAndPlatforms(mockCollider, platforms));
        if (player.getPlatformToIgnore() == null && player.getY() > groundHeight) {
            player.setGrounded(false);
            fallDown();
        }


        // dont forget to set the platform to ignore
    }

    private void checkInteractionWithAnimals() {

        for (Animal animal : animals) {

            if (player.getCollider().overlaps(animal.getCollider())) {
                repositionPlayer(animal);
                renderSingleLine(animal);
            }
        }
    }

    public void repositionPlayer(Animal animal) {

        if (!animal.hasTalked()) {

            animalIcons[animal.getId()].setShow(true);
            animal.setTalked();

        }

        if (player.getX() < animal.getCollider().x) {
            player.getCollider().x = animal.getCollider().x - player.getCollider().width + 1;
        } else {
            player.getCollider().x = animal.getCollider().x + animal.getCollider().width - 1;
        }

    }

    private void fallDown() {

        player.setFalling(true);
        player.setJumping(true);
        jumpDistance = -1;
    }

    /**
     * Moves all animals according to player input, when necessary
     *
     * @param deltaX the distance to move in the X-axis
     */
    private void moveAnimals(float deltaX) {

        for (Animal animal : animals) {
            animal.getCollider().x += deltaX;
        }
    }


    /**
     * Moves all platforms according to player input, when necessary
     *
     * @param deltaX the distance to move in the X-axis
     */
    private void movePlatforms(float deltaX) {

        for (Platform platform : platforms) {
            platform.getCollider().x += deltaX;
        }
    }

    /**
     * Method for ascending and descending the player
     * Calls collision with Blocks
     */
    private void jump() {

        // if player is not in mid-air, ignore this method
        if (!player.isJumping()) {
            return;
        }

        player.getCollider().y += jumpDistance;

        // if player is going up, decrement the amount of travel distance for each tick
        // until a maximum value
        if (!player.isFalling() && jumpDistance >= 0.5) {
            jumpDistance -= 0.1;

        }

        // if the player is going down, increment the amount of travel distance for each tick
        // until a maximum value
        if (player.isFalling() && jumpDistance <= -0.5) {
            jumpDistance -= 0.1;
        }

        // if player reaches its target Y while going up, invert the travel direction
        if (!player.isFalling() && player.getY() >= maxJumpHeight) {
            player.setFalling(true);
            jumpDistance *= -1;
        }

        // if player is falling and the feet collide with a platform, return to idle state
        Platform targetPlatform = CollisionDetector.playerFallsOnPlatform(player.getCollider(), platforms);
        if (player.isFalling() && targetPlatform != null) {
            returnToIdleState(targetPlatform.getCollider().y + targetPlatform.getCollider().getHeight());
        }

        // if player is falling and reaches ground level, return to idle state
        if (player.isFalling() && player.getY() <= groundHeight) {
            returnToIdleState(groundHeight);

        }
    }

    private void returnToIdleState(float targetY) {

        player.getCollider().y = targetY;
        jumpDistance = defaultJumpDistance;
        player.setFalling(false);
        player.setJumping(false);
        player.setGrounded(true);
    }


    /**
     * Renders the animal's line on top of it [TESTING ONLY]
     */
    private void renderSingleLine(Animal animal) {

        cache.setText(animal.getSentence(), animal.getCollider().x + 10, animal.getCollider().y + animal.getCollider().height + 35);
        cache.setColors(Color.WHITE);

        cache.draw(batch);
    }


    public void draw(){
        int x = 900;
        int everytrue = 0;
        for (int i = 0; i < animalIcons.length; i++) {
            if (animalIcons[i].isShow()) {
                everytrue++;
                batch.draw(animalIcons[i].getTexture(), x, 600, 45, 45);
                x += 50;
            }
        }
        if (everytrue == 6){
            princessOneDream.setScreen(new DarkFlorest(princessOneDream));
        }
    }

}
