package com.team13.game.boat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.team13.game.lane.Lane;

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
     * @param lane_right right lane boundry.
     * @param lane_left left lane boundry.
     */
    public boatAI(Integer skill, int lane_right, int lane_left){
        skill_level = skill;
        ailane = new Lane(lane_left,lane_right);
        Random ran = new Random();
        Integer min = skill_level;
        //50 used as placeholder until testing can be done
        Integer max = min + 50;
        detection_distance = ran.nextInt((max+1)-min) + min;
    }

//Methods

    public String Direction(Obstacle obstacle, boatAI boat) {
        String output = "";
        if(obstacle.stationary()){
            if(obstacle.getobstaclepostion.getPosX() <= boat.ailane.getMiddle()){
                output = "R";
            }else{
                output = "L";
            }
            return output;
        }else if(obstacle.dynamic()){
            if(obstacle.direction() < 0){
                output = "R";
            }else{
                output = "L";
            }
            return output;
        }else{
            return output;
        }


    }

//    public boolean slowDown() {
//
//        return true;
//    }



    private boolean obstacle_detected(Obstacle obstacle, boatAI boat){


            if(obstacle.y < boat.getBoatPosition().getPosY()){
                return false;
            }
            //come back if x is a range of values
            if(obstacle.y <= detection_distance && obstacle.x == boat.getBoatPosition().getPosX()){
                return true;
            }else{
                return false;
            }

    }

    public void Movement(boatAI boat, ArrayList<Obstacle> obstacles){

        boat.accelerate(1);
        Iterator obstacle_iterator = obstacles.iterator();

        while(obstacle_iterator.hasNext())
            if(obstacle_detected(obstacle_iterator.next(),boat)){
                String dir = Direction(obstacle,boat);
                if(dir == "L"){

                    boat.boatPosition.setPosX(boat.getBoatPosition().getPosX() - boat.getBoatStats().getManeuverability());

                }else if(dir == "R"){

                    boat.boatPosition.setPosX(boat.getBoatPosition().getPosX() - boat.getBoatStats().getManeuverability());

                }
            }




    }

}
