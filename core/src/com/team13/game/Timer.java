package com.team13.game;

public class Timer {
    long startTime;
    long elapsedTime;
    long penalties;
    Timer(){
        startTime = 0L;
        elapsedTime = 0L;
        penalties = 0L;
    }

    //Methods
    public void start(){
        startTime = System.currentTimeMillis();
    }


    public void stop(){
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedTime += penalties;
    }

    public void addTime(long seconds){
        penalties += seconds*1000;
    }


    // Getters
    public long getTimeMillis() {
        return elapsedTime;
    }

    public String getTimeFormatted(){
        return Integer.toString((int)elapsedTime/60) + ':' + Integer.toString((int)(elapsedTime /1000)%60);
    }
}
