package com.team13.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;


/**
 * Class to pick a texture from a texturelist file.
 */
public class TexturePicker {
    /**
     * Keeping track of how many textures have been picked.
     */
    int texturesPicked;

    /**
     * List of textures to shoose from
     */
    ArrayList<FileHandle> textureArray;

    /**
     * List of filenames for textures
     */
    String[] files;

    /**
     * Constructor for the TexturePicker.
     * Initializes the textureArray. Reads the texturelist file, splitting ot into an array by line ending (both windows and unix compliant)
     * Adds FileHandles for each texture file to the textureArray.
     * @param texturelistDirectory directory which holds the texturelist file
     * @param texturelistFile name of the texturelist file
     */
    public TexturePicker(String texturelistDirectory, String texturelistFile) {
        textureArray = new ArrayList<>();
        files = Gdx.files.internal(texturelistDirectory + "/" + texturelistFile).readString().split("\\r?\\n");
        for (String file : files) {
            textureArray.add(Gdx.files.internal(texturelistDirectory + "/" + file));
        }
    }

    /**
     * Picks a texture out of the textureArray and increments texturesPicked.
     * @return FileHandle of the picked texture.
     */
    public  FileHandle pick(){
        return textureArray.get(texturesPicked++);
    }
}
