package com.team13.game.boat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

public class UserBoat extends Boat {

    public UserBoat(Position position, Stats stats){
        spriteScale = 0.2F;
        boatPosition = position;
        boatStats = stats;
        boatTexture = new Texture(Gdx.files.internal("textures/boatTexture.png"));
        boatTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        boatSprite = new Sprite(boatTexture);
        boatSprite.setPosition(boatPosition.getPosX(), boatPosition.getPosY());
        boatSprite.setScale(spriteScale);

    }

    @Override
    public void control(){
        //when D is pressed, moving position to right
        if (Gdx.input.isKeyPressed(Keymap.RIGHT)){
            boatPosition.setPosX(boatPosition.getPosX() + boatStats.getManeuverability());
            this.boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());

            //when A is pressed, moving position to left
        } else if (Gdx.input.isKeyPressed(Keymap.LEFT)){
            boatPosition.setPosX(boatPosition.getPosX() - boatStats.getManeuverability());
            this.boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());

            //when W is pressed, the boat is accelerating
        } else if (Gdx.input.isKeyPressed(Keymap.UP)){
            accelerate(Direction.FORWARDS);
            boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
            
        //when S is pressed, the boat is decelerating
        } else if (Gdx.input.isKeyPressed(Keymap.DOWN)){
            if (boatStats.getSpeed() > 0) {
                accelerate(Direction.BACKWARDS);
                // Position is is still increased, but boat speed is decreasing
                this.boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
            }

        } else {
            update();
        }

    }


    // Using this class, controls can be redefined more easily
    public static class Keymap{
        public static final int UP = Input.Keys.W;
        public static final int DOWN = Input.Keys.S;
        public static final int LEFT = Input.Keys.A;
        public static final int RIGHT = Input.Keys.D;
    }
}
