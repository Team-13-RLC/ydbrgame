package com.team13.game.utils;

/**
 * Class to time each leg of the race.
 */
public class Timer {
    /**
     * Starting time of the timer.
     */
   private long startTime;

    /**
     * how much time passed between timer being started and stopped.
     */
    private long elapsedTime;

    /**
     * Constructor of the timer object. Resets the timer.
     */
    public Timer(){
        reset();
    }

    //Methods

    /**
     * assigns current system time to startsTime
     */
    public void start(){
        startTime = System.currentTimeMillis();
    }

    /**
     * Subtracts startTime from current system time, to get elapsedTime.
     */
    public void stop(){
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    /**
     * Resets both the startTime and elapsed time.
     */
    public void reset() {
        startTime = 0L;
        elapsedTime = 0L;
    }

    /**
     * Adds time to elapsed time.
     * Takes in seconds so has to multiply by 1000.
     * @param seconds number fo seconds to be added.
     */
    public void addTime(long seconds){
        elapsedTime += seconds*1000;
    }


    // Getters
    public long getTimeMillis() {
        return elapsedTime;
    }

    /**
     * Converts time into a formatted string in the format M:SS
     * @return formatted string
     */
    public String getTimeFormatted(){
        String minutes = Integer.toString((int)(elapsedTime/1000)/60);
        int numSeconds = (int)(elapsedTime /1000)%60;
        String seconds = numSeconds > 9 ? Integer.toString(numSeconds) : "0" + numSeconds;
        return  minutes + ":" + seconds;
    }
}
