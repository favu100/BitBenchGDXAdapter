package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by fabian on 01.05.17.
 */

public class GdxEngine {

    /**
     *
     * @param radius - gets a radius
     * @param color - gets a color
     * @return returns a LibGDX Circle-Texture with the given radius an color
     */
    public static Texture createCircleTexture(float radius, Color color) {
        Pixmap pixmap = createPixmap((int) (2*radius), (int) (2*radius), color);
        pixmap.fillCircle((int) radius,  (int) radius, (int) radius);
        return createTextureFromPixmap(pixmap);
    }

    /**
     *
     * @param edgeLength - gets the length of the edge
     * @param color - gets a color
     * @return returns a LibGDX Rectangle-Texture with the length of the edge a the color
     */
    public static Texture createRectangleTexture(float edgeLength, Color color) {
        Pixmap pixmap = createPixmap((int) edgeLength, (int) edgeLength, color);
        pixmap.fill();
        return createTextureFromPixmap(pixmap);
    }

    /**
     * @param width - gets the width
     * @param height - gets the height
     * @param color - gets the color
     * @return returns a LibGDX Polygon-Texture with the given sizes and color
     */
    public static Texture createPolygonTexture(float width, float height, Color color) {
        Pixmap pixmap = createPixmap((int) width, (int) height, color);
        pixmap.fill();
        return createTextureFromPixmap(pixmap);
    }

    /**
     *
     * @param length - gets the length
     * @param color - gets the color
     * @param horizontal - gets a boolean whether the line should be horizontal
     * @return returns a horizontal or vertical LibGDX Line-Texture with the given sizes, color
     */
    public static Texture createLine(float length, Color color, boolean horizontal) {
        int lineSize = 2;
        Pixmap pixmap = createPixmap(lineSize, (int) length, color);
        if(horizontal) {
            pixmap = createPixmap((int) length, lineSize, color);
        }
        pixmap.fill();
        return createTextureFromPixmap(pixmap);
    }

    /**
     *
     * @param pixmap - gets a LibGDX-Pixmap
     * @return returns a Texture from the pixmap
     */
    private static Texture createTextureFromPixmap(Pixmap pixmap) {
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    /**
     *
     * @param x - gets the x-coordinate
     * @param y - gets the y-coordinate
     * @param pathUp - gets the path of the image for the ImageButton if it is not checked
     * @param pathDown - gets the path of the image for the ImageButton if it is checked
     * @param toggle - gets the boolean whether the button should be a toggle button
     * @return creates a ImageButton with the given information
     */
    public static ImageButton createImageButton(float x, float y, String pathUp, String pathDown, boolean toggle) {
        return createImageButton(x,y, new Texture(Gdx.files.internal(pathUp)), new Texture(Gdx.files.internal(pathDown)), toggle);
    }


    /**
     *
     * @param x - gets the x-coordinate
     * @param y - gets the y-coordinate
     * @param textureUp - gets the texture of the image for the ImageButton if it is not checked
     * @param textureDown - gets the texture of the image for the ImageButton if it is checked
     * @param toggle - gets the boolean whether the button should be a toggle button
     * @return creates a ImageButton with the given information
     */
    public static ImageButton createImageButton(float x, float y, Texture textureUp, Texture textureDown, boolean toggle) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        Drawable drawableUp = new TextureRegionDrawable(new TextureRegion(textureUp));
        Drawable drawableDown = new TextureRegionDrawable(new TextureRegion(textureDown));
        ImageButton button;
        if(toggle) {
            button = new ImageButton(drawableUp, null, drawableDown);
        } else {
            button = new ImageButton(drawableUp, drawableDown);
        }
        button.setSize(0.07f*width, 0.13f*height);
        button.setPosition(x,y);
        return button;
    }

    /**
     *
     * @param backgroundPath - gets the path for the background of the touchpad
     * @param knobPath - gets the path for the knob of the touchpad
     * @return creates the touchpad with the Texture of the images of the given path
     *         the maximum bounds of the touchpad is constant and set here
     */
    public static Touchpad createTouchpad(String backgroundPath, String knobPath) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        Drawable drawableBackground = new TextureRegionDrawable(new TextureRegion(new Texture(backgroundPath)));
        Drawable drawableKnob =  new TextureRegionDrawable(new TextureRegion(new Texture(knobPath)));
        Touchpad touchpad = new Touchpad(10, new Touchpad.TouchpadStyle(drawableBackground, drawableKnob));
        touchpad.setBounds((float) 0.03*width, (float) 0.03*height, 150, 150);
        touchpad.setSize(0.07f*width, 0.07f*width);
        return touchpad;
    }

    /**
     *
     * @param width - gets the width
     * @param length - gets the height
     * @param color - gets the color
     * @return returns the pixmap with the given size and color (the background is transparent)
     */

    private static Pixmap createPixmap(int width, int length, Color color) {
        Pixmap pixmap = new Pixmap(width, length, Pixmap.Format.RGBA8888);
        // transparent background
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        pixmap.setColor(color);
        return pixmap;
    }

    /**
     *
     * @param sb - gets the SpriteBatch
     * @param bf - gets the BitmapFont
     * @param color - gets the color
     * @param text - gets the text
     * @param coords - gets the position
     * @return creates a BitmapFontCache with the BitmapFont
     *         it writes the given text with the given color at the given position with the SpriteBatch
     */

    public static BitmapFontCache createBitmapFontCache(SpriteBatch sb, BitmapFont bf, Color color, String text, Vector coords) {
        BitmapFontCache bfCache = new BitmapFontCache(bf);
        bfCache.setColor(color);
        bfCache.addText(text, coords.getX(), coords.getY());
        bfCache.draw(sb);
        return bfCache;
    }


}
