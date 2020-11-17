package com.team13.game.boat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class UserBoat extends Boat {
    public UserBoat() {
        boatTexture = new Texture("textures/boatTexture.png");
    }

    public void control(){
        //when D is pressed, moving position to right
        if (Gdx.input.isKeyPressed(Keymap.LEFT)){
            boatPosition.setPosX(boatPosition.getPosX() + boatStats.getManeuverability());
            
        //when A is pressed, moving position to left
        } else if (Gdx.input.isKeyPressed(Keymap.RIGHT)){
            boatPosition.setPosX(boatPosition.getPosX() - boatStats.getManeuverability());
            
        //when W is pressed, the boat is accelerating
        } else if (Gdx.input.isKeyPressed(Keymap.UP)){
            accelerate(Direction.FORWARDS);
            boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
            
        //when S is pressed, the boat is decelerating
        } else if (Gdx.input.isKeyPressed(Keymap.DOWN)){
            accelerate(Direction.BACKWARDS);
            this.boatPosition.setPosY(boatPosition.getPosY() - boatStats.getSpeed());

        } else {
            update();
        }

    }

    public void update(){
        /* when boat is moving but no keys are pressed,
        the boat is slowly decelerating until it almost becomes stationary
        */
        if (boatStats.getSpeed() > 0){
            boatStats.setSpeed(boatStats.getSpeed() - boatStats.getAcceleration());
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
