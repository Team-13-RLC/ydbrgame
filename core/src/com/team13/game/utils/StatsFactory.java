package com.team13.game.utils;

import com.team13.game.stats.Stats;

import java.util.Random;

/**
 * Class to generate Stats for AIBoats
 * @see Stats
 * @see com.team13.game.boat.AIBoat
 */
public class StatsFactory {
    /**
     * Static function to calculate balanced stats for AIBoats.
     * maxSpeed and acceleration always multiply to give the same value. maxSpeed is chosen first at random between 3 and 7.
     * Robustness and manoeuvrability also multiply to give the same value. robustness is chosen first, as a value between 50 and 150.
     * @return the generated Stats object.
     * @see Stats
     * @see com.team13.game.boat.AIBoat
     */
    public static Stats make_stats(){
        Random random = new Random();
        // Generate random between 3 and 7 (inclusive)
        float maxSpeed = random.nextInt((7-4)+ 1) + 4;
        // Generate random between 50 and 150 (inclusive)
        float robustness = random.nextInt((150 - 50) + 1) + 50;
        float acceleration = maxSpeed / 5;
        float manoeuvrability = 300/ robustness;
        return new Stats(acceleration, maxSpeed, manoeuvrability, robustness, 0);
    }
}
