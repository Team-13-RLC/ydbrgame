package com.team13.game.lane;


public class UserLane extends Lane {
    /**
     * Constructor takes 2 values for border positions and assigns them to border positions of the instance.
     * Also initialises the shape renderer used to render the borders
     * The only reason for this thing to exist is to be able to tell which lane the user belongs to.
     * It does not add any functionality at the moment.
     *
     * @param lBorder left border x coordinate
     * @param rBorder right border x coordinate
     */
    public UserLane(int lBorder, int rBorder) {
        super(lBorder, rBorder);
    }

    // For testing. This will turn the user lane red
//    public void draw(){
//        // Between .begin and .eng shapes can be drawn.
//        shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
//        // Color is set to black
//        shapeRenderer.setColor(1, 0, 0, 1);
//        // Left border drawn (god this is awful)
//        shapeRenderer.rect( lBorder, 0, BORDERWIDTH, com.team13.game.mainGame.Resolution.HEIGHT);
//        // Right border drawn
//        shapeRenderer.rect( rBorder - BORDERWIDTH, 0, BORDERWIDTH, com.team13.game.mainGame.Resolution.HEIGHT);
//        shapeRenderer.end();
//    }

}
