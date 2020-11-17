package com.team13.game;

import org.graalvm.compiler.replacements.nodes.ArrayEqualsNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class boatAI extends Boat{
//Detection, Movement,
//Skill level acting as minimum for detection range
    private Integer skill_level = 0;
    private Integer detection_distance = 0;

    public boatAI(Integer skill){
        skill_level = skill;

        Random ran = new Random();
        Integer min = skill_level;
        //50 used as placeholder until testing can be done
        Integer max = min + 50;
        detection_distance = ran.nextInt((max+1)-min) + min;
    }

    public String Direction(Obstacle obstacle, boatAI boat) {
        String output = "";
        if(obstacle.stationary()){
            if(obstacle.getobstaclepostion.getPosX() < (boat.lane.right() + boat.lane.left())/2){
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
