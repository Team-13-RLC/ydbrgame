package com.team13.game.utils;

import com.team13.game.stats.Stats;

import java.util.Random;

public class StatsFactory {
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
