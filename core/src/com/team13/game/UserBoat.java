package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class UserBoat extends Boat {

    UserBoat(){
    }
    public void control(){
        //when D is pressed, moving position to right
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.setPosX(this.getPosX() + this.getH_speed());
            
        //when A is pressed, moving position to left
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.setPosX(this.getPosX() - this.getH_speed());
            
        //when W is pressed, the boat is accelarating
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            accelerate(Direction.FORWARDS);
            this.setPosY(getPosY() + this.getV_speed());
            
        //when S is pressed, the boat is decelarating
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            accelerate(Direction.BACKWARDS);
            this.setPosY(getPosY() - this.getV_speed());
            
        } else {
            this.update();
        }

    }

    public void update(){
        /* when boat is moving but no keys are pressed,
        the boat is slowly decelarating until it almost becomes stationary
        */
        if (this.getV_speed() > 0){
            this.setV_speed(this.getV_speed() - this.getAcceleration());
        }
    }
}
