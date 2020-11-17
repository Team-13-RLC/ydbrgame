package com.team13.game.lane;

public class UserLane extends Lane {
    /**
     * Constructor takes 2 values for border positions and assigns them to border positions of the instance.
     * Also initialises the shape renderer used to render the borders
     *
     * @param lBorder left border x coordinate
     * @param rBorder right border x coordinate
     */
    public UserLane(int lBorder, int rBorder) {
        super(lBorder, rBorder);
    }


}
