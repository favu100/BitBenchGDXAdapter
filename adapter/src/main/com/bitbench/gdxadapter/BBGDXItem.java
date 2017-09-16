package com.bitbench.gdxadapter;

/**
 * Created by fabian on 16.09.17.
 */

public abstract class BBGDXItem {

    public BBGDXItem() {
    }

    private String id;

    protected float xPos;

    protected float yPos;

    protected float width;

    protected float height;

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
