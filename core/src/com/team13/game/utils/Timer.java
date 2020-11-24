package com.team13.game.utils;

public class Timer {
   private long startTime;
    private long elapsedTime;
    public Timer(){
        reset();
    }

    //Methods
    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    public void reset() {
        startTime = 0L;
        elapsedTime = 0L;
    }

    public void addTime(long seconds){
        elapsedTime += seconds*1000;
    }


    // Getters
    public long getTimeMillis() {
        return elapsedTime;
    }

    public String getTimeFormatted(){
        String minutes = Integer.toString((int)(elapsedTime/1000)/60);
        int numSeconds = (int)(elapsedTime /1000)%60;
        String seconds = numSeconds > 9 ? Integer.toString(numSeconds) : "0" + Integer.toString(numSeconds);
        return  minutes + ":" + seconds;
    }
}
