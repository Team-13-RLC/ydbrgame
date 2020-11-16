package com.team13.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Lane {
    // Consider changing these to short
    private int lBorder;
    private int rBorder;
    // How wide the border of a lane is
    private static final byte width = 5;
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;



    // Private constructor so borders never get initialised to 0
    private Lane() {
    }


    public Lane(int lBorder, int rBorder) {
        shapeRenderer = new ShapeRenderer();
        this.lBorder = lBorder;
        this.rBorder = rBorder;
    }

        public void draw(Camera camera){
            // Between .begin and .eng shapes can be drawn.
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            // Color is set to black
            shapeRenderer.setColor(0, 0, 0, 1);
            // Left border drawn (god this is awful)
            shapeRenderer.rect( lBorder, 0, width, mainGame.Resolution.HEIGHT);
            // Right border drawn
            shapeRenderer.rect( rBorder - width, 0, width, mainGame.Resolution.HEIGHT);
            shapeRenderer.end();

    }


}
