package com.team13.game.utils;

public class Timer {
   private long startTime;
    private long elapsedTime;
    private long penalties;
    public Timer(){
        reset();
    }

    //Methods
    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedTime += penalties;
    }

    public void reset() {
        startTime = 0L;
        elapsedTime = 0L;
        penalties = 0L;
    }

    public void addTime(long seconds){
        penalties += seconds*1000;
    }


    // Getters
    public long getTimeMillis() {
        return elapsedTime;
    }

    public String getTimeFormatted(){
        return Integer.toString((int)(elapsedTime/1000)/60) + ':' + (int)(elapsedTime /1000)%60;
    }
}
