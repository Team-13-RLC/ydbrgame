package com.team13.game;


public class MainLoop {
    private Canvas[] canvases;
    private static final MainLoop instance = new MainLoop();
    private byte loopCounter;
    private final byte numLoops = 3;
    private boolean canvasCreated;

    private MainLoop() {
        canvases = new Canvas[numLoops];
        for (int canvas = 0; canvas < numLoops; canvas++) {
            canvases[canvas] = new Canvas();
        }
    }

    public void create(){
        loopCounter = 0;
        canvasCreated = false;
    }



    public void run(){
        if (loopCounter < numLoops){
            // Crate the canvas
            if (!canvasCreated) {
                canvases[loopCounter].create();
                canvasCreated = true;
            }

            // Have a while loop for canvas to run in

            if (!canvases[loopCounter].checkForEnd()) {
                canvases[loopCounter].update();
            } else {
                // Dispose of canvas
                canvases[loopCounter].dispose();
                loopCounter ++;
                canvasCreated = false;
            }
        }

    }


    public void resize(int width, int height) {
        for (Canvas c :
                canvases) {
            c.resize(width, height);
        }
    }
    // Getters

    public static MainLoop getInstance() {
        return instance;
    }

}