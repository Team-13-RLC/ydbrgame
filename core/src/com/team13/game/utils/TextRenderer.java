package com.team13.game.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextRenderer {
    private static final BitmapFont font = new BitmapFont();
    private static final SpriteBatch batch = new SpriteBatch();
    private static final GlyphLayout glyphLayout = new GlyphLayout();

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

    static public void print(String text, float posX, float posY, final Camera camera) {
        print(text, posX, posY, camera, 1);
    }
}
