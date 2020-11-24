package com.team13.game.boat;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team13.game.lane.Lane;
import com.team13.game.obstacle.Obstacle;
import com.team13.game.obstacle.Spawn;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

//Fields
public class AIBoat extends Boat{
    /**
     * Holds the detection distance for obstacles once that is calculated
     * using the skill level in the constructor statement.
     */
    private final int detection_distance;
    /**
     * Holds the left and right borders of the lane that the ai is in
     * also contains methods that allow the getting of the co-ords of said
     * borders.
     */
    private final Lane aiLane;


//Constructors

    /**
     * Constructor for the boat_ai.
     * Allocates the lane the ai uses and sets the detection distance for obstacles.
     * @param skill_level the minimum bound for obstacle detection.
     * @param aiLane lane which this boat belongs to
     */
    public AIBoat(Position position, Stats stats, int skill_level, final Lane aiLane){
        // Drawing things.
        spriteScale = 0.2F;
        boatPosition = position;
        boatStats = stats;
        // TODO: make a random texture picker;
        boatTexture = new Texture(Gdx.files.internal("textures/AIboatTexture1.png"));
        boatTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        boatSprite = new Sprite(boatTexture);
        boatSprite.setPosition(boatPosition.getPosX(), boatPosition.getPosY());
        boatSprite.setScale(spriteScale);
        spriteWidth = boatSprite.getBoundingRectangle().width;
        spriteHeight = boatSprite.getBoundingRectangle().height;

        // Specific AI boat stuff
        this.aiLane = aiLane;
//        Random ran = new Random();
        //50 used as placeholder until testing can be done
//        int max = skill_level + 50;
        detection_distance = skill_level;
//        detection_distance = ran.nextInt((max+1)- skill_level) + skill_level;
    }

//Methods

    public String direction(Obstacle obstacle) {

        String output = "";
        if(obstacle instanceof Obstacle){
            if(obstacle.getObstaclePosition().getPosX() <= aiLane.getMiddle()){
                output = "R";
            }else{
                output = "L";
            }
        }else{
//            if(obstacle.getVelocityX() < 0){
//                output = "R";
//            }else{
//                output = "L";
//            }
        }
        return output;

    }

//    public boolean slowDown() {
//
//        return true;
//    }



    private boolean obstacle_detected(Obstacle obstacle){
        // Added for clarity
        float obstaclePosY = obstacle.getObstaclePosition().getPosY();
        float obstaclePosX = obstacle.getObstaclePosition().getPosX();
        float obstacleWidth = obstacle.getObstacleWidth();

        if(obstacle.getObstaclePosition().getPosY() < getBoatPosition().getPosY()){
            return false;
        }
        //come back if x is a range of values
        if (obstaclePosY < detection_distance + getBoatPosition().getPosY()) {
            if (obstaclePosX - obstacleWidth / 2 < getBoatPosition().getPosX() + spriteWidth / 2 &&
                    obstaclePosX - obstacleWidth / 2 > getBoatPosition().getPosX() - spriteWidth / 2) {
                return true;
                // Yes, i know it can be simplified
            } else if (obstaclePosX + obstacleWidth / 2 > getBoatPosition().getPosX() - spriteWidth / 2 &&
                    obstaclePosX + obstacleWidth < getBoatPosition().getPosX() + spriteWidth / 2) {
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }

        // removed for readability
//        return posY <= detection_distance &&
//                ((posX - width/2  < getBoatPosition().getPosX() + spriteWidth/2 &&
//                        posX - width/2 > getBoatPosition().getPosX() - spriteWidth/2)||
//                        (posX + width/2  > getBoatPosition().getPosX() - spriteWidth/2 &&
//                                posX + width < getBoatPosition().getPosX() + spriteWidth/2));
    }

    @Override
    public void control(final Spawn spawn) {
        accelerate(Direction.FORWARDS);
        boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
        boolean detected = false;
        for (Obstacle o : spawn.getObstacleList()) {
            if(obstacle_detected(o)){
                detected = true;
                turn(direction(o));
            }
        }
        if (!detected){
            if (getBoatPosition().getPosX() > aiLane.getrBorder()) {
                turn("L");
            } else if (getBoatPosition().getPosX() < aiLane.getlBorder()) {
                turn("R");
            }
        }
    }



    private void turn(String dir){
        if (dir.equals("L")) {

            boatPosition.setPosX(getBoatPosition().getPosX() - getBoatStats().getManeuverability());

        } else if (dir.equals("R")) {

            boatPosition.setPosX(getBoatPosition().getPosX() + getBoatStats().getManeuverability());

        }
    }
}
