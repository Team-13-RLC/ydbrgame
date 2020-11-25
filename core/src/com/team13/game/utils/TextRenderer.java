package com.team13.game.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class to render text on the screen with a static function.
 */
public class TextRenderer {
    private static final BitmapFont font = new BitmapFont();
    private static final SpriteBatch batch = new SpriteBatch();
    private static final GlyphLayout glyphLayout = new GlyphLayout();

    /**
     * Static function to render text on the screen.
     * Updates the projection matrix, for the SpriteBatch, scales the font, gets the dimensions of the text (with glyphLayout)
     * Using the dimensions of text, calculates it's x and y positions so the text can be centered.
     * Renders the text with the SpriteBatch at the correct coordinates.
     *
     * @param text Text to be rendered
     * @param posX x position of the middle of the text
     * @param posY y position of the middle of the text
     * @param camera camera who's projection matrix is being used.
     * @param scale how large the text is.
     */
    static public void print(String text, float posX, float posY, final Camera camera, float scale){
        batch.setProjectionMatrix(camera.combined);
        font.getData().setScale(scale);
        glyphLayout.setText(font, text);

        float calculatedX = posX - glyphLayout.width/2 +100;
        float calculatedY = posY + camera.position.y + glyphLayout.height/2 - camera.viewportHeight/2;



        batch.begin();
        font.draw(batch, text, calculatedX, calculatedY );
        batch.end();
    }

    /**
     * Overload of the other print function but without scale
     *
     * @param text Text to be rendered
     * @param posX x position of the middle of the text
     * @param posY y position of the middle of the text
     * @param camera camera who's projection matrix is being used.
     * @see TextRenderer#print(String, float, float, Camera, float)
     */
    static public void print(String text, float posX, float posY, final Camera camera) {
        print(text, posX, posY, camera, 1);
    }
}
