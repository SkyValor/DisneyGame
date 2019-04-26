package com.mygdx.game.animals;

import com.badlogic.gdx.graphics.Texture;

public class AnimalIcon {

    private Texture texture;
    private boolean show;
    private AnimalIcon[] animalIcons;


    public AnimalIcon() {
        animalIcons = new AnimalIcon[8];
        createIcons();
    }

    public AnimalIcon(String path, boolean show) {
        this.texture = new Texture(path);
        this.show = show;
    }

    public void createIcons() {

        animalIcons[0] = (new AnimalIcon("icos/Squirl.png", false));

        animalIcons[1] = (new AnimalIcon("icos/birdAzul.png", false));


        animalIcons[2] = (new AnimalIcon("icos/Owl.png", false));

        animalIcons[3] = (new AnimalIcon("icos/grey_rabbit.png", false));

        animalIcons[4] = (new AnimalIcon("icos/red_bird.png", false));

        animalIcons[5] = (new AnimalIcon("icos/rabbit.png", false));
        animalIcons[6] = (new AnimalIcon("bird33.png", false));
        animalIcons[7] = (new AnimalIcon("grey_rabbit.png", false));


    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    
    public AnimalIcon[] getAnimalList() {
        return animalIcons;
    }

}