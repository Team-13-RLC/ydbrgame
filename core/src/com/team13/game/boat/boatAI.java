package com.team13.game.boat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.team13.game.lane.Lane;
import com.team13.game.stats.Position;

//Fields
public class boatAI extends Boat{
    /**
     * Holds the "skill level" of the boat which is used to determine
     * detection distance.
     **/
    private Integer skill_level = 0;
    /**
     * Holds the detection distance for obstacles once that is calculated
     * using the skill level in the constructor statement.
     */
    private Integer detection_distance = 0;
    /**
     * Holds the left and right borders of the lane that the ai is in
     * also contains methods that allow the getting of the co-ords of said
     * borders.
     */
    private Lane ailane;

//Constructors

    /**
     * Constructor for the boat_ai.
     * Allocates the lane the ai uses and sets the detection distance for obstacles.
     * @param skill the minimum bound for obstacle detection.
     * @param lane_right right lane boundary.
     * @param lane_left left lane boundary.
     */
    public boatAI(Integer skill, int lane_right, int lane_left){
        skill_level = skill;
        ailane = new Lane(lane_left,lane_right);
        Random ran = new Random();
        int min = skill_level;
        //50 used as placeholder until testing can be done
        int max = min + 50;
        detection_distance = ran.nextInt((max+1)-min) + min;
    }

//Methods

    public String Direction(Obstacle obstacle) {

        String output = "";
        if(obstacle instanceof staticObstacle){
            if(obstacle.getObstaclePosition.getPosX() <= ailane.getMiddle()){
                output = "R";
            }else{
                output = "L";
            }
        }else{


            if(obstacle.getVelocityX<0){
                output = "R";
            }else{
                output = "L";
            }
        }
        return output;

    }

//    public boolean slowDown() {
//
//        return true;
//    }



    private boolean obstacle_detected(Obstacle obstacle){


        if(obstacle.getObstaclePosition.getPosY < getBoatPosition().getPosY()){
            return false;
        }
        //come back if x is a range of values
        return obstacle.getObstaclePosition.getPosY <= detection_distance && ((obstacle.getObstaclePosition.getPosX - obstacle.getObstacleWidth()/2  < getBoatPosition().getPosX() + spriteWidth/2 && obstacle.getObstaclePosition.getPosX - obstacle.getObstacleWidth()/2 > getBoatPosition().getPosX() - spriteWidth/2)||(obstacle.getObstaclePosition.getPosX + obstacle.getObstacleWidth()/2  > getBoatPosition().getPosX() - spriteWidth/2 && obstacle.getObstaclePosition.getPosX + obstacle.getObstacleWidth() < getBoatPosition().getPosX() + spriteWidth/2));

    }

    public void Control(){
        ArrayList<Obstacle> obstacles = Spawn.getObstacleList();
        accelerate(1);
        Iterator obstacle_iterator = obstacles.iterator();
        boolean detected = false;
        while(obstacle_iterator.hasNext())
            Obstacle currentObst = obstacle_iterator.next();
            if(obstacle_detected(currentObst)){
                String dir = Direction(currentObst);
                detected = true;
                turn(dir);
            }
        if (!detected){
            if (getBoatPosition().getPosX() > ailane.getrBorder()) {
                turn("L");
            } else if (getBoatPosition().getPosX() < ailane.getlBorder()) {
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
