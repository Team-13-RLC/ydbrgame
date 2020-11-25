package com.team13.game.boat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
    public AIBoat(Position position, Stats stats, int skill_level, final Lane aiLane, FileHandle textureLocation){
        // Drawing things.
        spriteScale = 0.2F;
        boatPosition = position;
        boatStats = stats;
        // TODO: make a random texture picker;
        boatTexture = new Texture(textureLocation);
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

        // Provies a way to react to different kinds of obstacles later.
        if(obstacle instanceof Obstacle){
            if (boatPosition.getPosX() + spriteWidth < aiLane.getlBorder()){
                return "R";
            }
            if (boatPosition.getPosX() + spriteWidth > aiLane.getrBorder()){
                return "L";
            }
            if(obstacle.getObstaclePosition().getPosX() <= aiLane.getMiddle()){
                return "R";
            }else{
                return "L";
            }
        }else{
//            if(obstacle.getVelocityX() < 0){
//                output = "R";
//            }else{
//                output = "L";
//            }
        }
        return "";
    }


//    public boolean slowDown() {
//
//        return true;
//    }



    private boolean obstacle_detected(Obstacle obstacle){
        // Added for clarity
        Rectangle obstacleBounds = obstacle.getObstacleSprite().getBoundingRectangle();
        float obstacleBottomBound = obstacleBounds.y;
        float obstacleTopBound = obstacleBottomBound + obstacleBounds.height;
        float obstacleLeftBound = obstacleBounds.x;
        float obstacleRightBound = obstacleLeftBound + obstacleBounds.width;

        if(boatPosition.getPosY() > obstacleTopBound){
            return false;
        }
        //come back if x is a range of values
        if (obstacleBottomBound < detection_distance + boatPosition.getPosY()) {
            return obstacleLeftBound < boatPosition.getPosX() + spriteWidth ||
                    obstacleRightBound > boatPosition.getPosX();
        } else{
            return false;
        }
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
