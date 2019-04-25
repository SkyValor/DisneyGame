package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PrincessDream extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Rectangle player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		//player move
		player = new Rectangle();
		player.x = 0;
		player.y = 0;
		player.width = 50;
		player.height = 150;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, player.x, player.y);
		batch.end();
		userInputs();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	private void userInputs() {


		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x -= 400 * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x += 400 * Gdx.graphics.getDeltaTime();
		}


		if (player.x < 0) {
			player.x = 0;
		}

		if (player.x > 940) {
			player.x = 940;
		}


	}
}
