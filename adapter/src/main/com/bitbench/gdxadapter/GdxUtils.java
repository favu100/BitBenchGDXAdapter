/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.modernpong.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bitbench.modernpong.GdxGame;
import com.bitbench.modernpong.IGdxAndroidInterface;

public class GdxUtils {
    public static GdxGame sGdxGame;
    public static IGdxAndroidInterface androidInterface;
    public static BitmapFont sBitmapFont;
    public static Skin sSkin;
    public static OrthographicCamera sCamera;
    public static ScreenViewport sViewport;
    public static SpriteBatch sSB;
    public static SpriteCache sSC;
    public static I18NBundle sStrings;

    public static void addToInputProcessor(InputProcessor inputProcessor) {
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(inputProcessor);
    }

    public static void removeFromInputProcessor(InputProcessor inputProcessor) {
        ((InputMultiplexer) Gdx.input.getInputProcessor()).removeProcessor(inputProcessor);
    }

    public static void setBtnState(Button btn, boolean disabled) {
        if (disabled) {
            btn.setTouchable(Touchable.disabled);
        } else {
            btn.setTouchable(Touchable.enabled);
        }
        btn.setDisabled(disabled);
    }
}
