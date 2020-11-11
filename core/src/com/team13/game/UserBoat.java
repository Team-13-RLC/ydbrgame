package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class UserBoat extends Boat {

    UserBoat(){
    }
    public void control(){
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.setPosX(this.getPosX() + this.getH_speed());
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.setPosX(this.getPosX() - this.getH_speed());
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            accelerate(Direction.FORWARDS);
            this.setPosY(getPosY() + this.getV_speed());
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            accelerate(Direction.BACKWARDS);
            this.setPosY(getPosY() - this.getV_speed());
        } else {
            this.update();
        }

    }

    public void update(){
        if (this.getV_speed() > 0){
            this.setV_speed(this.getV_speed() - this.getAcceleration());
        }
    }
}
