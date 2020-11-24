package com.team13.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;


public class TexturePicker {
    int texturesPicked;
    ArrayList<FileHandle> textureArray;
    String[] files;
    public TexturePicker(String texturelistDirectory, String texturelistFile) {
        textureArray = new ArrayList<>();
        files = Gdx.files.internal(texturelistDirectory + "/" + texturelistFile).readString().split("\\r?\\n");
        for (String file : files) {
            textureArray.add(Gdx.files.internal(texturelistDirectory + "/" + file));
        }
    }

    public  FileHandle pick(){
        return textureArray.get(texturesPicked++);
    }
}
