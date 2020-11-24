package com.team13.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TexturePicker {
    int texturesPicked;
    FileHandle[] textureArray;
    public TexturePicker(final int numTextures, FileHandle dir) {
//        textureArray = dir.list();
    }

    public  FileHandle pick(){
//        return textureArray[0];
       return Gdx.files.internal("textures/aitextures/AIboatTexture2.png");
    }
}
